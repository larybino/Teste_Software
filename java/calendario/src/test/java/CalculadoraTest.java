import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lary.bino.Calculadora;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes - Calculadora (Particionamento de Equivalência)")
class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new Calculadora();
    }

    @Test
    @DisplayName("CT01 - Nenhum parâmetro: exibe mês corrente")
    void testSemParametros() {
        String resultado = calculadora.getCalendario();
        LocalDate hoje = LocalDate.now();
        assertNotNull(resultado);
        assertTrue(resultado.contains(String.valueOf(hoje.getYear())));
    }

    @Test
    @DisplayName("CT02 - Um parâmetro: ano válido (2025)")
    void testUmParametroAnoValido() {
        String resultado = calculadora.getCalendario("2025");
        assertNotNull(resultado);
        assertTrue(resultado.contains("2025"));
    }

    @Test
    @DisplayName("CT03 - Um parâmetro: ano mínimo válido (1)")
    void testUmParametroAnoMinimo() {
        String resultado = calculadora.getCalendario("1");
        assertNotNull(resultado);
        assertTrue(resultado.contains("1"));
    }

    @Test
    @DisplayName("CT04 - Um parâmetro: ano máximo válido (9999)")
    void testUmParametroAnoMaximo() {
        String resultado = calculadora.getCalendario("9999");
        assertNotNull(resultado);
        assertTrue(resultado.contains("9999"));
    }

    @Test
    @DisplayName("CT05 - Um parâmetro: ano inválido abaixo do mínimo (0)")
    void testUmParametroAnoInvalidoAbaixo() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.getCalendario("0"));
    }

    @Test
    @DisplayName("CT06 - Um parâmetro: ano inválido acima do máximo (10000)")
    void testUmParametroAnoInvalidoAcima() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.getCalendario("10000"));
    }
}