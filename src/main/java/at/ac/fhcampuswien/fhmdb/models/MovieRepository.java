package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Providing the database functions
public class MovieRepository {
    Dao<MovieEntity, Long> dao;

    public MovieRepository() throws DatabaseException {
        this.dao = DatabaseManager.getDatabaseInstance().getMovieDao();
    }

    public List<MovieEntity> getAllMovies() throws DatabaseException{
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException("Could not get all movies");
        }
    }

    public int removeAll() throws DatabaseException {
        try {
            dao.delete(getAllMovies());
        } catch (SQLException e) {
            throw new DatabaseException("Could not removeAll");
        }
        return 1;
    }

    public MovieEntity getMovie(String movieId) throws DatabaseException {
        try {
            List<MovieEntity> movieEntityList = dao.queryForAll();
            for (MovieEntity movieEntity : movieEntityList) {
                if (movieEntity.getApiId().equals(movieId)) return movieEntity;
            }
        }
        catch(SQLException e){
            throw new DatabaseException("Could not get Movie");
        }
        return new MovieEntity();
    }

    public int addAllMovies(List<Movie> movies) throws DatabaseException {
        int count = 0;
        try {
            for (Movie movie : movies) {
                dao.createIfNotExists(movieToEntity(movie));
                count++;
            }
        }
        catch (SQLException e){
            throw new DatabaseException("Could not add all movies");
        }
        return count;
    }

    public void addToMovies(Movie movie) throws DatabaseException {
        try {
            dao.createIfNotExists(movieToEntity(movie));
        } catch (SQLException e) {
            throw new DatabaseException("Could not add to Movies");
        }
    }


    public static MovieEntity movieToEntity(Movie movie){
        return new MovieEntity(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenresListAsString(), movie.getImgUrl(), movie.getReleaseYear(), movie.getLengthInMinutes(), movie.getRating());
    }

    public static Movie entityToMovie(MovieEntity entity){
        return new Movie(entity.getApiId(), entity.getTitle(), entity.getDescription(), getStringAsGenreList(entity.getGenres()), entity.getReleaseYear(), entity.getImgUrl(), entity.getLengthInMinutes(), (float) entity.getRating());
    }

    public static List<Genres> getStringAsGenreList(String genreString) {
        String[] inputGenresArray = genreString.split(",");
        List<Genres> outputGenreList = new ArrayList<>();
        Genres tmp;
        for(String genre : inputGenresArray){
            try{
               tmp=Genres.valueOf(genre.trim());
                           outputGenreList.add(tmp);
            }catch (IllegalArgumentException ilae){
                System.out.println("Wrong Genre Type"+ilae+"\n"+genre);
            }
        }
        return outputGenreList;
    }
}
