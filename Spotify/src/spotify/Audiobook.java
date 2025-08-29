package spotify;



public class Audiobook extends Midia {

    public Audiobook(String titulo, String artista, int duracaoEmSegundos, Genero genero) throws MidiaInvalidaException {

        super(titulo, artista, duracaoEmSegundos, genero);

    }



    @Override

    public String exibirDetalhes() {

        return "Audiobook: " + getTitulo() + " - Narrador: " + getArtista() + " (" + getDuracaoEmSegundos() + "s) - Categoria: " + getGenero();

    }

}