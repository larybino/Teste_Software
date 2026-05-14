import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lary.bino.Calendario;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes - Calendario (Particionamento de Equivalência)")
class CalendarioTest {

    private Calendario calendario;

    @BeforeEach
    void setUp() {
        calendario = new Calendario();
    }

    @Test
    @DisplayName("CT01 - Nenhum parâmetro: exibe mês corrente")
    void testSemParametros() {
        String resultado = calendario.getCalendario();
        LocalDate hoje = LocalDate.now();
        assertNotNull(resultado);
        assertTrue(resultado.contains(String.valueOf(hoje.getYear())));
    }

    @Test
    @DisplayName("CT02 - Um parâmetro: ano válido (2025)")
    void testUmParametroAnoValido() {
        String resultado = calendario.getCalendario("2025");
        assertNotNull(resultado);
        assertTrue(resultado.contains("2025"));
    }

    @Test
    @DisplayName("CT03 - Um parâmetro: ano mínimo válido (1)")
    void testUmParametroAnoMinimo() {
        String resultado = calendario.getCalendario("1");
        assertNotNull(resultado);
        assertTrue(resultado.contains("1"));
    }

    @Test
    @DisplayName("CT04 - Um parâmetro: ano máximo válido (9999)")
    void testUmParametroAnoMaximo() {
        String resultado = calendario.getCalendario("9999");
        assertNotNull(resultado);
        assertTrue(resultado.contains("9999"));
    }

    @Test
    @DisplayName("CT05 - Um parâmetro: ano inválido abaixo do mínimo (0)")
    void testUmParametroAnoInvalidoAbaixo() {
        assertThrows(IllegalArgumentException.class, () -> calendario.getCalendario("0"));
    }

    @Test
    @DisplayName("CT06 - Um parâmetro: ano inválido acima do máximo (10000)")
    void testUmParametroAnoInvalidoAcima() {
        assertThrows(IllegalArgumentException.class, () -> calendario.getCalendario("10000"));
    }

    @Test
    @DisplayName("CT07 - Dois parâmetros: mês e ano válidos (06/2025)")
    void testDoisParametrosMesAnoValidos() {
        String resultado = calendario.getCalendario("6", "2025");
        assertNotNull(resultado);
        assertTrue(resultado.contains("2025"));
    }

    @Test
    @DisplayName("CT08 - Dois parâmetros: mês mínimo válido (01/2025)")
    void testDoisParametrosMesMinimo() {
        String resultado = calendario.getCalendario("1", "2025");
        assertNotNull(resultado);
        assertTrue(resultado.contains("2025"));
    }

    @Test
    @DisplayName("CT09 - Dois parâmetros: mês máximo válido (12/2025)")
    void testDoisParametrosMesMaximo() {
        String resultado = calendario.getCalendario("12", "2025");
        assertNotNull(resultado);
        assertTrue(resultado.contains("2025"));
    }

    @Test
    @DisplayName("CT10 - Dois parâmetros: mês inválido abaixo do mínimo (0)")
    void testDoisParametrosMesInvalidoAbaixo() {
        assertThrows(IllegalArgumentException.class,
            () -> calendario.getCalendario("0", "2025"));
    }

    @Test
    @DisplayName("CT11 - Dois parâmetros: mês inválido acima do máximo (13)")
    void testDoisParametrosMesInvalidoAcima() {
        assertThrows(IllegalArgumentException.class,
            () -> calendario.getCalendario("13", "2025"));
    }

    @Test
    @DisplayName("CT12 - Dois parâmetros: ano inválido abaixo do mínimo (0)")
    void testDoisParametrosAnoInvalidoAbaixo() {
        assertThrows(IllegalArgumentException.class,
            () -> calendario.getCalendario("6", "0"));
    }

    @Test
    @DisplayName("CT13 - Dois parâmetros: ano inválido acima do máximo (10000)")
    void testDoisParametrosAnoInvalidoAcima() {
        assertThrows(IllegalArgumentException.class,
            () -> calendario.getCalendario("6", "10000"));
    }

    @Test
    @DisplayName("CT14 - Ano anterior à reforma: setembro de 1751 (calendário normal)")
    void testAnteriorReformaGregoriana() {
        String resultado = calendario.getCalendario("9", "1751");
        assertNotNull(resultado);
        assertTrue(resultado.contains("1751"));
    }

    @Test
    @DisplayName("CT15 - Ano posterior à reforma: setembro de 1753 (calendário normal)")
    void testPosteriorReformaGregoriana() {
        String resultado = calendario.getCalendario("9", "1753");
        assertNotNull(resultado);
        assertTrue(resultado.contains("1753"));
    }

    @Test
    @DisplayName("CT16 - Mais de dois parâmetros: excedentes ignorados")
    void testVarargsMaisDeDoisParametros() {
        String resultado = calendario.getCalendario("6", "2025", "99");
        assertNotNull(resultado);
        assertTrue(resultado.contains("2025"));
    }
}