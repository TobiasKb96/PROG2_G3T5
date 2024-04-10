package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.controller.MovieAPI;

import java.util.List;
import java.util.Map;

//TODO Konstantin integrate new filter fields
public class Movie {
    public List<Genres> genres;
    private String id, title, description, imgUrl;
    private List<String> directors, writers, mainCast;
    private float rating;
    private  int releaseYear, lengthInMinutes;

    public Movie(String title, String description, List<Genres> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public Movie(List<Genres> genres, String id, String title, String description, String imgUrl, List<String> directors, List<String> writers, List<String> mainCast, float rating, int releaseYear, int lengthInMinutes) {
        this.genres = genres;
        this.id = id;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.lengthInMinutes = lengthInMinutes;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genres> getGenres() {
        return genres;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public float getRating() {
        return rating;
    }

    public String getGenresListAsString() {
        StringBuilder out = new StringBuilder();

        for (Genres g : genres) {
            out.append(g.toString() + ", ");
        }
        out.delete(out.length() - 2, out.length());

        return out.toString();
    }

    public static List<Movie> initializeMovies(Map<String, String> params){
        MovieAPI movieAPI = new MovieAPI();
        movieAPI.setUrl(params);
        return movieAPI.jsonToMovieList(movieAPI.apiQuery());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        else if (!(o instanceof Movie))
            return false;
        else if (((Movie) o).title.equals(this.title) &&
                ((Movie) o).description.equals(this.description)){
            for(int i = 0; i<((Movie) o).genres.size(); i++) {
                if(!((Movie) o).genres.get(i).equals(this.genres.get(i)))
                    return false;
            }
            return true;
        }
        else
            return false;

    }
}
