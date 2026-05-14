import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lary.bino.Irpf;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes - IRPF (Particionamento de Equivalência)")
class IrpfTest {

    private Irpf irpf;

    @BeforeEach
    void setUp() {
        irpf = new Irpf();
    }

    @Test
    @DisplayName("CT01 - Isento: salário até R$ 1.903,98")
    void testIsento() {
        double resultado = irpf.calcular(1000.00);
        assertEquals(0.00, resultado, 0.01);
    }

    @Test
    @DisplayName("CT02 - Alíquota 7,5%: salário de R$ 2.500,00")
    void testAliquota7_5() {
        double resultado = irpf.calcular(2500.00);
        assertEquals(44.70, resultado, 0.01);
    }

    @Test
    @DisplayName("CT03 - Alíquota 15%: salário de R$ 3.000,00")
    void testAliquota15() {
        double resultado = irpf.calcular(3000.00);
        assertEquals(95.20, resultado, 0.01);
    }

    @Test
    @DisplayName("CT04 - Alíquota 22,5%: salário de R$ 4.000,00")
    void testAliquota22_5() {
        double resultado = irpf.calcular(4000.00);
        assertEquals(263.87, resultado, 0.01);
    }

    @Test
    @DisplayName("CT05 - Alíquota 27,5%: salário de R$ 7.000,00")
    void testAliquota27_5() {
        double resultado = irpf.calcular(7000.00);
        assertEquals(1055.64, resultado, 0.01);
    }

}