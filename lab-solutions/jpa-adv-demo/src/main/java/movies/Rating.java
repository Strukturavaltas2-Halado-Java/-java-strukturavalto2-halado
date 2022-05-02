package movies;


import javax.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double value;
    private String username;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Rating() {
    }

    public Rating(double value, String username) {
        this.value = value;
        this.username = username;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "value=" + value +
                ", username='" + username + '\'' +
                '}';
    }
}
