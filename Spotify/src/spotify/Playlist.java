package spotify;



import java.util.ArrayList;

import java.util.List;



public class Playlist {

    String nome;

    Usuario proprietario;

    List<Midia> minhasMidias;



    public Playlist(String nomePlaylist, Usuario dono) {

        if (nomePlaylist == null || nomePlaylist.isEmpty() || dono == null) {

            System.out.println("Erro: nome da playlist ou proprietário não podem ser vazios.");

        } else {

            this.nome = nomePlaylist;

            this.proprietario = dono;

            this.minhasMidias = new ArrayList<>();

        }

    }



    public void adicionarMidia(Midia novaMidia) {

        if (novaMidia != null) {

            minhasMidias.add(novaMidia);

            System.out.println("Mídia '" + novaMidia.getTitulo() + "' adicionada à playlist '" + nome + "'.");

        } else {

            System.out.println("Erro: a mídia que você tentou adicionar é nula.");

        }

    }



    public void removerMidia(Midia midiaPraRemover) {

        if (midiaPraRemover != null) {

            boolean removeu = false;

            for (int i = 0; i < minhasMidias.size(); i++) {

                if (minhasMidias.get(i).getTitulo().equals(midiaPraRemover.getTitulo())) {

                    minhasMidias.remove(i);

                    removeu = true;

                    break;

                }

            }

            if (removeu) {

                System.out.println("Mídia '" + midiaPraRemover.getTitulo() + "' removida da playlist '" + nome + "'.");

            } else {

                System.out.println("Mídia '" + midiaPraRemover.getTitulo() + "' não encontrada na playlist '" + nome + "'.");

            }

        } else {

            System.out.println("Erro: a mídia que você tentou remover é nula.");

        }

    }



    public int calcularDuracaoTotal() {

        int duracaoTotal = 0;

        for (Midia m : minhasMidias) {

            duracaoTotal += m.getDuracaoEmSegundos();

        }

        return duracaoTotal;

    }



    public void exibirDetalhes() {

        System.out.println("\n--- Playlist: " + nome + " ---");

        System.out.println("Proprietário: " + proprietario.getNome());

        System.out.println("Duração Total: " + calcularDuracaoTotal() + " segundos");

        if (minhasMidias.isEmpty()) {

            System.out.println("A playlist está vazia.");

        } else {

            System.out.println("Mídias:");

            for (Midia m : minhasMidias) {

                m.exibirDetalhes();

            }

        }

    }



    public List<Midia> getMidias() {

        return minhasMidias;

    }

    

    public String getNome() { 

    	return nome;

    }

    

    public Usuario getProprietario() { 

    	return proprietario; 

    }

}