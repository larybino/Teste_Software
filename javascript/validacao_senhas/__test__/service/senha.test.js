test('Mínimo de 8 caracteres' , () => {
    //arange 
    const senha = "1234567"
    //act 
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(false).toBe(resultado)
});

test('Pelo menos uma letra maiúscula.' , () => {
    //arange
    const senha = "12345678"
    //act 
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(false).toBe(resultado)
});

test('Pelo menos uma letra minúscula.' , () => {
    //arange
    const senha = "12345678A"
    //act 
    const resultado = SenhaService.validarSenha(senha)  
    //assert
    expect(false).toBe(resultado)
});

test('Pelo menos um número.' , () => {
    //arange
    const senha = "abcdefghA"
    //act 
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(false).toBe(resultado)
});

test('Pelo menos um caractere especial (!@#$%^&*).' , () => {
    //arange
    const senha = "abcdefghA1" 
    //act
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(false).toBe(resultado)
});

test('Não deve conter espaços em branco.' , () => {
    //arange
    const senha = "abc defgA1@"
    //act
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(false).toBe(resultado)
});

test('Senha válida' , () => {
    //arange
    const senha = "Abcdefg1@"
    //act
    const resultado = SenhaService.validarSenha(senha)
    //assert
    expect(true).toBe(resultado)
});
