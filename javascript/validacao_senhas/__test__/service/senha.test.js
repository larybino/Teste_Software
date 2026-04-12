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