package at.ac.fhcampuswien.fhmdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Contains links to the movies in the watchlist, e.g. the ApiId of the selected MovieEntities

@DatabaseTable(tableName = "watchlist")
public class WatchlistMovieEntity{
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(unique = true)
    private String apiId;

        public  WatchlistMovieEntity(){
    }
    public WatchlistMovieEntity(String apiId) {
        this.apiId = apiId;
    }
}

