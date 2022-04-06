package sp.senac.br.petshop.model;

public class Funcionario extends Usuario{

    private boolean Admin;

    public Funcionario(){

    }

    public Funcionario(boolean Admin){
        super();
        this.Admin = Admin;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean Admin) {
        this.Admin = Admin;
    }

    
}
