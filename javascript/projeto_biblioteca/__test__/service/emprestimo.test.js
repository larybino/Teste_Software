const Livro = require("../../src/model/Livro")
const Usuario = require("../../src/model/Usuario")
const EmprestimoService = require("../../src/service/EmprestimoService")
const constantes = require("../../utils/Constantes") 
const msg = require("../../utils/msg");
const casos = require("../data/emprestimo.json")

describe("Emprestimo", () => {
    test('Teste usuário e livro válidos' , () => {
        //arange 
        const usuario= new Usuario({id: 1, nome: "Lary", ativo: true, emprestimosAtivos: 2, multaPendente: 10})
        const livro= new Livro({id:1,  titulo: "ACOTAR", disponivel:true})
        //act 
        const resultado = EmprestimoService.validarEmprestimo(usuario, livro)

        //assert
        expect(true).toBe(resultado)
    });

    test('Teste usuário inválido  e livro válido' , () => {
        //arange 
        const usuario= new Usuario({id: 1, nome: "Lary", ativo: false, emprestimosAtivos: 2, multaPendente: 10})
        const livro= new Livro({id:1,  titulo: "ACOTAR", disponivel:true})
        //act 
        const resultado = EmprestimoService.validarEmprestimo(usuario, livro)

        //assert
        expect(false).toBe(resultado)
    });


    test('Teste usuário inválido (emprestimosAtivos) e livro válido' , () => {
        //arange 
        const usuario= new Usuario({id: 1, nome: "Lary", ativo: true, emprestimosAtivos: constantes.USUARIO_LIMITE_EMPRESTIMOS + 1, multaPendente: 10})
        const livro= new Livro({id:1,  titulo: "ACOTAR", disponivel:true})
        //act 
        const resultado = EmprestimoService.validarEmprestimo(usuario, livro)

        //assert
        expect(false).toBe(resultado)
    });



    test('Teste usuário inválido (multaPendente) e livro válido' , () => {
        //arange 
        const usuario= new Usuario({id: 1, nome: "Lary", ativo: true, emprestimosAtivos: 2, multaPendente: constantes.USUARIO_VALOR_MULTA+1})
        const livro= new Livro({id:1,  titulo: "ACOTAR", disponivel:true})
        //act 
        const resultado = EmprestimoService.validarEmprestimo(usuario, livro)

        //assert
        expect(false).toBe(resultado)
    });


    test('Teste usuário válido e livro inválido' , () => {
        //arange 
        const usuario= new Usuario({id: 1, nome: "Lary", ativo: true, emprestimosAtivos: 2, multaPendente: 10})
        const livro= new Livro({id:1,  titulo: "ACOTAR", disponivel:false})

        //act 
        expect(() => EmprestimoService.validarEmprestimo(usuario, livro)).toThrow(msg.LIVRO_INDISPONÍVEL)

        //assert

    });

    test.each(casos)('$descricao', (caso)=>{
        const usuario= new Usuario({id: 1, nome: "Lary", ativo: caso.ativo, emprestimosAtivos: caso.emprestimosAtivos, multaPendente: caso.multaPendente})
        const livro= new Livro({id:1,  titulo: "ACOTAR", disponivel: caso.disponivel})
        if(caso.disponivel){
            const resultado = EmprestimoService.validarEmprestimo(usuario, livro)
            expect(caso.resultado).toBe(resultado)
        } else {
            expect(() => EmprestimoService.validarEmprestimo(usuario, livro)).toThrow(msg.LIVRO_INDISPONÍVEL)
        }
    })
})