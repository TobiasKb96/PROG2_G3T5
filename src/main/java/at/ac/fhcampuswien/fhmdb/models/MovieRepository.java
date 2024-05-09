package at.ac.fhcampuswien.fhmdb.models;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

//Providing the database functions
public class MovieRepository {
    Dao<MovieEntity, Long> dao;

    public MovieRepository() throws SQLException {
        this.dao = DatabaseManager.getDatabaseInstance().getMovieDao();
    }

    public void addToMovies(Movie movie) throws SQLException {
        dao.create(movieToEntity(movie));
    }

    public void removeFromMovies(Movie movie) throws SQLException{
        dao.delete(movieToEntity(movie));
    }

    //To read in UI
    public List<MovieEntity> readAllMovies() throws SQLException{
        return dao.queryForAll();
    }

    private MovieEntity movieToEntity(Movie movie){
        return new MovieEntity(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenresListAsString(), movie.getImgUrl(), movie.getReleaseYear(), movie.getLengthInMinutes(), movie.getRating());
    }
}
