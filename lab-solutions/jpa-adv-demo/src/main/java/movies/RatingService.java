package movies;

public class RatingService {

    private RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public void saveNewRating(Movie movie, Rating rating){
        if(ratingRepository.findRatingByUsernameOnMovie(movie.getId(),rating.getUsername())>0){
            throw new IllegalArgumentException("You already rated this movie!");
        }
        ratingRepository.saveRating(rating, movie.getId());
    }


}
