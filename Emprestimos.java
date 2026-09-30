import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Emprestimos {

    static String[] tipoObjeto = new String[10]; // nome
    static String[][] pessoas = new String[3][4]; // nome, email, senha, dataEntrada
    static String[][] objetos = new String[3][4]; // nome, tipo, responsavel, situacao
    static String[][] manutencoes = new String[3][5]; // objeto, descricao, data_entrada, data_saida, estado
    static String[][] emprestimos = new String[3][5]; // responsavel, objeto, situacao, data_emprestimo, data_devolucao

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarVetores();
        menuInicial();
    }

    static void inicializarVetores() {
        int i, j;
        for (i = 0; i < tipoObjeto.length; i++)
            tipoObjeto[i] = "";
        for (i = 0; i < pessoas.length; i++)
            for (j = 0; j < pessoas[i].length; j++)
                pessoas[i][j] = "";
        for (i = 0; i < objetos.length; i++)
            for (j = 0; j < objetos[i].length; j++)
                objetos[i][j] = "";
        for (i = 0; i < manutencoes.length; i++)
            for (j = 0; j < manutencoes[i].length; j++)
                manutencoes[i][j] = "";
        for (i = 0; i < emprestimos.length; i++)
            for (j = 0; j < emprestimos[i].length; j++)
                emprestimos[i][j] = "";
    }

    // Verifica posição livre em vetor
    static int verificaPosicaoLivreNoVetor(String vetor[]) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i].equals(""))
                return i;
        }
        return -1;
    }

    // Verifica posição livre em matriz
    static int verificaPosicaoLivreNaMatriz(String matriz[][]) {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0] == null || matriz[i][0].isEmpty()) {
                return i;
            }
        }
        return -1;
    }

    // menu inicial
    static void menuInicial() {
        int opcao;

        do {
            System.out.println("\n===== MENU INICIAL =====");
            System.out.println("1 - Pessoa");
            System.out.println("2 - Tipo Objeto");
            System.out.println("3 - Objeto");
            System.out.println("4 - Manutenção");
            System.out.println("5 - Empréstimo");
            System.out.println("6 - Sair");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    subMenu("pessoa");
                    break;
                case 2:
                    subMenu("tipoObjeto");
                    break;
                case 3:
                    subMenu("objeto");
                    break;
                case 4:
                    subMenu("manutencao");
                    break;
                case 5:
                    subMenu("emprestimo");
                    break;
                case 6:
                    System.out.println("\nSaindo do sistema... Até logo!\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n ==== Opção inválida! Tente novamente. ====");
                    System.out.println("\n[ OK ] - PRESS ENTER\n");
                    scanner.nextLine();
                    scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
                    break;
            }
        } while (opcao != 6);
        scanner.close();
    }

    // o sub menu presente em todas as opcoes do menu inicial
    static void subMenu(String tipo) {
        int opcao;

        do {
            System.out.println("\n===== SUBMENU DE " + tipo.toUpperCase() + " =====");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Listar");
            System.out.println("5 - Voltar");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    cadastrar(tipo);
                    break;
                case 2:
                    alterar(tipo);
                    break;
                case 3:
                    excluir(tipo);
                    break;
                case 4:
                    listar(tipo);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\n ==== Opção inválida! Tente novamente. ====");
                    System.out.println("\n[ OK ] - PRESS ENTER\n");
                    scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
                    break;
            }

        } while (opcao != 5);
    }

    static void cadastrar(String tipo) {
        // vai verificar qual tipo que quer cadastrar e chamar a funcao adequada
        System.out.println("\nCadastrando " + tipo + "...\n");
        if (tipo.equals("pessoa")) {
            cadastrarPessoa();
        } else if (tipo.equals("tipoObjeto")) {
            cadastrarTipoObjeto();
        } else if (tipo.equals("objeto")) {
            cadastrarObjeto();
        } else if (tipo.equals("manutencao")) {
            cadastrarManutencao();
        } else if (tipo.equals("emprestimo")) {
            cadastrarEmprestimo();
        }
    }

    static void alterar(String tipo) {
        // vai verificar qual tipo que quer cadastrar e chamar a funcao adequada
        System.out.println("\nAlterando " + tipo + "...\n");
        if (tipo.equals("pessoa")) {
            alterarPessoa();
        } else if (tipo.equals("tipoObjeto")) {
            alterarTipoObjeto();
        } else if (tipo.equals("objeto")) {
            alterarObjeto();
        } else if (tipo.equals("manutencao")) {
            alterarManutencao();
        } else if (tipo.equals("emprestimo")) {
            alterarEmprestimo();
        }
    }

    static void excluir(String tipo) {
        // vai verificar qual tipo que quer excluir e chamar a funcao adequada
        System.out.println("\nExcluir " + tipo + "...\n");
        if (tipo.equals("pessoa")) {
            excluirPessoa();
        } else if (tipo.equals("tipoObjeto")) {
            excluirTipoObjeto();
        } else if (tipo.equals("objeto")) {
            excluirObjeto();
        } else if (tipo.equals("manutencao")) {
            excluirManutencao();
        } else if (tipo.equals("emprestimo")) {
            excluirEmprestimo();
        }
    }

    static void listar(String tipo) {
        System.out.println("\nListando " + tipo + "...\n");
        if (tipo.equals("pessoa")) {
            listarPessoas();
        } else if (tipo.equals("tipoObjeto")) {
            listarTiposObjetos();
        } else if (tipo.equals("objeto")) {
            listarObjetos();
        } else if (tipo.equals("manutencao")) {
            listarManutencoes();
        } else if (tipo.equals("emprestimo")) {
            listarEmprestimos();
        }
    }

    // ==========================================================
    // FUNÇÕES DE CADASTRO
    // ==========================================================
    // PESSOA
    static void cadastrarPessoa() {
        int posicao;
        String nomeDigitado, emailDigitado, senhaDigitado;
        posicao = verificaPosicaoLivreNaMatriz(pessoas);

        if (posicao == -1) {
            System.out.print("Armazenamento de PESSOAS cheio! Não é possível cadastrar mais pessoas.");
        } else {
            System.out.print("Nome -> ");
            nomeDigitado = scanner.nextLine().toUpperCase();
            System.out.print("Email -> ");
            emailDigitado = scanner.nextLine().toUpperCase();
            System.out.print("Senha -> ");
            senhaDigitado = scanner.nextLine().toUpperCase();

            if ((nomeDigitado != null && !nomeDigitado.isBlank()) && (emailDigitado != null && !emailDigitado.isBlank())
                    && (senhaDigitado != null && !senhaDigitado.isBlank())) {
                pessoas[posicao][0] = nomeDigitado;
                pessoas[posicao][1] = emailDigitado;
                pessoas[posicao][2] = senhaDigitado; // cadastrando a senha sem criptografar, acho que não tem
                                                     // necessidade por ser um trabalho
                pessoas[posicao][3] = LocalDateTime.now().toString().toUpperCase();
            } else {
                System.out.println("\nPreencha todos os campos para concluir o cadastro!\n");
                System.out.println("\n[ OK ] - PRESS ENTER\n");
                scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
                return;
            }
        }
        System.out.println("Cadastro de PESSOA concluido!\n");
        System.out.println("'" + pessoas[posicao][0] + "' cadastrado com sucesso!");

        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
    }

    // TIPO OBJETO
    static void cadastrarTipoObjeto() {
        int posicao;
        String digitado;
        posicao = verificaPosicaoLivreNoVetor(tipoObjeto);

        if (posicao == -1) {
            System.out
                    .println("Armazenamento de TIPOS DE OBJETO cheio! Não é possível cadastrar mais tipos de objetos.");
        } else {
            System.out.print("Nome -> ");
            digitado = scanner.nextLine().toUpperCase();
            if (digitado != null && !digitado.isBlank()) {
                tipoObjeto[posicao] = digitado;
            } else {
                System.out.println("\nPreencha todos os campos para concluir o cadastro!");
                System.out.println("\n[ OK ] - PRESS ENTER\n");
                scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
                return;
            }
        }

        System.out.println("\nCadastro de TIPO DE OBJETO concluido.\n");
        System.out.println("'" + tipoObjeto[posicao] + "' cadastrado com sucesso!");

        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
    }

    // OBJETO
    static void cadastrarObjeto() {
        int posicao;
        String nomeDigitado;

        posicao = verificaPosicaoLivreNaMatriz(objetos);

        if (posicao == -1) {
            System.out.println("Armazenamento de OBJETOS cheio! Não é possível cadastrar mais objetos.");
            return;
        }

        System.out.print("Nome -> ");
        nomeDigitado = scanner.nextLine().toUpperCase();

        if (nomeDigitado == null || nomeDigitado.isBlank()) {
            System.out.println("Preencha todos os campos para concluir o cadastro!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine();
            return;
        }

        // LISTA DE TIPOS DE OBJETO
        System.out.println("\n--- Tipos de Objetos ---");
        System.out.println("ID  | Nome");
        System.out.println("-----------------------------------");
        for (int i = 0; i < tipoObjeto.length; i++) {
            if (tipoObjeto[i] != null && !tipoObjeto[i].isEmpty()) {
                System.out.printf("%-3d | %-15s%n", i, tipoObjeto[i]);
                System.out.println("-----------------------------------");
            }
        }

        System.out.print("Digite o ID do Tipo -> ");
        int idTipo = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        if (idTipo < 0 || idTipo >= tipoObjeto.length ||
                tipoObjeto[idTipo] == null || tipoObjeto[idTipo].isEmpty()) {

            System.out.println("Selecione um TIPO DE OBJETO válido!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine();
            return;
        }
        String tipoEscolhido = tipoObjeto[idTipo].toUpperCase();

        // LISTA DE RESPONSÁVEIS
        System.out.println("\n--- Responsáveis ---");
        System.out.println("ID  | Nome");
        System.out.println("-----------------------------------");
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i][0] != null && !pessoas[i][0].isEmpty()) {
                System.out.printf("%-3d | %-15s%n", i, pessoas[i][0]);
                System.out.println("-----------------------------------");
            }
        }

        System.out.print("Digite o ID do responsável -> ");
        int idResponsavel = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        if (idResponsavel < 0 || idResponsavel >= pessoas.length ||
                pessoas[idResponsavel][0] == null || pessoas[idResponsavel][0].isEmpty()) {

            System.out.println("Responsável inválido!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine();
            return;
        }
        String responsavelEscolhido = pessoas[idResponsavel][0].toUpperCase();

        objetos[posicao][0] = nomeDigitado;
        objetos[posicao][1] = tipoEscolhido;
        objetos[posicao][2] = responsavelEscolhido;
        objetos[posicao][3] = "DISPONIVEL";

        System.out.println("\nCadastro de OBJETO concluído!");
        System.out.println("'" + objetos[posicao][0] + "' cadastrado com sucesso!");

        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine();
    }

    // MANUTENCAO
    static void cadastrarManutencao() {
        int posicao;
        String descricaoProblemaDigitado;
        posicao = verificaPosicaoLivreNaMatriz(manutencoes);
        if (posicao == -1) {
            System.out.print("Armazenamento de MANUTENÇÕES cheio! Não é possível cadastrar mais manutenções.");
        } else {
            System.out.println("--- Objetos ---");
            System.out.println("ID  | Nome            | Tipo              | Responsavel  | Situacao");
            System.out.println("---------------------------------------------------------------");
            for (int i = 0; i < objetos.length; i++) {
                if (objetos[i][0] != null && !objetos[i][0].isEmpty()) {
                    System.out.printf("%-3d | %-15s | %-17s | %-12s | %-10s%n",
                            i, objetos[i][0], objetos[i][1], objetos[i][2], objetos[i][3]);
                    System.out.println("---------------------------------------------------------------");
                }
            }
            System.out.println("(Digite o ID para selecionar!)");
            System.out.print("Objeto -> ");
            int idObjeto = scanner.nextInt();

            if (idObjeto < 0 || idObjeto >= objetos.length ||
                    objetos[idObjeto][0] == null || objetos[idObjeto][0].isEmpty()) {
                System.out.println("Objeto inválido!");
                System.out.println("\n[ OK ] - PRESS ENTER\n");
                scanner.nextLine();
                return;
            }
            String objeto = objetos[idObjeto][0].toUpperCase();

            scanner.nextLine();
            System.out.print("Descrição do problema -> ");
            descricaoProblemaDigitado = scanner.nextLine().toUpperCase();
            if (descricaoProblemaDigitado == null || descricaoProblemaDigitado.isBlank()) {
                System.out.println("Preencha todos os campos para concluir o cadastro!");
                System.out.println("\n[ OK ] - PRESS ENTER\n");
                scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
                return;
            }

            manutencoes[posicao][0] = objeto;
            manutencoes[posicao][1] = descricaoProblemaDigitado;
            manutencoes[posicao][2] = LocalDateTime.now().toString().toUpperCase(); // data de entrada
            manutencoes[posicao][3] = ""; // daata de saída ainda vazio
            manutencoes[posicao][4] = "recebido".toUpperCase(); // estado recebido

            for (int i = 0; i < objetos.length; i++) {
                if (objetos[i][0].equalsIgnoreCase(objeto)) {
                    objetos[i][3] = "MANUTENCAO";
                    break;
                }
            }
        }
        System.out.println("\nCadastro de MANUTENCAO concluido.\n");
        System.out.println("'" + manutencoes[posicao][0] + "' já esta com ticket de manutencao!");

        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
    }

    // EMPRESTIMO
    static void cadastrarEmprestimo() {
        int posicao;
        posicao = verificaPosicaoLivreNaMatriz(emprestimos);
        boolean existeDisponivel = false;
        if (posicao == -1) {
            System.out.print("Armazenamento de EMPRESTIMOS cheio! Não é possível cadastrar mais emprestimos.");
        } else {
            System.out.println("--- Objetos Disponíveis para Emprestimo ---");
            System.out.println("ID  | Nome            | Tipo              | Responsavel  | Situacao");
            System.out.println("---------------------------------------------------------------");
            for (int i = 0; i < objetos.length; i++) {
                if (objetos[i][0] != null && !objetos[i][0].isEmpty()) {
                    if (objetos[i][3].equalsIgnoreCase("disponivel")) { // Exibe apenas os objetos com situação =
                                                                        // disponíveis
                        existeDisponivel = true;

                        System.out.printf("%-3d | %-15s | %-17s | %-12s | %-10s%n",
                                i, objetos[i][0], objetos[i][1], objetos[i][2], objetos[i][3]);
                        System.out.println("---------------------------------------------------------------");
                    }
                }
            }

            if (!existeDisponivel) {
                System.out.println("\nNenhum objeto disponível para empréstimo no momento!");
                System.out.println("\n[ OK ] - PRESS ENTER\n");
                scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
                return;
            }

            System.out.println("(Digite o ID para selecionar!)");
            System.out.print("\nObjeto -> ");
            int idObjeto = scanner.nextInt();

            if (idObjeto < 0 || idObjeto >= objetos.length ||
                    objetos[idObjeto][0] == null || objetos[idObjeto][0].isEmpty()) {

                System.out.println("Objeto inválido!");
                System.out.println("\n[ OK ] - PRESS ENTER\n");
                scanner.nextLine();
                return;
            }

            String objeto = objetos[idObjeto][0].toUpperCase();
            String responsavelObjeto = objetos[idObjeto][2];

            System.out.println("--- Pessoas ---");
            System.out.println("ID  | Nome            | Email                | Senha      | Entrada");
            System.out.println("---------------------------------------------------------------");
            for (int i = 0; i < pessoas.length; i++) {
                if (pessoas[i][0] != null && !pessoas[i][0].isEmpty()) {
                    if (!pessoas[i][0].equalsIgnoreCase(responsavelObjeto)) { // nao exibe o dono do objeto como opcao
                        System.out.printf("%-3d | %-15s | %-20s | %-10s | %-10s%n",
                                i, pessoas[i][0], pessoas[i][1], pessoas[i][2], pessoas[i][3]);
                        System.out.println("---------------------------------------------------------------");
                    }
                }
            }
            System.out.print("(Digite o ID para selecionar!)\n");
            System.out.print("\nPessoa que será responsável pelo empréstimo -> ");
            int idPessoa = scanner.nextInt();

            if (idPessoa < 0 || idPessoa >= pessoas.length || pessoas[idPessoa][0] == null
                    || pessoas[idPessoa][0].isEmpty() || pessoas[idPessoa][0].equalsIgnoreCase(responsavelObjeto)) {

                System.out.println("Pessoa inválida!");
                System.out.println("\n[ OK ] - PRESS ENTER\n");
                scanner.nextLine();
                return;
            }
            String pessoa = pessoas[idPessoa][0].toUpperCase();

            // verifica se a pessoa digitada eh realmente verdadeira

            emprestimos[posicao][0] = pessoa;
            emprestimos[posicao][1] = objeto;
            emprestimos[posicao][2] = "EMPRESTADO";
            emprestimos[posicao][3] = LocalDateTime.now().toString().toUpperCase(); // data de entrada
            emprestimos[posicao][4] = ""; // ainda vazio

            // muda a situação do objeto escolhido para "EMPRESTADO"
            for (int i = 0; i < objetos.length; i++) {
                if (objetos[i][0].equalsIgnoreCase(objeto)) {
                    objetos[i][3] = "EMPRESTADO";
                    break;
                }
            }

            System.out.println("\nCadastro de EMPRESTIMO concluido.\n");
            System.out.println("'" + objeto + "' foi emprestado!");

            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
        }
    }

    // ==========================================================
    // FUNÇÕES DE LISTAR/CONSULTA
    // ==========================================================
    // PESSOA
    static void listarPessoas() {
        // irá exibir a senha mesmo, sabendo que eh so um trabalho...
        System.out.println("--- Pessoas ---");
        System.out.println("ID  | Nome            | Email                | Senha      | Entrada");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i][0] != null && !pessoas[i][0].isEmpty()) {
                System.out.printf("%-3d | %-15s | %-20s | %-10s | %-10s%n",
                        i, pessoas[i][0], pessoas[i][1], pessoas[i][2], pessoas[i][3]);
                System.out.println("---------------------------------------------------------------");
            }
        }

        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
    }

    // TIPO OBJETOS
    static void listarTiposObjetos() {
        System.out.println("--- Tipos de objetos ---");
        System.out.println("ID  | Nome ");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < tipoObjeto.length; i++) {
            if (tipoObjeto[i] != null && !tipoObjeto[i].isEmpty()) {
                System.out.printf("%-3d | %-15s%n",
                        i, tipoObjeto[i]);
                System.out.println("---------------------------------------------------------------");
            }
        }

        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine(); // espera a confirmação antes de mostrar o menu novamente
    }
    // OBJETOS

    static void listarObjetos() {

        System.out.println("\n===== LISTAR OBJETOS =====");
        System.out.println("1 - Listar Geral");
        System.out.println("2 - Listar por Tipo de Objeto");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        // Criar lista auxiliar para ordenação
        ArrayList<String[]> lista = new ArrayList<>();

        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i][0] != null && !objetos[i][0].isEmpty()) {
                lista.add(new String[] {
                        String.valueOf(i),
                        objetos[i][0],
                        objetos[i][1],
                        objetos[i][2],
                        objetos[i][3]
                });
            }
        }

        // Ordenar pelo nome (coluna 1)
        lista.sort(Comparator.comparing(a -> a[1]));

        // =========================
        // LISTAGEM GERAL
        // =========================
        if (opcao == 1) {

            System.out.println("\n===== OBJETOS NÃO EMPRESTADOS =====");
            for (String[] obj : lista) {
                String situacao = obj[4];

                if (!situacao.equalsIgnoreCase("EMPRESTADO")) {
                    System.out.printf(
                            "ID: %-3s | Nome: %-15s | Tipo: %-12s | Situação: %-12s%n",
                            obj[0], obj[1], obj[2], obj[4]);
                }
            }

            System.out.println("\n===== OBJETOS EMPRESTADOS =====");
            for (String[] obj : lista) {
                String situacao = obj[4];

                if (situacao.equalsIgnoreCase("EMPRESTADO")) {
                    System.out.printf(
                            "ID: %-3s | Nome: %-15s | Tipo: %-12s | Situação: %-12s | Responsável: %-15s%n",
                            obj[0], obj[1], obj[2], obj[4], obj[3]);
                }
            }

            System.out.println("\n[ OK ] - PRESS ENTER");
            scanner.nextLine();
            return;
        }

        // =========================
        // LISTAR POR TIPO
        // =========================
        else if (opcao == 2) {

            System.out.println("\n===== TIPOS DE OBJETO =====");
            for (int i = 0; i < tipoObjeto.length; i++) {
                if (tipoObjeto[i] != null && !tipoObjeto[i].isEmpty()) {
                    System.out.printf("%-3d | %s%n", i, tipoObjeto[i]);
                }
            }

            System.out.print("\nSelecione o ID do tipo -> ");
            int idTipo = scanner.nextInt();
            scanner.nextLine();

            if (idTipo < 0 || idTipo >= tipoObjeto.length || tipoObjeto[idTipo] == null
                    || tipoObjeto[idTipo].isEmpty()) {
                System.out.println("\n[ERRO] Tipo inválido!");
                System.out.println("\n[ OK ] - PRESS ENTER");
                scanner.nextLine();
                return;
            }

            String tipoEscolhido = tipoObjeto[idTipo];

            System.out.println("\n== Objetos do tipo: " + tipoEscolhido + " ==");

            System.out.println("\n===== NÃO EMPRESTADOS =====");
            for (String[] obj : lista) {
                if (obj[2].equals(tipoEscolhido) && !obj[4].equalsIgnoreCase("EMPRESTADO")) {
                    System.out.printf(
                            "ID: %-3s | Nome: %-15s | Tipo: %-12s | Situação: %-12s%n",
                            obj[0], obj[1], obj[2], obj[4]);
                }
            }

            System.out.println("\n===== EMPRESTADOS =====");
            for (String[] obj : lista) {
                if (obj[2].equals(tipoEscolhido) && obj[4].equalsIgnoreCase("EMPRESTADO")) {
                    System.out.printf(
                            "ID: %-3s | Nome: %-15s | Tipo: %-12s | Situação: %-12s | Responsável: %-15s%n",
                            obj[0], obj[1], obj[2], obj[4], obj[3]);
                }
            }

            System.out.println("\n[ OK ] - PRESS ENTER");
            scanner.nextLine();
            return;
        }

        else {
            System.out.println("\nOpção inválida!");
            System.out.println("[ OK ] - PRESS ENTER");
            scanner.nextLine();
        }
    }

    // MANUTENCOES
    static void listarManutencoes() {

        System.out.println("--- Selecione uma opção ---");
        System.out.println("1 - Listar Todos ");
        System.out.println("2 - Listar por Data ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        if (opcao == 1) {
            System.out.println("--- Emprestimos ---");
            System.out.println(
                    "ID  | Responsável          | Objeto              | Situação               | Data do Emprestimo                 | Data Devolução");
            System.out.println("---------------------------------------------------------------");
            for (int i = 0; i < emprestimos.length; i++) {
                if (emprestimos[i][0] != null && !emprestimos[i][0].isEmpty()) {
                    System.out.printf("%-3d | %-15s | %-22s | %-15s | %-15s  | %-10s %n",
                            i, emprestimos[i][0], emprestimos[i][1], emprestimos[i][2], emprestimos[i][3],
                            emprestimos[i][4]);
                    System.out.println("---------------------------------------------------------------");
                }
            }
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine();
        }

        else if (opcao == 2) {

            System.out.print("\n Data para pesquisa (dd/mm/aaaa) -> ");
            String dataDigitada = scanner.nextLine();

            boolean encontrado = false;

            System.out.println("\n--- Empréstimos na data " + dataDigitada + " ---");
            System.out.println(
                    "ID  | Responsável          | Objeto              | Situação               | Data do Emprestimo                 | Data Devolução");
            System.out.println("---------------------------------------------------------------");

            for (int i = 0; i < emprestimos.length; i++) {
                if (emprestimos[i][3] != null && !emprestimos[i][3].isEmpty()) {

                    if (dataISOCombinaComDDMMAAAA(emprestimos[i][3], dataDigitada)) {

                        encontrado = true;

                        System.out.printf("%-3d | %-15s | %-22s | %-15s | %-15s  | %-10s %n",
                                i, emprestimos[i][0], emprestimos[i][1], emprestimos[i][2],
                                emprestimos[i][3], emprestimos[i][4]);

                        System.out.println("---------------------------------------------------------------");
                    }
                }
            }

            if (!encontrado) {
                System.out.println("\nNenhum empréstimo encontrado nesta data!");
            }

            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine();

        }
    }

    static boolean dataISOCombinaComDDMMAAAA(String dataISO, String dataDigitada) {
        if (dataISO == null || dataISO.isEmpty() || dataDigitada == null || dataDigitada.isEmpty())
            return false;

        try {
            // Converte entrada dd/mm/aaaa
            DateTimeFormatter fmtEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataBusca = LocalDate.parse(dataDigitada, fmtEntrada);

            // Converte data salva (ISO)
            LocalDateTime dt = LocalDateTime.parse(dataISO);

            // Compara apenas ano-mês-dia
            return dt.toLocalDate().equals(dataBusca);

        } catch (Exception e) {
            return false;
        }
    }

    // EMPRESTIMOS
    static void listarEmprestimos() {

        System.out.println("--- Selecione uma opção ---");
        System.out.println("1 - Listar Todos ");
        System.out.println("2 - Listar por Data ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // limpar buffer

        if (opcao == 1) {
            System.out.println("--- Emprestimos ---");
            System.out.println(
                    "ID  | Responsável          | Objeto              | Situação               | Data do Emprestimo                 | Data Devolução");
            System.out.println("---------------------------------------------------------------");
            for (int i = 0; i < emprestimos.length; i++) {
                if (emprestimos[i][0] != null && !emprestimos[i][0].isEmpty()) {
                    System.out.printf("%-3d | %-15s | %-22s | %-15s | %-15s  | %-10s %n",
                            i, emprestimos[i][0], emprestimos[i][1], emprestimos[i][2], emprestimos[i][3],
                            emprestimos[i][4]);
                    System.out.println("---------------------------------------------------------------");
                }
            }
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine();
        }

        else if (opcao == 2) {

            System.out.print("\n Data para pesquisa (dd/mm/aaaa) -> ");
            String dataPesquisa = scanner.nextLine().trim();

            System.out.println("\n--- Emprestimos na data " + dataPesquisa + " ---");
            System.out.println(
                    "ID  | Responsável          | Objeto              | Situação               | Data do Emprestimo                 | Data Devolução");
            System.out.println("---------------------------------------------------------------");

            boolean encontrou = false;

            for (int i = 0; i < emprestimos.length; i++) {
                if (emprestimos[i][0] != null && !emprestimos[i][0].isEmpty()) {

                    // AQUI é onde filtra pela data do empréstimo (coluna 3)
                    if (emprestimos[i][3] != null && dataISOCombinaComDDMMAAAA(emprestimos[i][3], dataPesquisa)) {

                        encontrou = true;
                        System.out.printf("%-3d | %-15s | %-22s | %-15s | %-15s  | %-10s %n",
                                i, emprestimos[i][0], emprestimos[i][1], emprestimos[i][2], emprestimos[i][3],
                                emprestimos[i][4]);
                        System.out.println("---------------------------------------------------------------");
                    }
                }
            }

            if (!encontrou) {
                System.out.println("\nNenhum empréstimo encontrado nesta data.");
            }

            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine();
        }
    }

    // ==========================================================
    // FUNÇÕES DE ALTERAR
    // ==========================================================
    // PESSOA
    static void alterarPessoa() {
        System.out.println("--- Pessoas ---");
        System.out.println("ID  | Nome            | Email                | Senha      | Entrada");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i][0] != null && !pessoas[i][0].isEmpty()) {
                System.out.printf("%-3d | %-15s | %-20s | %-10s | %-10s%n",
                        i, pessoas[i][0], pessoas[i][1], pessoas[i][2], pessoas[i][3]);
                System.out.println("---------------------------------------------------------------");
            }
        }

        System.out.println("(Selecione o ID do usuário!)\n");
        System.out.print("Usuário que você deseja alterar -> ");
        int idPessoaEscolhida = scanner.nextInt();

        // verificar se o índice é válido e se existe pessoa cadastrada
        if (idPessoaEscolhida < 0 || idPessoaEscolhida >= pessoas.length || pessoas[idPessoaEscolhida][0] == null
                || pessoas[idPessoaEscolhida][0].isEmpty()) {
            System.out.println("\n[ERRO] - Selecione um usuário válido para fazer a alteração!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            scanner.nextLine();
            return;
        }

        System.out.println("\n===== Qual informação você deseja alterar? =====");
        System.out.println("1 - Nome");
        System.out.println("2 - Email");
        System.out.println("3 - Senha");
        System.out.println("4 - Cancelar");
        System.out.print("\nEscolha uma opção: ");
        int opcaoEditar = scanner.nextInt();

        if (opcaoEditar == 4) {
            System.out.println("Cancelando edição...");
            return;
        }

        String opcaoEditarTexto;
        if (opcaoEditar == 1)
            opcaoEditarTexto = "NOME";
        else if (opcaoEditar == 2)
            opcaoEditarTexto = "EMAIL";
        else if (opcaoEditar == 3)
            opcaoEditarTexto = "SENHA";
        else {
            System.out.println("Opção inválida!");
            return;
        }

        // pede a nova informação ANTES de gravar
        scanner.nextLine();
        System.out.println("\nAlterando " + opcaoEditarTexto);
        System.out.print("Novo(a) " + opcaoEditarTexto + " -> ");
        String novaInformacao = scanner.nextLine();
        if (novaInformacao.isEmpty()) {
            System.out.println("Valor vazio. Operação cancelada.");
            return;
        }

        // grava a alteração
        if (opcaoEditar == 1) {
            pessoas[idPessoaEscolhida][0] = novaInformacao.toUpperCase();
        } else if (opcaoEditar == 2) {
            pessoas[idPessoaEscolhida][1] = novaInformacao.toUpperCase();
        } else if (opcaoEditar == 3) {
            pessoas[idPessoaEscolhida][2] = novaInformacao.toUpperCase();
        }

        System.out.println("\n" + opcaoEditarTexto + " alterado com sucesso!");
        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine(); // espera antes de voltar
    }

    static void alterarTipoObjeto() {
        System.out.println("--- Tipos de objetos ---");
        System.out.println("ID  | Nome ");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < tipoObjeto.length; i++) {
            if (tipoObjeto[i] != null && !tipoObjeto[i].isEmpty()) {
                System.out.printf("%-3d | %-15s%n",
                        i, tipoObjeto[i]);
                System.out.println("---------------------------------------------------------------");
            }
        }

        System.out.println("(Selecione o ID do Tipo de Objeto!)\n");
        System.out.print("Tipo de Objeto que você deseja alterar -> ");
        int idTipoObjetoEscolhido = scanner.nextInt();

        // verificar se o índice é válido e se existe pessoa cadastrada
        if (idTipoObjetoEscolhido < 0 || idTipoObjetoEscolhido >= tipoObjeto.length
                || tipoObjeto[idTipoObjetoEscolhido] == null || tipoObjeto[idTipoObjetoEscolhido].isEmpty()) {
            System.out.println("\n[ERRO] - Selecione um Tipo de Objeto válido para fazer a alteração!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            scanner.nextLine();
            return;
        }

        System.out.println("\n===== Qual informação você deseja alterar? =====");
        System.out.println("1 - Nome");
        System.out.println("2 - Cancelar");
        System.out.print("\nEscolha uma opção: ");
        int opcaoEditar = scanner.nextInt();

        if (opcaoEditar == 2) {
            System.out.println("Cancelando edição...");
            return;
        }

        String opcaoEditarTexto;
        if (opcaoEditar == 1) {
            opcaoEditarTexto = "NOME";
        } else {
            System.out.println("Opção inválida!");
            return;
        }

        // pede a nova informação antes de alterar
        scanner.nextLine();
        System.out.println("\nAlterando " + opcaoEditarTexto);
        System.out.print("Novo(a) " + opcaoEditarTexto + " -> ");
        String novaInformacao = scanner.nextLine();
        if (novaInformacao.isEmpty()) {
            System.out.println("Valor vazio. Operação cancelada.");
            return;
        }

        // grava a alteração
        if (opcaoEditar == 1) {
            tipoObjeto[idTipoObjetoEscolhido] = novaInformacao.toUpperCase();
        }

        System.out.println("\n" + opcaoEditarTexto + " alterado com sucesso!");
        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine(); // espera antes de voltar
    }

    static void alterarObjeto() {
        System.out.println("--- Objetos ---");
        System.out.println("ID  | Nome            | Tipo              | Responsavel  | Situacao");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i][0] != null && !objetos[i][0].isEmpty()) {
                System.out.printf("%-3d | %-15s | %-17s | %-12s | %-10s%n",
                        i, objetos[i][0], objetos[i][1], objetos[i][2], objetos[i][3]);
                System.out.println("---------------------------------------------------------------");
            }
        }
        System.out.println("(Selecione o ID do Objeto!)\n");
        System.out.print("Objeto que você deseja alterar -> ");
        int idObjetoEscolhido = scanner.nextInt();

        if (idObjetoEscolhido < 0 || idObjetoEscolhido >= objetos.length || objetos[idObjetoEscolhido][0] == null
                || objetos[idObjetoEscolhido][0].isEmpty()) {
            System.out.println("\n{[ERRO] - Selecione um Objeto válido para fazer a alteração!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine();
            scanner.nextLine();
            return;
        }

        System.out.println("\n===== Qual informação você deseja alterar? =====");
        System.out.println("1 - Nome");
        System.out.println("2 - Tipo");
        System.out.println("3 - Responsável");
        System.out.println("4 - Situação");
        System.out.println("5 - Cancelar");
        System.out.print("\nEscolha uma opção: ");
        int opcaoEditar = scanner.nextInt();

        if (opcaoEditar == 5) {
            System.out.println("Cancelando edição...");
            return;
        }

        scanner.nextLine(); // limpar buffer
        String novaInformacao = "";

        // ============================================================
        // ALTERAR NOME DO OBJETO
        // ============================================================
        if (opcaoEditar == 1) {
            System.out.println("\nAlterando NOME");
            System.out.print("Novo NOME -> ");
            novaInformacao = scanner.nextLine();
            if (!novaInformacao.isEmpty()) {
                objetos[idObjetoEscolhido][0] = novaInformacao.toUpperCase();
            }
        }

        // ============================================================
        // ALTERAR TIPO DO OBJETO
        // ============================================================
        else if (opcaoEditar == 2) {
            System.out.println("\n--- Tipos Disponíveis ---");
            System.out.println("ID  | Nome");
            System.out.println("-----------------------------------");

            for (int i = 0; i < tipoObjeto.length; i++) {
                if (tipoObjeto[i] != null && !tipoObjeto[i].isEmpty()) {
                    System.out.printf("%-3d | %-15s%n", i, tipoObjeto[i]);
                    System.out.println("-----------------------------------");
                }
            }

            System.out.print("\nSelecione o ID do novo TIPO -> ");
            int idTipo = scanner.nextInt();
            scanner.nextLine();

            if (idTipo >= 0 && idTipo < tipoObjeto.length && tipoObjeto[idTipo] != null
                    && !tipoObjeto[idTipo].isEmpty()) {
                objetos[idObjetoEscolhido][1] = tipoObjeto[idTipo].toUpperCase();
            } else {
                System.out.println("Tipo inválido! Alteração cancelada.");
                return;
            }
        }

        // ============================================================
        // ALTERAR RESPONSÁVEL
        // ============================================================
        else if (opcaoEditar == 3) {

            System.out.println("\n--- Responsáveis Disponíveis ---");
            System.out.println("ID  | Nome");
            System.out.println("-----------------------------------");

            for (int i = 0; i < pessoas.length; i++) {
                if (pessoas[i][0] != null && !pessoas[i][0].isEmpty()) {
                    System.out.printf("%-3d | %-15s%n", i, pessoas[i][0]);
                    System.out.println("-----------------------------------");
                }
            }

            System.out.print("\nSelecione o ID do novo RESPONSÁVEL -> ");
            int idResp = scanner.nextInt();
            scanner.nextLine();

            if (idResp >= 0 && idResp < pessoas.length && pessoas[idResp][0] != null && !pessoas[idResp][0].isEmpty()) {
                objetos[idObjetoEscolhido][2] = pessoas[idResp][0].toUpperCase();
            } else {
                System.out.println("Responsável inválido! Alteração cancelada.");
                return;
            }
        }

        // ============================================================
        // ALTERAR SITUAÇÃO DO OBJETO
        // ============================================================
        else if (opcaoEditar == 4) {

            String[] situacoes = { "DISPONIVEL", "EMPRESTADO", "MANUTENCAO", "BAIXADO" };

            System.out.println("\n--- Situações Disponíveis ---");
            for (int i = 0; i < situacoes.length; i++) {
                System.out.println(i + " - " + situacoes[i]);
            }

            System.out.print("\nSelecione a nova situação -> ");
            int idSit = scanner.nextInt();
            scanner.nextLine();

            if (idSit >= 0 && idSit < situacoes.length) {
                objetos[idObjetoEscolhido][3] = situacoes[idSit];
            } else {
                System.out.println("Situação inválida! Alteração cancelada.");
                return;
            }
        }

        System.out.println("\nAlteração realizada com sucesso!");
        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine();
    }

    static void alterarManutencao() {

        System.out.println("--- Manutenções ---");
        System.out.println(
                "ID  | Objeto          | Descricao              | Data Entrada        | Data Saida          | Estado");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < manutencoes.length; i++) {
            if (manutencoes[i][0] != null && !manutencoes[i][0].isEmpty()) {
                System.out.printf("%-3d | %-15s | %-22s | %-18s | %-18s | %-12s%n",
                        i, manutencoes[i][0], manutencoes[i][1], manutencoes[i][2],
                        manutencoes[i][3], manutencoes[i][4]);
                System.out.println("---------------------------------------------------------------");
            }
        }

        System.out.println("(Selecione o ID da Manutenção!)\n");
        System.out.print("Manutenção que você deseja alterar -> ");

        int idManutencaoEscolhido = scanner.nextInt();
        scanner.nextLine();

        if (idManutencaoEscolhido < 0 ||
                idManutencaoEscolhido >= manutencoes.length ||
                manutencoes[idManutencaoEscolhido][0] == null ||
                manutencoes[idManutencaoEscolhido][0].isEmpty()) {

            System.out.println("\n[ERRO] - Selecione uma Manutenção válida!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine();
            return;
        }

        System.out.println("\n===== Qual informação você deseja alterar? =====");
        System.out.println("1 - Objeto");
        System.out.println("2 - Descrição");
        System.out.println("3 - Estado");
        System.out.println("4 - Cancelar");
        System.out.print("\nEscolha uma opção: ");

        int opcaoEditar = scanner.nextInt();
        scanner.nextLine();

        if (opcaoEditar == 4) {
            System.out.println("Cancelando edição...");
            return;
        }

        // ---------------------------
        // ALTERAR OBJETO
        // ---------------------------
        if (opcaoEditar == 1) {

            System.out.println("\n--- Objetos cadastrados ---");
            System.out.println("ID  | Nome            | Tipo             | Responsável      | Situação");
            System.out.println("---------------------------------------------------------------");

            for (int i = 0; i < objetos.length; i++) {
                if (objetos[i][0] != null && !objetos[i][0].isEmpty()) {
                    System.out.printf("%-3d | %-15s | %-16s | %-16s | %-12s%n",
                            i, objetos[i][0], objetos[i][1], objetos[i][2], objetos[i][3]);
                    System.out.println("---------------------------------------------------------------");
                }
            }

            System.out.print("Digite o ID do novo objeto -> ");
            int idObj = scanner.nextInt();
            scanner.nextLine();

            if (idObj < 0 || idObj >= objetos.length ||
                    objetos[idObj][0] == null || objetos[idObj][0].isEmpty()) {

                System.out.println("\n[ERRO] Objeto inválido!");
                return;
            }

            manutencoes[idManutencaoEscolhido][0] = objetos[idObj][0].toUpperCase();

            System.out.println("\nObjeto alterado com sucesso!");
        }

        // ---------------------------
        // ALTERAR ESTADO
        // ---------------------------
        else if (opcaoEditar == 3) {

            System.out.println("\n--- Estados disponíveis ---");
            System.out.println("1 - RECEBIDO");
            System.out.println("2 - EM MANUTENCAO");
            System.out.println("3 - CONCLUIDO");
            System.out.print("Novo estado -> ");

            int idEstado = scanner.nextInt();
            scanner.nextLine();

            String novoEstado;

            if (idEstado == 1)
                novoEstado = "RECEBIDO";
            else if (idEstado == 2)
                novoEstado = "EM MANUTENCAO";
            else if (idEstado == 3)
                novoEstado = "CONCLUIDO";
            else {
                System.out.println("Estado inválido!");
                return;
            }

            manutencoes[idManutencaoEscolhido][4] = novoEstado;

            // Atualiza o status do objeto relacionado
            String nomeObj = manutencoes[idManutencaoEscolhido][0];

            for (int i = 0; i < objetos.length; i++) {
                if (objetos[i][0] != null &&
                        objetos[i][0].equalsIgnoreCase(nomeObj)) {

                    if (novoEstado.equals("RECEBIDO") ||
                            novoEstado.equals("EM MANUTENCAO")) {

                        objetos[i][3] = "MANUTENCAO";
                    }

                    if (novoEstado.equals("CONCLUIDO")) {
                        objetos[i][3] = "DISPONIVEL";

                        // AQUI: data de saída atual AUTOMATICAMENTE
                        java.time.LocalDate hoje = java.time.LocalDate.now();
                        manutencoes[idManutencaoEscolhido][3] = hoje.toString();
                    }
                }
            }

            System.out.println("\nEstado alterado com sucesso!");
        }

        // ---------------------------
        // ALTERAR DESCRIÇÃO
        // ---------------------------
        else if (opcaoEditar == 2) {

            System.out.println("\nAlterando DESCRIÇÃO");
            System.out.print("Nova DESCRIÇÃO -> ");

            String novaDescricao = scanner.nextLine().toUpperCase();

            if (novaDescricao.isEmpty()) {
                System.out.println("Valor inválido.");
                return;
            }

            manutencoes[idManutencaoEscolhido][1] = novaDescricao;

            System.out.println("\nDescrição alterada com sucesso!");
        }

        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine();
    }

    static void alterarEmprestimo() {
        System.out.println("--- Emprestimos ---");
        System.out.println(
                "ID  | Responsável          | Objeto              | Situação               | Data do Emprestimo       | Data Devolução");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < emprestimos.length; i++) {
            if (emprestimos[i][0] != null && !emprestimos[i][0].isEmpty()) {
                System.out.printf("%-3d | %-15s | %-20s | %-15s | %-20s | %-15s %n",
                        i, emprestimos[i][0], emprestimos[i][1], emprestimos[i][2], emprestimos[i][3],
                        emprestimos[i][4]);
                System.out.println("---------------------------------------------------------------");
            }
        }

        System.out.println("(Selecione o ID do Empréstimo!)\n");
        System.out.print("Empréstimo que você deseja alterar -> ");
        int idE = scanner.nextInt();

        if (idE < 0 || idE >= emprestimos.length || emprestimos[idE][0] == null || emprestimos[idE][0].isEmpty()) {
            System.out.println("\n[ERRO] - Seleciona um empréstimo válido!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine();
            scanner.nextLine();
            return;
        }

        System.out.println("\n===== O que deseja alterar? =====");
        System.out.println("1 - Responsável");
        System.out.println("2 - Objeto");
        System.out.println("3 - Situação");
        System.out.println("4 - Cancelar");
        System.out.print("\nEscolha uma opção: ");
        int opcao = scanner.nextInt();

        if (opcao == 4)
            return;

        scanner.nextLine();

        // =====================================
        // 1 - ALTERAR RESPONSÁVEL
        // =====================================
        if (opcao == 1) {
            System.out.println("\n--- Responsáveis Disponíveis ---");
            for (int i = 0; i < pessoas.length; i++) {
                if (pessoas[i][0] != null && !pessoas[i][0].isEmpty()) {
                    System.out.printf("%d - %s%n", i, pessoas[i][0]);
                }
            }

            System.out.print("Selecione o novo responsável -> ");
            int idResp = scanner.nextInt();
            scanner.nextLine();

            if (idResp < 0 || idResp >= pessoas.length || pessoas[idResp][0] == null) {
                System.out.println("[ERRO] Responsável inválido!");
                return;
            }

            emprestimos[idE][0] = pessoas[idResp][0].toUpperCase();

            System.out.println("\nResponsável alterado com sucesso!");
            System.out.println("\n[ OK ] - ENTER\n");
            scanner.nextLine();
            return;
        }

        // =====================================
        // 2 - ALTERAR OBJETO
        // =====================================
        if (opcao == 2) {

            // O objeto atual volta a ficar disponível
            String objetoAtual = emprestimos[idE][1];
            for (int i = 0; i < objetos.length; i++) {
                if (objetos[i][0] != null && objetos[i][0].equalsIgnoreCase(objetoAtual)) {
                    objetos[i][3] = "DISPONIVEL";
                }
            }

            System.out.println("\n--- Objetos Disponíveis ---");
            for (int i = 0; i < objetos.length; i++) {
                if (objetos[i][0] != null && objetos[i][2].equals("DISPONIVEL")) {
                    System.out.printf("%d - %s%n", i, objetos[i][0]);
                }
            }

            System.out.print("Selecione o novo objeto -> ");
            int idObj = scanner.nextInt();
            scanner.nextLine();

            if (idObj < 0 || idObj >= objetos.length || objetos[idObj][0] == null) {
                System.out.println("[ERRO] Objeto inválido!");
                return;
            }

            // Novo objeto vira emprestado
            objetos[idObj][2] = "EMPRESTADO";
            emprestimos[idE][1] = objetos[idObj][0].toUpperCase();

            System.out.println("\nObjeto alterado com sucesso!");
            System.out.println("\n[ OK ] - ENTER\n");
            scanner.nextLine();
            return;
        }

        // =====================================
        // 3 - ALTERAR SITUAÇÃO
        // =====================================
        if (opcao == 3) {
            System.out.println("\nSituações disponíveis:");
            System.out.println("1 - EM ANDAMENTO");
            System.out.println("2 - DEVOLVIDO");
            System.out.print("Escolha -> ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == 1) {
                emprestimos[idE][2] = "EM ANDAMENTO";
                System.out.println("\nSituação alterada para EM ANDAMENTO!");
            }

            else if (escolha == 2) {
                emprestimos[idE][2] = "DEVOLVIDO";

                // seta a data de devolução
                emprestimos[idE][4] = java.time.LocalDate.now().toString();

                // objeto devolvido volta a ficar disponível
                String obj = emprestimos[idE][1];
                for (int i = 0; i < objetos.length; i++) {
                    if (objetos[i][0] != null && objetos[i][0].equalsIgnoreCase(obj)) {
                        objetos[i][3] = "DISPONIVEL";
                    }
                }

                System.out.println("\nSituação alterada para DEVOLVIDO e objeto liberado!");
            }

            else {
                System.out.println("Opção inválida!");
                return;
            }

            System.out.println("\n[ OK ] - ENTER\n");
            scanner.nextLine();
            return;
        }
    }

    // ==========================================================
    // FUNÇÕES DE EXCLUIR
    // ==========================================================

    static void excluirPessoa() {
        System.out.println("--- Pessoas ---");
        System.out.println("ID  | Nome            | Email                | Senha      | Entrada");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < pessoas.length; i++) {
            if (pessoas[i][0] != null && !pessoas[i][0].isEmpty()) {
                System.out.printf("%-3d | %-15s | %-20s | %-10s | %-10s%n",
                        i, pessoas[i][0], pessoas[i][1], pessoas[i][2], pessoas[i][3]);
                System.out.println("---------------------------------------------------------------");
            }
        }
        System.out.println("(Digite o ID para selecionar!)\n");
        System.out.print("Pessoa que deseja excluir -> ");
        int idPesoaSelecionada = scanner.nextInt();

        if (idPesoaSelecionada < 0 || idPesoaSelecionada >= pessoas.length || pessoas[idPesoaSelecionada][0] == null
                || pessoas[idPesoaSelecionada][0].isEmpty()) {
            System.out.println("\n[ERRO] - Selecione um usuário válido para fazer a exclusão!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            scanner.nextLine();
            return;
        }

        System.out.println("Você tem certeza que deseja excluir o usuário " + pessoas[idPesoaSelecionada][0]
                + "? Sim(Y) Não(N). ");
        scanner.nextLine();
        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("y")) {
            System.out.println("\nExclusão cancelada...");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            return;
        }

        for (int j = 0; j < pessoas[idPesoaSelecionada].length; j++) {
            pessoas[idPesoaSelecionada][j] = ""; // apagando os dados
        }

        System.out.println("Usuário excluído com sucesso!");
        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine();
    }

    static void excluirTipoObjeto() {
        System.out.println("--- Tipos de objetos ---");
        System.out.println("ID  | Nome ");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < tipoObjeto.length; i++) {
            if (tipoObjeto[i] != null && !tipoObjeto[i].isEmpty()) {
                System.out.printf("%-3d | %-15s%n",
                        i, tipoObjeto[i]);
                System.out.println("---------------------------------------------------------------");
            }
        }

        System.out.println("(Digite o ID para selecionar!)\n");
        System.out.print("Tipo Objeto que deseja excluir -> ");
        int idTipoObjetoSelecionado = scanner.nextInt();

        if (idTipoObjetoSelecionado < 0 || idTipoObjetoSelecionado >= tipoObjeto.length
                || tipoObjeto[idTipoObjetoSelecionado] == null || tipoObjeto[idTipoObjetoSelecionado].isEmpty()) {
            System.out.println("\n[ERRO] - Selecione um usuário válido para fazer a exclusão!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            scanner.nextLine();
            return;
        }

        System.out.println("Você tem certeza que deseja excluir o Tipo Objeto " + tipoObjeto[idTipoObjetoSelecionado]
                + "? Sim(Y) Não(N). ");
        scanner.nextLine();
        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("y")) {
            System.out.println("\nExclusão cancelada...");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            return;
        }

        tipoObjeto[idTipoObjetoSelecionado] = "";

        System.out.println("Tipo Objeto excluído com sucesso!");
        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine();
    }

    static void excluirObjeto() {
        System.out.println("--- Objetos ---");
        System.out.println("ID  | Nome            | Tipo              | Responsavel  | Situacao");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i][0] != null && !objetos[i][0].isEmpty()) {
                System.out.printf("%-3d | %-15s | %-17s | %-12s | %-10s%n",
                        i, objetos[i][0], objetos[i][1], objetos[i][2], objetos[i][3]);
                System.out.println("---------------------------------------------------------------");
            }
        }

        System.out.println("(Digite o ID para selecionar!)\n");
        System.out.print("Objeto que deseja excluir -> ");
        int idObjetoSelecionado = scanner.nextInt();

        if (idObjetoSelecionado < 0 || idObjetoSelecionado >= objetos.length || objetos[idObjetoSelecionado][0] == null
                || objetos[idObjetoSelecionado][0].isEmpty()) {
            System.out.println("\n[ERRO] - Selecione um Objeto válido para fazer a exclusão!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            scanner.nextLine();
            return;
        }

        System.out.println("Você tem certeza que deseja excluir o objeto " + objetos[idObjetoSelecionado][0]
                + "? Sim(Y) Não(N). ");
        scanner.nextLine();
        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("y")) {
            System.out.println("\nExclusão cancelada...");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            return;
        }

        for (int j = 0; j < objetos[idObjetoSelecionado].length; j++) {
            objetos[idObjetoSelecionado][j] = ""; // apagando os dados
        }

        System.out.println("Objeto excluído com sucesso!");
        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine();
    }

    static void excluirManutencao() {
        System.out.println("--- Manutenções ---");
        System.out.println(
                "ID  | Objeto          | Descricao              | Data de Entrada               | Data de Saida                 | Estado");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < manutencoes.length; i++) {
            if (manutencoes[i][0] != null && !manutencoes[i][0].isEmpty()) {
                System.out.printf("%-3d | %-15s | %-22s | %-15s | %-15s  | %-10s %n",
                        i, manutencoes[i][0], manutencoes[i][1], manutencoes[i][2], manutencoes[i][3],
                        manutencoes[i][4]);
                System.out.println("---------------------------------------------------------------");
            }
        }

        System.out.println("(Digite o ID para selecionar!)\n");
        System.out.print("Manutenção que deseja excluir -> ");
        int idManutencaoSelecionado = scanner.nextInt();

        if (idManutencaoSelecionado < 0 || idManutencaoSelecionado >= manutencoes.length
                || manutencoes[idManutencaoSelecionado][0] == null
                || manutencoes[idManutencaoSelecionado][0].isEmpty()) {
            System.out.println("\n[ERRO] - Selecione uma Manutenção válida para fazer a exclusão!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            scanner.nextLine();
            return;
        }

        System.out.println("Você tem certeza que deseja excluir a manutenção de "
                + manutencoes[idManutencaoSelecionado][0] + "? Sim(Y) Não(N). ");
        scanner.nextLine();
        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("y")) {
            System.out.println("\nExclusão cancelada...");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            return;
        }

        String objetoDaManutencao = manutencoes[idManutencaoSelecionado][0];

        for (int j = 0; j < manutencoes[idManutencaoSelecionado].length; j++) {
            manutencoes[idManutencaoSelecionado][j] = ""; // apagando os dados
        }

        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i][0] != null && objetos[i][0].equalsIgnoreCase(objetoDaManutencao)) {
                objetos[i][3] = "DISPONIVEL"; // alterando situacao do objeto para disponivel ja que o emprestimo foi
                                              // excluido
                break;
            }
        }

        System.out.println("Manutenção excluída com sucesso!");
        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine();
    }

    static void excluirEmprestimo() {
        System.out.println("--- Emprestimos ---");
        System.out.println(
                "ID  | Responsável          | Objeto              | Situação               | Data do Emprestimo                 | Data Devolução");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < emprestimos.length; i++) {
            if (emprestimos[i][0] != null && !emprestimos[i][0].isEmpty()) {
                System.out.printf("%-3d | %-15s | %-22s | %-15s | %-15s  | %-10s %n",
                        i, emprestimos[i][0], emprestimos[i][1], emprestimos[i][2], emprestimos[i][3],
                        emprestimos[i][4]);
                System.out.println("---------------------------------------------------------------");
            }
        }

        System.out.println("(Digite o ID para selecionar!)\n");
        System.out.print("Emprestimo que deseja excluir -> ");
        int idEmprestimoSelecionado = scanner.nextInt();

        if (idEmprestimoSelecionado < 0 || idEmprestimoSelecionado >= emprestimos.length
                || emprestimos[idEmprestimoSelecionado][0] == null
                || emprestimos[idEmprestimoSelecionado][0].isEmpty()) {
            System.out.println("\n[ERRO] - Selecione um Emprestimo válida para fazer a exclusão!");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            scanner.nextLine();
            return;
        }

        System.out.println("Você tem certeza que deseja excluir o emprestimo de "
                + emprestimos[idEmprestimoSelecionado][1] + "? Sim(Y) Não(N). ");
        scanner.nextLine();
        String confirmacao = scanner.nextLine();

        if (!confirmacao.equalsIgnoreCase("y")) {
            System.out.println("\nExclusão cancelada...");
            System.out.println("\n[ OK ] - PRESS ENTER\n");
            scanner.nextLine(); // espera a confirmação antes de voltar
            return;
        }

        String objetoDoEmprestimo = emprestimos[idEmprestimoSelecionado][1];

        for (int j = 0; j < emprestimos[idEmprestimoSelecionado].length; j++) {
            emprestimos[idEmprestimoSelecionado][j] = ""; // apagando os dados
        }

        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i][0] != null && objetos[i][0].equalsIgnoreCase(objetoDoEmprestimo)) {
                objetos[i][3] = "DISPONIVEL"; // alterando situacao do objeto para disponivel ja que o emprestimo foi
                                              // excluido
                break;
            }
        }

        System.out.println("Emprestimo excluído com sucesso!");
        System.out.println("\n[ OK ] - PRESS ENTER\n");
        scanner.nextLine();
    }
}
