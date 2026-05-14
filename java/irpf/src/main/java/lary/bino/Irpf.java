package lary.bino;

public class Irpf {

    public double calcular(double salario) {
        if (salario <= 0) {
            throw new IllegalArgumentException(
                "Salário deve ser maior que zero: " + salario
            );
        }

        if (salario <= 1903.98) {
            return 0.0;
        } else if (salario <= 2826.65) {
            return arredondar((salario * 0.075) - 142.80);
        } else if (salario <= 3751.05) {
            return arredondar((salario * 0.15) - 354.80);
        } else if (salario <= 4664.68) {
            return arredondar((salario * 0.225) - 636.13);
        } else {
            return arredondar((salario * 0.275) - 869.36);
        }
    }

    private double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
