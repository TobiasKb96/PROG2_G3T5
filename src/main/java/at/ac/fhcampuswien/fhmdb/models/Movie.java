package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.controller.MovieAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Movie(String apiID, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, float rating) {
        this.id = apiID;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.lengthInMinutes = lengthInMinutes;
    }

    public String getTitle() {
        try {
            return title;
        }catch (NullPointerException e){
            System.out.println("Could not find Title of Movie\n"+e);
        }
        return null;
    }

    public String getDescription() {
        try {
            return description;
        }catch (NullPointerException e){
            System.out.println("Could not find Description of Movie\n"+e);
        }
        return null;
    }

    public String getId() {
        try {
            return id;
        } catch (NullPointerException e) {
            System.out.println("Could not find ID of Movie\n" + e);
        }
        return null;
    }

    public String getImgUrl() {
        try {
            return imgUrl;
        } catch (NullPointerException e) {
            System.out.println("Could not find PosterUrl of Movie\n" + e);
        }
        return null;
    }

    public int getLengthInMinutes() {
        try {
            return lengthInMinutes;
        } catch (NullPointerException e) {
            System.out.println("Could not find Length of Movie\n" + e);
        }
        return -1;
    }

    public List<Genres> getGenres() {
        try {
            return genres;
        }catch (NullPointerException e){
            System.out.println("Could not find Genres of Movie\n"+e);
        }
        return null;
    }

    public int getReleaseYear() {
        try {
            return releaseYear;
        }catch (NullPointerException e){
            System.out.println("Could not find ReleaseYear of Movie\n"+e);
        }
        return -1;
    }

    public float getRating() {
        try {
            return rating;
        }catch (NullPointerException e){
            System.out.println("Could not find Ratings of Movie\n"+e);
        }
        return -1;
    }

    public List<String> getMainCast() {
        try {
            return mainCast;
        }catch (NullPointerException e){
            System.out.println("Could not find Main Cast of Movie\n"+e);
        }
        return null;
    }

    public List<String> getDirectors() {
        try {
            return directors;
        }catch (NullPointerException e){
            System.out.println("Could not find Directors of Movie\n"+e);
        }
        return null;
    }

    public String getGenresListAsString() {
        try {
            StringBuilder out = new StringBuilder();

            for (Genres g : genres) {
                out.append(g.toString() + ", ");
            }
            out.delete(out.length() - 2, out.length());

            return out.toString();
        }catch (NullPointerException e){
            System.out.println("Could not get Genres\n"+e);
        }
        return null;
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
