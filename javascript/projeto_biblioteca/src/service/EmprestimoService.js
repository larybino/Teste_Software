const constantes = require("../../utils/Constantes");

class EmprestimoService {
    static validarEmprestimo(usuario, livro){
        return this.validaUsuario(usuario) && this.validaLivro(livro);
    }
    static validaUsuario(usuario){
        if(!usuario.ativo )return false;
        if(usuario.emprestimosAtivos>=constantes.USUARIO_LIMITE_EMPRESTIMOS)return false;
        if(usuario.multaPendente>=constantes.USUARIO_VALOR_MULTA)return false;
        return true;
    }
    static validaLivro(livro){
        if(!livro.disponivel)return false;
        return true;
    }
}

module.exports = EmprestimoService;