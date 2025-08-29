package spotify;



public class Podcast extends Midia {

	

	

    public Podcast(String titulo, String artista, int duracaoEmSegundos, Genero genero) throws MidiaInvalidaException {

        super(titulo, artista, duracaoEmSegundos, genero);

    }



    @Override

    public String exibirDetalhes() {

        return "Podcast: " + getTitulo() + " - Apresentador: " + getArtista() + " (" + getDuracaoEmSegundos() + "s) - Tema: " + getGenero();

    }

}