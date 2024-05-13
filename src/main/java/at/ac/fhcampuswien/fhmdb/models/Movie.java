package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.controller.MovieAPI;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;

import java.util.*;

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

    public Movie(String id, String title, String description, List<Genres> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.id = id;
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

    //Constructor for Database (MovieEntity)
    public Movie(String id, String title, String description, List<Genres> genres, int releaseYear, String imgUrl, int lengthInMinutes, float rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.lengthInMinutes = lengthInMinutes;
    }

    public Movie(Movie movie) {
    }

    public String getTitle() throws NullPointerException{
            return title;
    }

    public String getDescription() throws NullPointerException{
            return description;
    }

    public String getId() throws NullPointerException{
            return id;
    }

    public String getImgUrl() throws NullPointerException{
            return imgUrl;
    }

    public int getLengthInMinutes() throws NullPointerException{
            return lengthInMinutes;
    }

    public List<Genres> getGenres() throws NullPointerException{
            return genres;
    }

    public int getReleaseYear() throws NullPointerException{
            return releaseYear;
    }

    public float getRating() throws NullPointerException{
            return rating;
    }

    public List<String> getMainCast() throws NullPointerException{
            return mainCast;
    }

    public List<String> getDirectors() throws NullPointerException{
            return directors;
    }

    public String getGenresListAsString() throws NullPointerException{
            StringBuilder out = new StringBuilder();

            for (Genres g : genres) {
                out.append(g.toString() + ", ");
            }
            out.delete(out.length() - 2, out.length());

            return out.toString();
    }

    public static List<Movie> initializeMovies(Map<String, Object> params) throws MovieAPIException {
        MovieAPI movieAPI = new MovieAPI();
        return movieAPI.jsonToMovieList(movieAPI.apiQuery(params));
    }

    public static List<Movie> initializeMovies() throws MovieAPIException{
        MovieAPI movieAPI = new MovieAPI();
        Map<String, Object> emptyMap = new HashMap<>();
        return movieAPI.jsonToMovieList(movieAPI.apiQuery(emptyMap));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        else if (!(o instanceof Movie))
            return false;
        else if (((Movie) o).id.equals(this.id)) {
            return true;
        } else
            return false;

    }
}
