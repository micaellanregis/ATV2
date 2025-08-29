package spotify;



public class Musica extends Midia {

    public Musica(String titulo, String artista, int duracaoEmSegundos, Genero genero) throws MidiaInvalidaException {

        super(titulo, artista, duracaoEmSegundos, genero);

    }



    @Override

    public String exibirDetalhes() {

        return "Música: " + getTitulo() + " - " + getArtista() + " (" + getDuracaoEmSegundos() + "s) - Gênero: " + getGenero();

    }

}