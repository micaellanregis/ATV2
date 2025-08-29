package spotify;



import java.util.ArrayList;

import java.util.List;



public class Usuario {

    String nome;

    String email;

    List<Playlist> minhasPlaylists = new ArrayList<>();



    public Usuario(String nomeUsuario, String emailUsuario) {

        if (nomeUsuario == null || nomeUsuario.isEmpty() || emailUsuario == null || emailUsuario.isEmpty()) {

            System.out.println("Erro: o nome e o email não podem ser vazios!");

        } else {

            this.nome = nomeUsuario;

            this.email = emailUsuario;

            System.out.println("Usuário " + nome + " criado com sucesso!");

        }

    }



    public void criarPlaylist(String nomePlaylist) throws PlaylistInvalidaException {

        Playlist novaPlaylist = new Playlist(nomePlaylist, this);

        minhasPlaylists.add(novaPlaylist);

        System.out.println("Playlist '" + nomePlaylist + "' criada pra o usuário " + nome + ".");

    }

    

    public Playlist encontrarPlaylist(String nomePlaylist) {

        for (Playlist p : minhasPlaylists) {

            if (p.getNome().equalsIgnoreCase(nomePlaylist)) {

                return p;

            }

        }

        return null;

    }



    public void mostrarPlaylists() {

        System.out.println("--- Playlists do " + nome + " ---");

        if (minhasPlaylists.isEmpty()) {

            System.out.println("Não tem nenhuma playlist ainda.");

        } else {

            for (Playlist p : minhasPlaylists) {

                System.out.println("- " + p.getNome());

            }

        }

    }

    

    public String getNome() {

        return nome;

    }

    

    public String getEmail() {

        return email;

    }

}