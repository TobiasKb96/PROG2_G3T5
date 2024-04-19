package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MovieAPITest {

    @Test
    void setUrlAllParams() {
        //given
        MovieAPI movieAPI = new MovieAPI();

        String expectedUrl = "https://prog2.fh-campuswien.ac.at/movies?query=Avatar&genre=ACTION&ratingFrom=8.6&releaseYear=2019";
        Map<String, Object> testMap = new HashMap<>();
        //when
        testMap.put("query", "Avatar");
        testMap.put("genre", "ACTION");
        testMap.put("ratingFrom", "8.6");
        testMap.put("releaseYear", "2019");
        movieAPI.setUrl(testMap);
        String actualUrl = movieAPI.getQueryUrl();
        //then
        assertEquals(expectedUrl, actualUrl);
    }
    @Test
    void setUrlNoParams() {
        //given
        MovieAPI movieAPI = new MovieAPI();

        String expectedUrl = "https://prog2.fh-campuswien.ac.at/movies";
        Map<String, Object> testMap = new HashMap<>();
        //when
        testMap.put("query", null);
        testMap.put("genre", "");
        testMap.put("ratingFrom", null);
        testMap.put("releaseYear", "No_Filter");
        movieAPI.setUrl(testMap);
        String actualUrl = movieAPI.getQueryUrl();
        //then
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    void apiQueryForOneMovieAllParams() {
        //given
        MovieAPI movieAPI = new MovieAPI();

        String expectedResponse = "[{\"id\":\"399f8e7e-7ab7-4e22-94db-3d068fba2ac2\",\"title\":\"Avatar\",\"genres\":[\"ACTION\",\"ADVENTURE\",\"FANTASY\",\"SCIENCE_FICTION\"],\"releaseYear\":2009,\"description\":\"A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.\",\"imgUrl\":\"https://m.media-amazon.com/images/M/MV5BZDA0OGQxNTItMDZkMC00N2UyLTg3MzMtYTJmNjg3Nzk5MzRiXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_FMjpg_UX1000_.jpg\",\"lengthInMinutes\":162,\"directors\":[\"James Cameron\"],\"writers\":[\"James Cameron\"],\"mainCast\":[\"Sam Worthington\",\"Zoe Saldana\",\"Sigourney Weaver\"],\"rating\":7.8}]";
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("query", "Avatar");
        testMap.put("genre", "ACTION");
        testMap.put("ratingFrom", "4.6");
        testMap.put("releaseYear", "2009");

        //when

        String actualResponse = movieAPI.apiQuery(testMap);
        //then
        assertEquals(expectedResponse, actualResponse);
    }
    @Test
    void apiQueryWithNoHit() {
        //given
        MovieAPI movieAPI = new MovieAPI();

        String expectedResponse = "[]";
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("query", "Avatar");
        testMap.put("genre", "");
        testMap.put("ratingFrom", "9.5");
        testMap.put("releaseYear", "1900");
        //when
        String actualResponse = movieAPI.apiQuery(testMap);
        //then
        assertEquals(expectedResponse, actualResponse);
    }
    @Test
    void jsonToMovieList() {
        List<Movie> expectedMovies = new ArrayList<>();
        MovieAPI movieAPI = new MovieAPI();
        //TestMap
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("query", "Avatar");
        testMap.put("genre", "ACTION");
        testMap.put("ratingFrom", "4.6");
        testMap.put("releaseYear", "2009");
        expectedMovies.add(new Movie(
                "399f8e7e-7ab7-4e22-94db-3d068fba2ac2",
                "Avatar",
                "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                new ArrayList<>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.FANTASY, Genres.SCIENCE_FICTION)),
                "https://m.media-amazon.com/images/M/MV5BZDA0OGQxNTItMDZkMC00N2UyLTg3MzMtYTJmNjg3Nzk5MzRiXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_FMjpg_UX1000_.jpg",
                new ArrayList<>(List.of("James Cameron")),
                new ArrayList<>(List.of("James Cameron")),
                new ArrayList<>(List.of("Sam Worthington", "Zoe Saldana", "Sigourney Weaver")),
                7.8f,
                2009,
                162
        ));
        //when
        List<Movie> actualMovies = new ArrayList<>();
        actualMovies = movieAPI.jsonToMovieList(movieAPI.apiQuery(testMap));
        //then
        assertEquals(expectedMovies, actualMovies);
    }
}