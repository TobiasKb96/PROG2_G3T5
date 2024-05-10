package at.ac.fhcampuswien.fhmdb.models;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

//Providing the database functions
public class WatchlistRepository {
    Dao<WatchlistMovieEntity, Long> dao;

    public WatchlistRepository() throws SQLException {
        this.dao = DatabaseManager.getDatabaseInstance().getWatchlistDao();
    }

    public List<WatchlistMovieEntity> getWatchlist() throws SQLException{
      return dao.queryForAll();
    }

    //To read in UI
    public void addToWatchlist(Movie movie) throws SQLException {
        dao.create(watchlistToEntity(movie));
    }

    public void removeFromMovies(Movie movie) throws SQLException{
        dao.delete(watchlistToEntity(movie));
    }


    private WatchlistMovieEntity watchlistToEntity(Movie movie){
        return new WatchlistMovieEntity(movie.getId(), movie.getTitle(), movie.getDescription(), movie.getGenresListAsString(), movie.getImgUrl(), movie.getReleaseYear(), movie.getLengthInMinutes(), movie.getRating());
    }


}
