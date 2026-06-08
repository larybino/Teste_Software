import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lary.bino.Irpf;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Simulador IRPF")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SimuladorIrpfWebTest {

    private WebDriver browser;
    private WebDriverWait wait;
    private Irpf irpf;

    private final List<double[]> results = new ArrayList<>();

    private final String simulatorUrl = "https://www27.receita.fazenda.gov.br/simulador-irpf/";

    @BeforeAll
    void setUp() {
        browser = new ChromeDriver();
        wait    = new WebDriverWait(browser, Duration.ofSeconds(15));
        irpf    = new Irpf();

        browser.manage().window().maximize();
        browser.get(simulatorUrl);

        wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("mat-select[formcontrolname='anoCalendario']")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//mat-option//span[normalize-space()='2022']")
        )).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("calculo-mensal")));
    }

    @AfterAll
    void tearDown() {
        analyzeEffectiveTaxRates();
        if (browser != null) {
            browser.quit();
        }
    }

    @ParameterizedTest(name = "{0}: R$ {1}")
    @CsvSource({
         "Isento - bem abaixo do limite,              1000.00",
        "Isento - teto exato da faixa,               1903.98",
        "Faixa 7.5%  - primeiro centavo tributavel,  1903.99",
        "Faixa 7.5%  - teto exato da faixa,          2826.65",
        "Faixa 15%   - primeiro centavo desta faixa, 2826.66",
        "Faixa 15%   - teto exato da faixa,          3751.05",
        "Faixa 22.5% - primeiro centavo desta faixa, 3751.06",
        "Faixa 22.5% - teto exato da faixa,          4664.68",
        "Faixa 27.5% - primeiro centavo desta faixa, 4664.69",
        "Faixa 27.5% - representante interno,        5000.00"
    })
    void checkTaxBoundaries(String caseName, double taxableIncome) throws InterruptedException {
        double expectedTax = irpf.calcular(taxableIncome);

        enterIncomeInSimulator(taxableIncome);

        String taxText   = getValueAfterLabel("4. Imposto");
        double siteTax   = parseBrazilianCurrency(taxText);

        results.add(new double[]{taxableIncome, siteTax});

        assertEquals(expectedTax, siteTax, 0.01, "Mismatch in case: " + caseName);
    }

  
    private void analyzeEffectiveTaxRates() {
        if (results.isEmpty()) return;

        System.out.println("ANÁLISE DE ALÍQUOTAS EFETIVAS");
        System.out.printf("%-14s  %-10s  %-14s%n",  "Renda (R$)", "Imposto", "Alíq. Efetiva");
        System.out.println("-".repeat(44));

        double previousRate   = -1;
        double previousIncome = -1;

        for (double[] entry : results) {
            double income        = entry[0];
            double tax           = entry[1];
            double effectiveRate = (income > 0) ? (tax / income) * 100 : 0;

            System.out.printf("%14.2f  %10.2f  %12.4f%%%n", income, tax, effectiveRate);

            if (previousRate >= 0 && effectiveRate > previousRate + 0.5) {
                System.out.printf("MUDANCA DE FAIXA: %.4f%% -> %.4f%%%n",
                        previousRate, effectiveRate);
            }

            if (previousRate >= 0 && income > previousIncome && effectiveRate < previousRate - 0.001) {
                System.out.printf("ANOMALIA: aliquota caiu com renda maior" +
                        " (%.4f%% -> %.4f%%)%n", previousRate, effectiveRate);
            }

            previousRate   = effectiveRate;
            previousIncome = income;
        }

        System.out.println("-".repeat(44));
        System.out.println("Casos avaliados: " + results.size());
    }

    private void enterIncomeInSimulator(double value) throws InterruptedException {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("input[formcontrolname='rendTributaveis']")
        ));

        field.click();
        field.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);

        String digitsOnly = String.format(Locale.US, "%.2f", value).replace(".", "");
        field.sendKeys(digitsOnly);
        field.sendKeys(Keys.TAB);

        Thread.sleep(1000);
    }

    private String getValueAfterLabel(String label) {
        String pageText = browser
                .findElement(By.tagName("calculo-mensal"))
                .getText();

        String[] lines = pageText.split("\\r?\\n");

        for (int i = 0; i < lines.length - 1; i++) {
            if (lines[i].trim().startsWith(label)) {
                return lines[i + 1].trim();
            }
        }

        throw new NoSuchElementException("Label não encontrado: " + label);
    }

    private double parseBrazilianCurrency(String value) {
        if (value == null || value.isBlank() || value.equals("-")) return 0.0;

        return Double.parseDouble(
            value.replaceAll("[R$\\s]", "")
                 .replace(".", "")
                 .replace(",", ".")
        );
    }
}