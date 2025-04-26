import java.util.ArrayList;
import java.util.List;

public class Pets extends ArrayList<Pets> {
    String nome;
    String raca;
    int idade;
    double peso;

    public Pets(String nome, String raca, int idade, double peso) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Pets{" +
                "nome='" + nome + '\'' +
                ", raca='" + raca + '\'' +
                ", idade=" + idade +
                ", peso=" + peso +
                '}';
    }

    @Override
    public List<Pets> reversed() {
        return super.reversed();
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getRaca() {
        return raca;
    }

    public double getPeso() {
        return peso;
    }

}