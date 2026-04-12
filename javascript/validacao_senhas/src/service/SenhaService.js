class SenhaService{
    static validarSenha(senha){
        if(senha.length < 8) return false
        if(!/[A-Z]/.test(senha)) return false
        if(!/[a-z]/.test(senha)) return false
        if(!/[0-9]/.test(senha)) return false
        if(!/[!@#$%^&*]/.test(senha)) return false 
        if(/\s/.test(senha)) return false 
        return true
    }
}
module.exports = SenhaService;