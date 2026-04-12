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