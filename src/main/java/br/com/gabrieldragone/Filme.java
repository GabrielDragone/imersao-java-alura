package br.com.gabrieldragone;

public class Filme {

    private Integer rank;
    private String title;
    private String image;
    private Double imDbRating;

    public Filme(Integer rank, String title, String image, Double imDbRating) {
        this.rank = rank;
        this.title = title;
        this.image = image;
        this.imDbRating = imDbRating;
    }
}
