# Validador de Senhas — TDD

## Descrição
Sistema de validação de senhas desenvolvido com a metodologia TDD (Test-Driven Development).

## Tecnologias
- Node.js
- Jest

## Critérios de validação
- Mínimo de 8 caracteres
- Pelo menos uma letra maiúscula
- Pelo menos uma letra minúscula
- Pelo menos um número
- Pelo menos um caractere especial (!@#$%^&*)
- Não pode conter espaços em branco

## Processo de desenvolvimento

### 1. Fase Red (testes falhando)
Escrita de todos os testes antes de qualquer implementação. Como a função `validarSenha` ainda não existia, todos os testes falharam — esse é o comportamento esperado no TDD.

Foram criados 7 testes:
- 6 testando cada critério individualmente, esperando `false`
- 1 testando uma senha válida com todos os critérios, esperando `true`

### 2. Criação da Model e Service
Com os testes escritos, foi criada a estrutura do projeto:
- `Senha.js` — model que representa o objeto senha
- `SenhaService.js` — service com a função `validarSenha` contendo as regras de validação

### 3. Fase Green (testes passando)
Após implementar a função `validarSenha` no service, os testes foram rodados novamente e todos passaram.

