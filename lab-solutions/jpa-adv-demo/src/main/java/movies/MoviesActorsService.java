package movies;

import java.util.List;

public class MoviesActorsService {

    private ActorRepository actorRepository;
    private MovieRepository movieRepository;

    public MoviesActorsService(ActorRepository actorRepository, MovieRepository movieRepository) {
        this.actorRepository = actorRepository;
        this.movieRepository = movieRepository;
    }

    public void saveMovieWithActors(Movie movie, List<Actor> actors){
        List<String> inDbActors = actorRepository.findActorsByName(actors.stream().map(Actor::getName).toList());
        for(Actor actor : actors){
            if(!inDbActors.contains(actor.getName())){
                actorRepository.saveActor(actor);
            }
            movie.addActor(actor);
        }

        movieRepository.saveMovie(movie);
    }

}
