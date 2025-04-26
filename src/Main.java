import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static BD banco = new BD();
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int opcMenu;
        do {
            try {
                banco.startConnection();
                //Atribui a váriavel "opcMenu" a opção escolhida nas funções respectivas de cada menu, além
                // de chamar a função que salva a lista de clientes no txt e tratar os erros;
                opcMenu = primeiroMenu();
                if (opcMenu == 1) {
                    //Atribui a opc a menuClientes;
                    opcMenu = menuClientes();
                    try {
                        if (opcMenu == 1) {
                            //Chama a função que inclui os clientes;
                            incluiCliente();
                        } else if (opcMenu == 2) {
                            //Chama a função que lista os clientes;
                            exibirClientes();
                        } else if (opcMenu == 3) {
                            //Chama a função que exclui o cliente;
                            excluiCliente();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                } else if (opcMenu == 2) {
                    //Atribui a opc a opção escolhida em menuPets;
                    opcMenu = menuPets();
                    try {
                        if (opcMenu == 1) {
                            //Chama a função que inlui os pets;
                            incluiPetsMENU();
                        } else if (opcMenu == 2) {
                            //Chama a função que exclui o pet;
                            excluiPet();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, insira um número.");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                } else if (opcMenu == 3) {
                    //Atribui a opc a opção esclhida em menuServicos;
                    opcMenu = menuServicos();
                    try {
                        if (opcMenu == 1) {
                            //Chama a função que lança o serviço;
                            lancaServico();
                        } else if (opcMenu == 2) {
                            //Chama a função que lança o pagamento;
                            lancaPagamento();
                        } else if (opcMenu == 3) {
                            //Chama a função que lista os saldos;
                            listaSaldo();
                        } else if (opcMenu == 4) {
                            //Chama a função que lista os saldos negativos;
                            listaSaldoNegativo();
                        }
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    //Se a opção for S ele exporta a lista de clientes para o txt e encerra o código;
                } else if (opcMenu == 'S') {
                    System.out.println("Fim do programa!\n");
                } else {
                    System.out.println("Opção não válida!!\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, tente novamente.");
                opcMenu = -1;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } while (opcMenu != 'S');
    }

    //Função que printa o primeiro Menu e armazena a entrada;
    public static int primeiroMenu() {
        String opc;
        Scanner input = new Scanner(System.in);
        int opc2;
        do {
            System.out.println("=============================================");
            System.out.println(" MENU PRINCIPAL ");
            System.out.println("=============================================");
            System.out.println("[1] Cadastro de Clientes");
            System.out.println("[2] Cadastro de Pets");
            System.out.println("[3] Serviços e controle de caixa");
            System.out.println("[S] Sair do programa");
            System.out.println("=============================================");
            System.out.print("Digite sua opção: ");

            opc = input.next().toUpperCase();

            if (opc.charAt(0) == 'S') {
                System.exit(0);
            }
            opc2 = Integer.parseInt(opc);
        } while (opc2 != 1 && opc2 != 2 && opc2 != 3);
        return opc2;
    }

    //Função que printa o Menu do Cliente e armazena a entrada;
    public static int menuClientes() {
        String opc;
        int opc2;
        Scanner input = new Scanner(System.in);
        System.out.println("=============================================");
        System.out.println(" CADASTRO DE CLIENTES ");
        System.out.println("=============================================");
        System.out.println("[1] Incluir Clientes");
        System.out.println("[2] Listar Clientes");
        System.out.println("[3] Excluir Clientes");
        System.out.println("[0] Volta ao menu principal");
        System.out.println("=============================================");
        System.out.print("Digite sua opção: ");

        opc = input.next().toUpperCase();
        opc2 = Integer.parseInt(opc);
        return opc2;
    }

    //Função que printa o Menu do Pet e armazena a entrada;
    public static int menuPets() {
        int opc2;
        String opc;
        Scanner input = new Scanner(System.in);
        System.out.println("=============================================");
        System.out.println(" CADASTRO DE PETS ");
        System.out.println("=============================================");
        System.out.println("[1] Incluir Pets");
        System.out.println("[2] Excluir Pets");
        System.out.println("[0] Volta ao menu principal");
        System.out.println("=============================================");
        System.out.print("Digite sua opção: ");

        opc = input.next().toUpperCase();
        opc2 = Integer.parseInt(opc);
        return opc2;
    }

    //Função que printa o Menu dos Servicos e armazena a entrada;
    public static int menuServicos() {
        int opc2;
        String opc;
        Scanner input = new Scanner(System.in);
        System.out.println("=============================================");
        System.out.println(" SERVIÇOS E CONTROLE DE CAIXA ");
        System.out.println("=============================================");
        System.out.println("[1] Lançar serviço");
        System.out.println("[2] Lançar pagamento");
        System.out.println("[3] Lista saldos");
        System.out.println("[4] Lista saldos NEGATIVOS");
        System.out.println("[0] Volta ao menu principal");
        System.out.println("=============================================");
        System.out.print("Digite sua opção: ");

        opc = input.next().toUpperCase();
        opc2 = Integer.parseInt(opc);
        return opc2;
    }

    public static void incluiCliente() throws IOException {
        // Inicializa o leitor de entrada
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PreparedStatement stmt = null;
        boolean sucesso = false; // Controla a repetição em caso de erro

        while (!sucesso) {
            try {
                // Pega os dados do Cliente
                System.out.println("Informe seu nome:");
                String nome = bf.readLine();
                System.out.println("Informe seu endereço:");
                String endereco = bf.readLine();
                System.out.println("Informe seu telefone:");
                String telefone = bf.readLine();
                double saldo = 0.0;

                // Comando SQL para incluir cliente
                String sql = "INSERT INTO petclub.clientes (nome, endereco, telefone, saldo) VALUES (?, ?, ?, ?)";
                stmt = banco.getConexao().prepareStatement(sql);

                stmt.setString(1, nome);
                stmt.setString(2, endereco);
                stmt.setString(3, telefone);
                stmt.setDouble(4, saldo);

                stmt.execute();
                System.out.println("Cliente incluído com sucesso!");

                int idCliente = buscaIdCliente(nome); // Busca o ID do cliente cadastrado
                sucesso = true; // Marca a operação como bem-sucedida

                // Pergunta sobre cadastro do pet
                System.out.println("Deseja incluir seu pet? \n1 - Sim \n2 - Não");
                String opcP = bf.readLine();

                if (opcP.equals("1")) {
                    System.out.println("Informe o nome do seu Pet:");
                    String nomeP = bf.readLine();
                    System.out.println("Informe a raça do seu Pet:");
                    String racaP = bf.readLine();
                    System.out.println("Informe a idade do seu Pet:");
                    int idadeP = Integer.parseInt(bf.readLine());
                    System.out.println("Informe o peso do seu Pet:");
                    double pesoP = Double.parseDouble(bf.readLine());

                    // Comando SQL para incluir pet
                    sql = "INSERT INTO petclub.pets (ID_CLIENTE, NOME, IDADE, RACA, peso) VALUES (?, ?, ?, ?, ?)";
                    stmt = banco.getConexao().prepareStatement(sql);

                    stmt.setInt(1, idCliente);
                    stmt.setString(2, nomeP);
                    stmt.setInt(3, idadeP);
                    stmt.setString(4, racaP);
                    stmt.setDouble(5, pesoP);

                    stmt.execute();
                    System.out.println("Pet cadastrado com sucesso!");
                } else if (opcP.equals("2")) {
                    System.out.println("Cliente cadastrado com sucesso!\n");
                } else {
                    System.out.println("Opção não válida. Cadastro concluído sem pet.");
                }

            } catch (SQLException e) {
                System.out.println("Erro no acesso ao BD: " + e.getMessage());
                System.out.println("Tente novamente.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                System.out.println("Tente novamente.");
            } finally {
                // Fecha o PreparedStatement
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        System.out.println("Erro ao fechar o PreparedStatement: " + e.getMessage());
                    }
                }
            }
        }
    }


    //Menu que inclui Pets
    public static void incluiPetsMENU() throws IOException {
        String nome, raca;
        int idade;
        double peso;

        //Seleciona o Cliente que recebera os pets
        System.out.println("Escolha o cliente: ");
        listarOpcoesClientes();
        int opcao = Integer.parseInt(bf.readLine());

        int id = buscaClientePorId(opcao);

        //Imputa os dados do Pet
        if (id > 0) {
            System.out.println("Informe o nome do seu Pet:");
            nome = bf.readLine();
            System.out.println("Informe a raca do seu Pet:");
            raca = bf.readLine();
            System.out.println("Informe a idade do seu Pet:");
            idade = Integer.parseInt(bf.readLine());
            System.out.println("Informe o peso do seu Pet:");
            peso = Double.parseDouble(bf.readLine());

            try {
                String sql = """
                INSERT INTO petclub.pets (ID_CLIENTE, NOME, IDADE, RACA, PESO) 
                VALUES (?, ?, ?, ?, ?)
            """;

                try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql)) {
                    stmt.setInt(1, id);
                    stmt.setString(2, nome);
                    stmt.setInt(3, idade);
                    stmt.setString(4, raca);
                    stmt.setDouble(5, peso);

                    stmt.executeUpdate();
                    System.out.println("Pet cadastrado com sucesso!");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao cadastrar o pet: " + e.getMessage());
            }
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }


    //Função responsável por Exibir os Clientes na Tela
    public static void exibirClientes() {
        String sql = """
                    SELECT 
                        c.ID AS cliente_id, 
                        c.nome AS cliente_nome, 
                        c.endereco, 
                        c.telefone, 
                        c.saldo, 
                        p.ID AS pet_id, 
                        p.nome AS pet_nome, 
                        p.idade AS pet_idade, 
                        p.raca AS pet_raca, 
                        p.Peso AS pet_peso
                    FROM 
                        petclub.clientes c
                    LEFT JOIN 
                        petclub.pets p
                    ON 
                        c.ID = p.ID_CLIENTE;
                """;

        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Percorre o resultado
            while (rs.next()) {
                // Dados do cliente
                String clienteNome = rs.getString("cliente_nome");
                String endereco = rs.getString("endereco");
                String telefone = rs.getString("telefone");
                double saldo = rs.getDouble("saldo");

                // Dados do pet
                int petId = rs.getInt("pet_id");
                String petNome = rs.getString("pet_nome");
                int petIdade = rs.getInt("pet_idade");
                String petRaca = rs.getString("pet_raca");
                double petPeso = rs.getDouble("pet_peso");

                // Exibe informações do cliente
                System.out.println("Nome: " + clienteNome);
                System.out.println("Endereço: " + endereco);
                System.out.println("Telefone: " + telefone);
                System.out.println("Saldo: " + saldo);

                // Exibe informações do pet (se existir)
                if (petId > 0) { // Verifica se há um pet associado
                    System.out.println("    Nome do Pet: " + petNome);
                    System.out.println("    Idade: " + petIdade);
                    System.out.println("    Raça: " + petRaca);
                    System.out.println("    Peso: " + petPeso);
                } else {
                    System.out.println("    Este cliente não possui pets.");
                }

                System.out.println("-------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }

    public static int buscaClientePorId(int opcao) throws IOException {
        String sql = "SELECT ID FROM petclub.clientes WHERE ID = ?";

        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql)) {
            stmt.setInt(1, opcao);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID"); // Retorna o ID do cliente encontrado
                } else {
                    System.out.println("Cliente não encontrado. Tente novamente.");

                    // Solicitar um novo ID ao usuário
                    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("Informe novamente o ID do cliente:");
                    int novoId = Integer.parseInt(bf.readLine());

                    // Chama a função novamente com o novo ID
                    return buscaClientePorId(novoId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            return -1;
        }
    }


    //Funcao que mostra as opções de clientes que o Usuário pode escolher
    public static void listarOpcoesClientes() throws IOException {
        String sql = """
                SELECT 
                c.ID AS cliente_id, 
                c.nome AS cliente_nome
                FROM 
                        petclub.clientes c
                """;

        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            // Percorre o resultado
            while (rs.next()) {
                // Dados do cliente
                int clienteId = rs.getInt("cliente_id");
                String clienteNome = rs.getString("cliente_nome");
                // Exibe informações do cliente
                System.out.println(clienteId + " - " + clienteNome);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Funcao responsável pela exclusão de Clientes
    public static void excluiCliente() throws IOException {
        System.out.println("Escolha o cliente que deseja excluir: ");
        listarOpcoesClientes();
        int opcao = Integer.parseInt(bf.readLine());
        int id = buscaClientePorId(opcao);

        // Trata o caso de o cliente não existir
        if (id > 0) {
            String sqlExcluirPets = "DELETE FROM petclub.pets WHERE ID_CLIENTE = ?";
            String sqlExcluirCliente = "DELETE FROM petclub.clientes WHERE ID = ?";

            try (PreparedStatement stmtPets = banco.getConexao().prepareStatement(sqlExcluirPets);
                 PreparedStatement stmtCliente = banco.getConexao().prepareStatement(sqlExcluirCliente)) {

                // Exclui os pets associados ao cliente
                stmtPets.setInt(1, id);
                stmtPets.executeUpdate();

                // Exclui o cliente
                stmtCliente.setInt(1, id);
                int linhasAfetadas = stmtCliente.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Cliente e seus pets excluídos com sucesso.");
                } else {
                    System.out.println("Cliente com ID " + id + " não encontrado.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao excluir o cliente: " + e.getMessage());
            }
        }
    }

    //Funcao responsável por selecionar um pet específico de um Cliente
    public static int selecionaPetsCliente() throws IOException {
        System.out.println("Escolha o cliente que deseja ver os pets: ");
        listarOpcoesClientes();
        int opcao = Integer.parseInt(bf.readLine());

        int id = buscaClientePorId(opcao);

        System.out.print("Digite o ID do pet que deseja selecionar: \n");
        listaPets(id);
        int petIdEscolhido = Integer.parseInt(bf.readLine());

        String verificarPetSQL = "SELECT ID FROM petclub.pets WHERE ID = ?";
        try (PreparedStatement verificarStmt = banco.getConexao().prepareStatement(verificarPetSQL)) {
            verificarStmt.setInt(1, petIdEscolhido);

            try (ResultSet verificarRs = verificarStmt.executeQuery()) {
                if (verificarRs.next()) {
                    System.out.println("Pet selecionado com sucesso!");
                    return petIdEscolhido; // Retorna o ID do pet selecionado
                } else {
                    System.out.println("Nenhum pet encontrado com o ID informado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
        return 0;
    }


        //Função responável por excluir Pets
    public static void excluiPet() throws IOException {
        int id = selecionaPetsCliente();

        if (id > 0) {
            String sql = "DELETE FROM petclub.pets WHERE ID = ?";

            try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql)) {
                // Define o ID do pet como parâmetro
                stmt.setInt(1, id);

                // Executa a exclusão
                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Pet com ID " + id + " excluído com sucesso.");
                } else {
                    System.out.println("Pet com ID " + id + " não encontrado.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao excluir o pet: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum pet foi selecionado para exclusão.");
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------

    //Função responsável por imputar os Serviços salvos no arquivo
    public static void exibeServicos() throws IOException {
        String sql = "SELECT ID, descricao, preco FROM servicos";

        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            // Percorre o resultado
            while (rs.next()) {
                // Dados do cliente
                int servicoID = rs.getInt("ID");
                String servicoDescricao = rs.getString("DESCRICAO");
                float servicoPreco = rs.getInt("PRECO");
                // Exibe informações do cliente
                System.out.println(servicoID + " - " + servicoDescricao + " - " + servicoPreco);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int escolheServicos(int ID) throws IOException {
        // POSSIBILITAR O CLIENTE DE SELECIONAR O SERVIÇO
        String sql = "SELECT ID FROM servicos WHERE ID = ?";

        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql)) {
            // Configura o parâmetro da consulta
            stmt.setInt(1, ID);

            // Executa a consulta
            ResultSet r = stmt.executeQuery();
            if (r.next()) {
                return r.getInt("ID"); // Retorna o ID encontrado na tabela
            } else {
                System.out.println("Serviço não encontrado. Tente novamente.");

                // Solicitar um novo ID ao usuário
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Informe o ID do serviço novamente:");
                int novoID = Integer.parseInt(bf.readLine());

                // Chama novamente a função com o novo ID
                return escolheServicos(novoID);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            throw new RuntimeException(e); // Re-throw para garantir que o erro seja propagado
        }
    }


    //Função que lista todos os serviços, deixa o Cliente escolher
    public static void lancaServico() throws IOException {
        //Seleciona o Cliente que recebera o servico
        System.out.println("Escolha o cliente: ");
        listarOpcoesClientes();
        int opcao = Integer.parseInt(bf.readLine());

        int id = buscaClientePorId(opcao);

        System.out.println("Escolha o serviço: ");
        exibeServicos();
        int opcao_servico = Integer.parseInt(bf.readLine());

        int id_servico = escolheServicos(opcao_servico);

        float valor_servico = buscaValorServico(id_servico);

        String sql = "UPDATE clientes SET saldo = saldo - ? WHERE id = ?";

        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql)) {

            // Configurando os valores no PreparedStatement
            stmt.setDouble(1, valor_servico);
            stmt.setInt(2, id);

            // Executando o comando SQL
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Saldo atualizado com sucesso para o cliente com ID: " + id);
            } else {
                System.out.println("Nenhum cliente encontrado com o ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar saldo: " + e.getMessage());
        }
    }

    public static float buscaValorServico(int id_servico) throws IOException {

        String sql = "SELECT preco FROM servicos WHERE ID = ?";
        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql)) {
            // Configura o parâmetro da consulta
            stmt.setInt(1, id_servico); // Ajustado para setInt, caso ID seja um inteiro

            // Executa a consulta
            ResultSet r = stmt.executeQuery();
            if (r.next()) {
                return r.getFloat("PRECO"); // Retorna o preco encontrado na tabela
            } else {
                System.out.println("Serviço não encontrado.");
                return -1; // Retorna -1 se o serviço não for encontrado
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            throw new RuntimeException(e); // Re-throw para garantir que o erro seja propagado
        }
    }

    //Função que faz o debito do saldo do Cliente
    public static void lancaPagamento() throws IOException {
        System.out.println("Escolha o cliente: ");
        listarOpcoesClientes();
        int opcao = Integer.parseInt(bf.readLine());

        int id = buscaClientePorId(opcao);
        System.out.println("Insira o valor do pagamento que deseja fazer: ");
        float valor = Float.parseFloat(bf.readLine());

        String sql = "UPDATE clientes SET saldo = saldo + ? WHERE id = ?";

        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql)) {

            // Configurando os valores no PreparedStatement
            stmt.setDouble(1, valor);
            stmt.setInt(2, id);

            // Executando o comando SQL
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Saldo atualizado com sucesso para o cliente com ID: " + id);
            } else {
                System.out.println("Nenhum cliente encontrado com o ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar saldo: " + e.getMessage());
        }
    }

    //Funcao responsável por listar os saldos dos Clientes
    public static void listaSaldo(){
        //FAZER SELECTS DOS SALDOS DO CLIENTES COM CAMPO DO NOME E TELEFONE E SALDO
        String sql = "SELECT NOME, TELEFONE, SALDO FROM Clientes";

        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            // Percorre o resultado
            while (rs.next()) {
                // Dados do cliente
                String clienteNome = rs.getString("NOME");
                int clienteTelefone = rs.getInt("TELEFONE");
                float clienteSaldo = rs.getInt("SALDO");
                // Exibe informações do cliente
                System.out.println(clienteNome + " - " + clienteTelefone + " - " + clienteSaldo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Função que lista os Clientes com saldo negativo
    public static void listaSaldoNegativo(){
        //SELECT SEMELHANTE AO DE CIMA, MAS DOS SALDOS NEGATIVOS

        String sql = "SELECT NOME, TELEFONE, SALDO FROM Clientes WHERE saldo < 0";

        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            // Percorre o resultado
            while (rs.next()) {
                // Dados do cliente
                String clienteNome = rs.getString("NOME");
                int clienteTelefone = rs.getInt("TELEFONE");
                float clienteSaldo = rs.getInt("SALDO");
                // Exibe informações do cliente
                System.out.println(clienteNome + " - " + clienteTelefone + " - " + clienteSaldo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int buscaIdCliente(String nome) {
        String sql = "SELECT ID FROM Clientes WHERE nome = ?";
        try (PreparedStatement stmt = banco.getConexao().prepareStatement(sql)) {
            // Configura o parâmetro da consulta
            stmt.setString(1, nome);

            // Executa a consulta
            ResultSet r = stmt.executeQuery();
            if (r.next()) {
                return r.getInt("ID"); // Retorna o ID do cliente
            } else {
                System.out.println("Cliente não encontrado.");
                return -1; // Retorna -1 se o cliente não foi encontrado
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void listaPets(int id) {
        boolean encontrouPets = false;
        String listarPetsSQL = "SELECT ID, NOME FROM petclub.pets WHERE ID_CLIENTE = ?";

        try (PreparedStatement stmt = banco.getConexao().prepareStatement(listarPetsSQL)) {
            // Define o valor do parâmetro da consulta
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                // Percorre o resultado
                while (rs.next()) {
                    encontrouPets = true;
                    // Dados do pet
                    int petID = rs.getInt("ID");
                    String petNome = rs.getString("NOME");
                    // Exibe informações do pet
                    System.out.println(petID + " - " + petNome);
                }

                if (!encontrouPets) {
                    System.out.println("Nenhum pet cadastrado para este cliente.");
                    primeiroMenu();
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }
}
