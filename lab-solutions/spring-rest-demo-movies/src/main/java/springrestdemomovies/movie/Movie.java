package springrestdemomovies.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Movie {

    private Long id;
    private String title;
    private int length;
    private List<Integer> ratings = new ArrayList<>();
    private double averageRating;

    public Movie(Long id, String title, int length) {
        this.id = id;
        this.title = title;
        this.length = length;
    }
}
