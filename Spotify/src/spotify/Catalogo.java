package spotify;



import java.util.ArrayList;

import java.util.List;





public class Catalogo {

    private List<Midia> midias;



    public Catalogo() {

        this.midias = new ArrayList<>();

    }



    public void adicionarMidia(Midia midia) {

        if (midia == null) {

            System.out.println("Erro: a mídia não pode ser nula.");

            return;

        }

        

        boolean jaExiste = false;

        for (Midia m : midias) {

            if (m.equals(midia)) {

                jaExiste = true;

                break;

            }

        }

        

        if (jaExiste) {

            System.out.println("Mídia '" + midia.getTitulo() + "' já existe no catálogo.");

        } else {

            midias.add(midia);

            System.out.println("Mídia '" + midia.getTitulo() + "' adicionada ao catálogo.");

        }

    }



    public List<Midia> buscarPorTitulo(String titulo) {

        List<Midia> resultados = new ArrayList<>();

        for (Midia m : midias) {

            if (m.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {

                resultados.add(m);

            }

        }

        return resultados;

    }



    public List<Midia> buscarPorArtista(String artista) {

        List<Midia> resultados = new ArrayList<>();

        for (Midia m : midias) {

            if (m.getArtista().toLowerCase().contains(artista.toLowerCase())) {

                resultados.add(m);

            }

        }

        return resultados;

    }



    public List<Midia> buscarPorGenero(Genero genero) {

        List<Midia> resultados = new ArrayList<>();

        for (Midia m : midias) {

            if (m.getGenero() == genero) {

                resultados.add(m);

            }

        }

        return resultados;

    }



    public void listarTodasAsMidias() {

        if (midias.isEmpty()) {

            System.out.println("O catálogo está vazio.");

            return;

        }

        System.out.println("--- Catálogo de Mídias ---");

        for (Midia m : midias) {

            System.out.println(m.exibirDetalhes());

        }

    }

}
