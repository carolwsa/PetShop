import java.util.ArrayList;

public class Clientes {
    String nome;
    String endereco;
    String telefone;
    double saldo;
    private ArrayList<Pets> pets;

    public Clientes(String nome, String endereco, String telefone, double saldo, ArrayList<Pets> pets) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.saldo = saldo;
        this.pets = pets;
    }

    public Clientes(String nome, String endereco, String telefone, double saldo) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.saldo = saldo;
    }

    public Clientes() {

    }

    @Override
    public String toString() {
        return "Clientes{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", saldo=" + saldo +
                ", pets=" + pets +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public ArrayList<Pets> getPets() {
        return pets;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setPets(ArrayList<Pets> pets) {
        this.pets = pets;
    }

    public void addPet(Pets pet) {
        this.pets.add(pet);
    }

}
