package at.ac.fhcampuswien.fhmdb.models;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

//Providing the database functions
public class WatchlistRepository {
    Dao<WatchlistMovieEntity, Long> dao;
    MovieRepository movieRepository;

    public WatchlistRepository(){
        this.dao = DatabaseManager.getDatabaseInstance().getWatchlistDao();
    }

    public List<WatchlistMovieEntity> getWatchlist() throws SQLException{
      return dao.queryForAll();
    }

    public void addToWatchlist(WatchlistMovieEntity entity) throws SQLException {
        dao.createIfNotExists(entity);
    }

    public void removeFromMovies(String apiId){
        try {
            List<WatchlistMovieEntity> movies = dao.queryForEq("apiId", apiId);
            if (!movies.isEmpty()) {
                dao.delete(movies);
            } else {
                System.out.println("No movie found with apiId: " + apiId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
