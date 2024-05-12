package at.ac.fhcampuswien.fhmdb.models;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Providing the database functions
public class MovieRepository {
    Dao<MovieEntity, Long> dao;

    public MovieRepository() throws SQLException {
        this.dao = DatabaseManager.getDatabaseInstance().getMovieDao();
    }

    public List<MovieEntity> getAllMovies() throws SQLException{
      return dao.queryForAll();
    }

    public int removeAll() throws SQLException {
        dao.delete(getAllMovies());
        return 1;
    }

    public MovieEntity getMovie(String movieId) throws SQLException {
        List<MovieEntity> movieEntityList = dao.queryForAll();
        for (MovieEntity movieEntity : movieEntityList) {
            if(movieEntity.getApiId().equals(movieId)) return movieEntity;
        }
        //return this.dao.queryForId(movieID);
        return null;
    }

    public int addAllMovies(List<Movie> movies) throws SQLException {
        int count = 0;
        for(Movie movie : movies){
            dao.createIfNotExists(movieToEntity(movie));
            count ++;
        }
        return count;
    }

    public void addToMovies(Movie movie) throws SQLException {
        dao.createIfNotExists(movieToEntity(movie));
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
