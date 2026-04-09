const Livro = require("../../src/model/Livro")
const Usuario = require("../../src/model/Usuario")
const EmprestimoService = require("../../src/service/EmprestimoService")


test('Teste usuário e livro válidos' , () => {
    //arange 
    const usuario= new Usuario({id: 1, nome: "Lary", ativo: true, emprestimosAtivos: 2, multaPendente: 10})
    const livro= new Livro({id:1,  titulo: "ACOTAR", disponivel:true})
    //act 
    const resultado = EmprestimoService.validarEmprestimo(usuario, livro)

    //assert
    expect(true).toBe(resultado)
})