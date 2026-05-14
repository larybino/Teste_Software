package lary.bino;

import java.time.LocalDate;

public class Calendario {

    public String getCalendario() {
        LocalDate hoje = LocalDate.now();
        return "Calendário de " + hoje.getMonthValue() + "/" + hoje.getYear();
    }

    public String getCalendario(String ano) {
        int anoInt = parseAno(ano);
        return "Janeiro Fevereiro Março Abril Maio Junho Julho Agosto Setembro Outubro Novembro Dezembro de " + anoInt;
    }

    public String getCalendario(String mes, String ano) {
        int mesInt = parseMes(mes);
        int anoInt = parseAno(ano);

        String[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                          "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        if (mesInt == 9 && anoInt == 1752) {
            return "Setembro de 1752 (Reforma Gregoriana - dias 5 a 14 omitidos)";
        }

        return meses[mesInt - 1] + " de " + anoInt;
    }

    public String getCalendario(String... params) {
        if (params == null || params.length == 0) {
            return getCalendario();
        } else if (params.length == 1) {
            return getCalendario(params[0]);
        } else {
            return getCalendario(params[0], params[1]);
        }
    }

    private int parseAno(String ano) {
        try {
            int anoInt = Integer.parseInt(ano.trim());
            if (anoInt < 1 || anoInt > 9999) {
                throw new IllegalArgumentException("Ano fora do intervalo permitido (1-9999): " + anoInt);
            }
            return anoInt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ano inválido: " + ano);
        }
    }

    private int parseMes(String mes) {
        try {
            int mesInt = Integer.parseInt(mes.trim());
            if (mesInt < 1 || mesInt > 12) {
                throw new IllegalArgumentException("Mês fora do intervalo permitido (1-12): " + mesInt);
            }
            return mesInt;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Mês inválido: " + mes);
        }
    }
}