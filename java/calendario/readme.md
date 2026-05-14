# Especificação do Programa `cal`

No Linux existe um programa utilizado para visualizar um calendário em modo texto, chamado `cal`. Esse programa (comando) recebe como parâmetro: nenhum valor, um valor ou dois valores (caso sejam fornecidos mais parâmetros, os excedentes serão ignorados).

## Regras/Requisitos

- Se nenhum parâmetro for passado, o calendário do mês corrente (do ano atual) é exibido;
- Se apenas um parâmetro for passado, esse representará o ano, então todos os meses desse ano devem ser exibidos. O ano pode variar entre `1` e `9999`;
- Se dois parâmetros forem passados, o primeiro representará o mês e o segundo representará o ano. Então, será exibido o calendário do mês (passado por parâmetro) em questão. O mês varia entre `1` e `12`.

## Assinaturas do Método

Considere as seguintes assinaturas para o método:

```java
getCalendario()
getCalendario(String ano)
getCalendario(String mes, String ano)
getCalendario(String... variosParametros)
```

Atenção: perceba que getCalendario(25) não exibe o calendário do ano 2025, mas sim do ano 25.

## Informações Adicionais
O ano se inicia em 1º de janeiro.
A reforma no calendário Gregoriano (The Gregorian Reformation) ocorreu no dia 3 de setembro de 1752. Até o momento, a maioria dos países reconheceu a reforma realizada (embora poucos ainda não o tinham feito até os anos 90).
Com a reforma, dez dias foram eliminados do calendário a partir da data acima, exibindo um calendário diferente para o mês e ano em questão.
O papa Gregório XIII ordenou que o dia seguinte a 4 de setembro de 1752 passasse a ser o dia 15 de setembro.

## Atividade

Crie casos de teste utilizando o critério Particionamento de Equivalência para testar uma implementação baseada na descrição acima.