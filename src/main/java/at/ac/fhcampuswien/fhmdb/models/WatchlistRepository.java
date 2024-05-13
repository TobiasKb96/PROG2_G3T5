package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

//Providing the database functions
public class WatchlistRepository {
    Dao<WatchlistMovieEntity, String> dao;

    public WatchlistRepository() throws DatabaseException{
        this.dao = DatabaseManager.getDatabaseInstance().getWatchlistDao();
    }

    public List<WatchlistMovieEntity> getWatchlist() throws DatabaseException{
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            throw new DatabaseException("Could not get watchlist");
        }
    }

    public void addToWatchlist(WatchlistMovieEntity entity) throws DatabaseException{
        try {
            dao.create(entity);
        } catch (SQLException e) {
            throw new DatabaseException("Movie already in watchlist");
        }
    }

    public void removeFromWatchlist(String apiId) throws DatabaseException{
        try {
            dao.deleteById(apiId);
        }
        catch (SQLException e) {
            throw new DatabaseException("Could not delete from watchlist");
        }
    }
}
