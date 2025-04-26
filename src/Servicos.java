import java.util.ArrayList;

public class Servicos {
    private String nome, preco;

        public Servicos(String nome, String preco) {
            this.nome = nome;
            this.preco = preco;
        }

        public String getNome() {
            return nome;
        }

        public String getPreco() {
            return preco;
        }
}
