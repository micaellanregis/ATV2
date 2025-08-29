package spotify;



import java.util.Objects;



public abstract class Midia {

    String titulo;

    String artista;

    int duracaoEmSegundos;

    Genero genero;



    public Midia(String tituloMidia, String artistaMidia, int duracao, Genero generoMidia) {

        if (tituloMidia == null || tituloMidia.isEmpty() || artistaMidia == null || artistaMidia.isEmpty() || duracao <= 0) {

            System.out.println("Erro: título, artista e duração precisam ser válidos.");

        } else {

            this.titulo = tituloMidia;

            this.artista = artistaMidia;

            this.duracaoEmSegundos = duracao;

            this.genero = generoMidia;

        }

    }



    public String getTitulo() { return titulo; }

    public String getArtista() { return artista; }

    public int getDuracaoEmSegundos() { return duracaoEmSegundos; }

    public Genero getGenero() { return genero; }



    public abstract String exibirDetalhes();



    @Override

    public boolean equals(Object o) {

        if (this == o) {

            return true;

        }

        if (o == null || getClass() != o.getClass()) {

            return false;

        }

        Midia midia = (Midia) o;

        return Objects.equals(titulo, midia.titulo) && Objects.equals(artista, midia.artista);

    }



    @Override

    public int hashCode() {

        return Objects.hash(titulo, artista);

    }

}