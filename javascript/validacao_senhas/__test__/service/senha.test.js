const Senha = require("../../src/model/Senha");
const SenhaService = require("../../src/service/SenhaService");

test('Mínimo de 8 caracteres' , () => {
    //arange 
    const senha = new Senha({senha: "Ab1@"}).senha
    //act 
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(false).toBe(resultado)
});

test('Pelo menos uma letra maiúscula.' , () => {
    //arange
    const senha = new Senha({senha: "12345678"}).senha
    //act 
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(false).toBe(resultado)
});

test('Pelo menos uma letra minúscula.' , () => {
    //arange
    const senha = new Senha({senha: "12345678A"}).senha
    //act 
    const resultado = SenhaService.validarSenha(senha)  
    //assert
    expect(false).toBe(resultado)
});

test('Pelo menos um número.' , () => {
    //arange
    const senha = new Senha({senha: "abcdefghA"}).senha
    //act 
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(false).toBe(resultado)
});

test('Pelo menos um caractere especial (!@#$%^&*).' , () => {
    //arange
    const senha = new Senha({senha: "abcdefghA1"}).senha
    //act
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(false).toBe(resultado)
});

test('Não deve conter espaços em branco.' , () => {
    //arange
    const senha = new Senha({senha: "abc defgA1@"}).senha
    //act
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(false).toBe(resultado)
});

test('Senha válida' , () => {
    //arange
    const senha = new Senha({senha: "Abcdefg1@"}).senha
    //act
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(true).toBe(resultado)
});
