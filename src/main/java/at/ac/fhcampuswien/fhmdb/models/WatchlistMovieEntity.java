package at.ac.fhcampuswien.fhmdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

//Contains links to the movies in the watchlist, e.g. the ApiId of the selected MovieEntities

@DatabaseTable(tableName = "watchlist")
public class WatchlistMovieEntity{
//    @DatabaseField()
//    private long id;

    @DatabaseField(id = true)
    private String apiId;

    public  WatchlistMovieEntity(){
    }
    public WatchlistMovieEntity(String apiId) {
        this.apiId = apiId;
    }

    public String getApiId() {
        return apiId;
    }

}

