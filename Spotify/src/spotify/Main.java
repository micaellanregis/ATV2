package spotify;



import java.util.InputMismatchException;

import java.util.Scanner;

import java.util.List;

import java.util.Arrays;



public class Main {

    private static Catalogo catalogo = new Catalogo();

    private static Scanner scanner = new Scanner(System.in);

    private static Usuario usuarioAtual = null;



    public static void main(String[] args) throws PlaylistInvalidaException, MidiaInvalidaException {

        System.out.println("Bem-vindo ao MusicFy!");



        System.out.println("\nPara começar, por favor, cadastre um usuário.");

        cadastrarUsuario();



        if (usuarioAtual != null) {

            exibirMenuPrincipal();

        }



        scanner.close();

    }



    private static void cadastrarUsuario() {

        System.out.print("Digite seu nome: ");

        String nome = scanner.nextLine();

        System.out.print("Digite seu email: ");

        String email = scanner.nextLine();



        if (nome == null || nome.isEmpty() || email == null || email.isEmpty()) {

            System.out.println("Erro ao cadastrar usuário: Nome e email não podem ser vazios.");

            cadastrarUsuario();

        } else {

            usuarioAtual = new Usuario(nome, email);

            System.out.println("Usuário " + usuarioAtual.getNome() + " cadastrado com sucesso!");

        }

    }



