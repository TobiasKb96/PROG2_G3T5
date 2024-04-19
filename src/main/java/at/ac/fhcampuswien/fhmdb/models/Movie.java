package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.controller.MovieAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//DONE Konstantin integrate new filter fields
public class Movie {
    public List<Genres> genres;
    private String id, title, description, imgUrl;
    private List<String> directors, writers, mainCast;
    private float rating;
    private int releaseYear, lengthInMinutes;

    public Movie(String title, String description, List<Genres> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }
    public Movie(String id,String title, String description, List<Genres> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.id=id;
    }

    public Movie(String id, String title, String description, List<Genres> genres, String imgUrl, List<String> directors, List<String> writers, List<String> mainCast, float rating, int releaseYear, int lengthInMinutes) {
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

    public List<String> getMainCast() {
        return mainCast;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public String getGenresListAsString() {
        StringBuilder out = new StringBuilder();

        for (Genres g : genres) {
            out.append(g.toString() + ", ");
        }
        out.delete(out.length() - 2, out.length());

        return out.toString();
    }

    public static List<Movie> initializeMovies(Map<String, Object> params) {
        MovieAPI movieAPI = new MovieAPI();
        return movieAPI.jsonToMovieList(movieAPI.apiQuery(params));
    }

    public static List<Movie> initializeMovies() {
        MovieAPI movieAPI = new MovieAPI();
        Map<String, Object> emptyMap = new HashMap<>();
        return movieAPI.jsonToMovieList(movieAPI.apiQuery(emptyMap));
    }
        @Override
        public boolean equals (Object o){
            if (o == this)
                return true;
            else if (!(o instanceof Movie))
                return false;
            else if (((Movie) o).id.equals(this.id)){
                return true;
            } else
                return false;

        }
    }