    private static void exibirMenuPrincipal() throws PlaylistInvalidaException, MidiaInvalidaException {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("\n--- MENU PRINCIPAL ---");

            System.out.println("1. Cadastrar nova mídia no catálogo");

            System.out.println("2. Criar uma nova playlist");

            System.out.println("3. Ver minhas playlists");

            System.out.println("4. Adicionar mídia a uma playlist");

            System.out.println("5. Remover mídia de uma playlist");

            System.out.println("6. Buscar mídias no catálogo");

            System.out.println("7. Listar todo o catálogo");

            System.out.println("0. Sair");

            System.out.print("Escolha uma opção: ");



            try {

                opcao = scanner.nextInt();

                scanner.nextLine();



                switch (opcao) {

                    case 1:

                        cadastrarMidia();

                        break;

                    case 2:

                        criarPlaylist();

                        break;

                    case 3:

                        usuarioAtual.mostrarPlaylists();

                        break;

                    case 4:

                        adicionarMidiaAPlaylist();

                        break;

                    case 5:

                        removerMidiaDaPlaylist();

                        break;

                    case 6:

                        buscarMidias();

                        break;

                    case 7:

                        catalogo.listarTodasAsMidias();

                        break;

                    case 0:

                        System.out.println("Saindo do sistema. Até mais!");

                        break;

                    default:

                        System.out.println("Opção inválida. Tente novamente.");

                }

            } catch (InputMismatchException e) {

                System.out.println("Entrada inválida. Por favor, digite um número.");

                scanner.nextLine();

                opcao = -1;

            }

        }

    }



    private static void cadastrarMidia() throws MidiaInvalidaException {

        System.out.println("\n--- Cadastrar Nova Mídia ---");

        System.out.println("Qual tipo de mídia deseja cadastrar?");

        System.out.println("1. Música");

        System.out.println("2. Podcast");

        System.out.println("3. Audiobook");

        System.out.print("Escolha uma opção: ");



        int tipo = scanner.nextInt();

        scanner.nextLine();



        System.out.print("Digite o título: ");

        String titulo = scanner.nextLine();

        System.out.print("Digite o nome do artista/autor/narrador: ");

        String artista = scanner.nextLine();

        System.out.print("Digite a duração em segundos: ");

        int duracao = scanner.nextInt();

        scanner.nextLine();



        System.out.println("Gêneros disponíveis: " + Arrays.toString(Genero.values()));

        System.out.print("Digite o gênero: ");

        String generoStr = scanner.nextLine().toUpperCase();

        Genero genero = null;



        try {

            genero = Genero.valueOf(generoStr);

        } catch (IllegalArgumentException e) {

            System.out.println("Gênero inválido. Mídia não cadastrada.");

            return;

        }



        if (titulo == null || titulo.isEmpty() || artista == null || artista.isEmpty() || duracao <= 0) {

            System.out.println("Dados da mídia inválidos. Mídia não cadastrada.");

            return;

        }



        switch (tipo) {

            case 1:

                catalogo.adicionarMidia(new Musica(titulo, artista, duracao, genero));

                break;

            case 2:

                catalogo.adicionarMidia(new Podcast(titulo, artista, duracao, genero));

                break;

            case 3:

                catalogo.adicionarMidia(new Audiobook(titulo, artista, duracao, genero));

                break;

            default:

                System.out.println("Opção de tipo de mídia inválida.");

        }

    }



    private static void criarPlaylist() throws PlaylistInvalidaException {

        System.out.print("Digite o nome da nova playlist: ");

        String nomePlaylist = scanner.nextLine();

        usuarioAtual.criarPlaylist(nomePlaylist);

    }



    private static void adicionarMidiaAPlaylist() {

        usuarioAtual.mostrarPlaylists();

        System.out.print("Digite o nome da playlist que deseja editar: ");

        String nomePlaylist = scanner.nextLine();

        Playlist playlist = usuarioAtual.encontrarPlaylist(nomePlaylist);



        if (playlist == null) {

            System.out.println("Playlist não encontrada.");

            return;

        }



        buscarMidiasParaAdicionar(playlist);

    }

    

    private static void buscarMidiasParaAdicionar(Playlist playlist) {

        System.out.println("\n--- Buscar Mídia para Adicionar ---");

        System.out.println("1. Buscar por Título");

        System.out.println("2. Buscar por Artista");

        System.out.println("3. Buscar por Gênero");

        System.out.println("0. Voltar");

        System.out.print("Escolha uma opção de busca: ");



        int opcao = scanner.nextInt();

        scanner.nextLine();



        List<Midia> resultados = null;

        switch (opcao) {

            case 1:

                System.out.print("Digite o título: ");

                resultados = catalogo.buscarPorTitulo(scanner.nextLine());

                break;

            case 2:

                System.out.print("Digite o nome do artista: ");

                resultados = catalogo.buscarPorArtista(scanner.nextLine());

                break;

            case 3:

                System.out.print("Digite o gênero (ex: ROCK, POP): ");

                try {

                    Genero genero = Genero.valueOf(scanner.nextLine().toUpperCase());

                    resultados = catalogo.buscarPorGenero(genero);

                } catch (IllegalArgumentException e) {

                    System.out.println("Gênero inválido.");

                    return;

                }

                break;

            case 0:

                return;

            default:

                System.out.println("Opção inválida.");

                return;

        }



        if (resultados.isEmpty()) {

            System.out.println("Nenhum resultado encontrado.");

            return;

        }



        System.out.println("\n--- Resultados da Busca ---");

        for (int i = 0; i < resultados.size(); i++) {

            System.out.println((i + 1) + ". " + resultados.get(i).exibirDetalhes());

        }



        System.out.print("Digite o número da mídia que deseja adicionar (0 para cancelar): ");

        try {

            int escolha = scanner.nextInt();

            if (escolha > 0 && escolha <= resultados.size()) {

                playlist.adicionarMidia(resultados.get(escolha - 1));

            } else if (escolha != 0) {

                System.out.println("Número inválido.");

            }

        } catch (InputMismatchException e) {

            System.out.println("Entrada inválida. Por favor, digite um número.");

        }

        scanner.nextLine();

    }



    private static void removerMidiaDaPlaylist() {

        usuarioAtual.mostrarPlaylists();

        System.out.print("Digite o nome da playlist para remover uma mídia: ");

        String nomePlaylist = scanner.nextLine();

        Playlist playlist = usuarioAtual.encontrarPlaylist(nomePlaylist);



        if (playlist == null) {

            System.out.println("Playlist não encontrada.");

            return;

        }



        playlist.exibirDetalhes();

        System.out.print("Digite o título da mídia que deseja remover: ");

        String titulo = scanner.nextLine();



        Midia midiaParaRemover = null;

        for (Midia m : playlist.getMidias()) {

            if (m.getTitulo().equalsIgnoreCase(titulo)) {

                midiaParaRemover = m;

                break;

            }

        }



        if (midiaParaRemover != null) {

            playlist.removerMidia(midiaParaRemover);

        } else {

            System.out.println("Mídia com o título '" + titulo + "' não encontrada na playlist.");

        }

    }



    private static void buscarMidias() {

        System.out.println("\n--- Buscar Mídias no Catálogo ---");

        System.out.println("1. Buscar por Título");

        System.out.println("2. Buscar por Artista");

        System.out.println("3. Buscar por Gênero");

        System.out.print("Escolha uma opção de busca: ");

        int opcao = scanner.nextInt();

        scanner.nextLine();



        List<Midia> resultados = null;

        switch (opcao) {

            case 1:

                System.out.print("Digite o título: ");

                resultados = catalogo.buscarPorTitulo(scanner.nextLine());

                break;

            case 2:

                System.out.print("Digite o nome do artista: ");

                resultados = catalogo.buscarPorArtista(scanner.nextLine());

                break;

            case 3:

                System.out.print("Digite o gênero (ex: ROCK, POP): ");

                try {

                    Genero genero = Genero.valueOf(scanner.nextLine().toUpperCase());

                    resultados = catalogo.buscarPorGenero(genero);

                } catch (IllegalArgumentException e) {

                    System.out.println("Gênero inválido.");

                    return;

                }

                break;

            default:

                System.out.println("Opção inválida.");

                return;

        }



        if (resultados.isEmpty()) {

            System.out.println("Nenhum resultado encontrado.");

        } else {

            System.out.println("\n--- Resultados da Busca ---");

            for (Midia m : resultados) {

                System.out.println("- " + m.exibirDetalhes());

            }

        }

    }

}