package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.controller.HomeController;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;
import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    @Test
    void test_if_the_sort_function_works_asc() {
        //                         ID           Title                Description                                   Genres                 ImgURL                                 Directors                                      Writers                                       Actors               rating         ReleaseYear         Length
        Movie movie1=new Movie("An ID1","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie2=new Movie("An ID2","C Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie3=new Movie("An ID3","B Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Joe","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie4=new Movie("An ID4","D Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("John","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie5=new Movie("An ID5","E Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);

        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = new ArrayList<>(List.of(movie1,movie2,movie3,movie5,movie4));
        List<Movie> expectedList = new ArrayList<>(List.of(movie1,movie3,movie2,movie4,movie5));

        //when
        controller.sortMovies_asc(actualList);

        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void test_if_the_sort_function_works_dsc() {
        //given
        //                         ID           Title                Description                                   Genres                 ImgURL                                 Directors                                      Writers                                       Actors               rating         ReleaseYear         Length
        Movie movie1=new Movie("An ID1","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie2=new Movie("An ID2","C Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie3=new Movie("An ID3","B Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Joe","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie4=new Movie("An ID4","D Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("John","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie5=new Movie("An ID5","E Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);

        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = new ArrayList<>(List.of(movie1,movie2,movie3,movie5,movie4));
        List<Movie> expectedList = new ArrayList<>(List.of(movie5,movie4,movie2,movie3,movie1));
        //when
        controller.sortMovies_dsc(actualList);

        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void test_for_searchBtnAction() {
        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = null;
        try {
            actualList = Movie.initializeMovies();
        } catch (MovieAPIException e) {
            throw new RuntimeException(e);
        }
        Genres genreToFilter = Genres.ACTION;
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("399f8e7e-7ab7-4e22-94db-3d068fba2ac2","Avatar",
                "A paraplegic Marine dispatched to the moon Pandora on a unique mission" +
                        " becomes torn between following his orders and protecting the world he feels is his home.",
                new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE,Genres.FANTASY,Genres.SCIENCE_FICTION))));
        //when
        actualList = controller.searchBtnAction(genreToFilter, "Ava","2009","7.8");
        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void test_for_searchBtnAction_null_input() {
        //given
        HomeController controller = new HomeController();
        Map<String, Object> emptyMap = new HashMap<>();
        List<Movie> actualList = null;
        List<Movie> expectedList=null;
        try {
            actualList = Movie.initializeMovies(emptyMap);
            expectedList = Movie.initializeMovies(emptyMap);
        } catch (MovieAPIException e) {
            throw new RuntimeException(e);
        }
        //when
        controller.searchBtnAction(null, null, null, null);
        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void most_frequently_occurring_person(){
        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = null;
        try {
            actualList = Movie.initializeMovies();
        } catch (MovieAPIException e) {
            throw new RuntimeException(e);
        }
        String expectedActor = "Leonardo DiCaprio";
        //when
        String actualActor = controller.getMostPopularActor(actualList);
        //then
        assertEquals(expectedActor, actualActor);
    }

    @Test
    void number_of_letters_of_longest_movie_title(){
        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = null;
        try {
            actualList = Movie.initializeMovies();
        } catch (MovieAPIException e) {
            throw new RuntimeException(e);
        }
        //Star Wars: Episode V - The Empire Strikes Back
        int expectedTitleLength = 46;
        //when
        int actualTitleLength = controller.getLongestMovieTitle(actualList);
        //then
        assertEquals(expectedTitleLength, actualTitleLength);
    }

    @Test
    void countMoviesFromSteve(){
        HomeController controller=new HomeController();
        //                         ID           Title                Description                                   Genres                 ImgURL                                 Directors                                      Writers                                       Actors               rating         ReleaseYear         Length
        Movie movie1=new Movie("An ID1","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie2=new Movie("An ID2","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie3=new Movie("An ID3","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Joe","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie4=new Movie("An ID4","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("John","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie5=new Movie("An ID5","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        List<Movie> test = new ArrayList<>(List.of(movie1, movie2, movie3, movie4, movie5));

        long expected=3,actual;
        actual=controller.countMoviesFrom(test,"Steve");
        assertEquals(actual,expected);
    }

    @Test
    void countMoviesFromJoe(){
        HomeController controller=new HomeController();
        //                         ID           Title                Description                                   Genres                 ImgURL                                 Directors                                      Writers                                       Actors               rating         ReleaseYear         Length
        Movie movie1=new Movie("An ID1","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie2=new Movie("An ID2","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie3=new Movie("An ID3","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Joe","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie4=new Movie("An ID4","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("John","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie5=new Movie("An ID5","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        List<Movie> test = new ArrayList<>(List.of(movie1, movie2, movie3, movie4, movie5));

        long expected=1,actual;
        actual=controller.countMoviesFrom(test,"Joe");
        assertEquals(actual,expected);
    }

    @Test
    void countMoviesFromNoOne(){
        HomeController controller=new HomeController();
        //                         ID           Title                Description                                   Genres                 ImgURL                                 Directors                                      Writers                                       Actors               rating         ReleaseYear         Length
        Movie movie1=new Movie("An ID1","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie2=new Movie("An ID2","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie3=new Movie("An ID3","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Joe","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie4=new Movie("An ID4","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("John","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        Movie movie5=new Movie("An ID5","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2100,120);
        List<Movie> test = new ArrayList<>(List.of(movie1, movie2, movie3, movie4, movie5));

        long expected=0,actual;
        actual=controller.countMoviesFrom(test,"NoOne");
        assertEquals(actual,expected);
    }

    @Test
    void getMoviesThreeToNine(){
        HomeController controller=new HomeController();
        //                         ID           Title                Description                                   Genres                 ImgURL                                 Directors                                      Writers                                       Actors               rating         ReleaseYear         Length
        Movie movie1=new Movie("An ID1","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,4,120);
        Movie movie2=new Movie("An ID2","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,7,120);
        Movie movie3=new Movie("An ID3","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Joe","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,9,120);
        Movie movie4=new Movie("An ID4","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("John","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2,120);
        Movie movie5=new Movie("An ID5","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,4,120);
        //4,7,9,2,4

        List<Movie> expected = new ArrayList<>(List.of(movie1,movie2, movie3, movie5));
        List<Movie> actual = controller.getMoviesBetweenYears(List.of(movie1,movie2,movie3,movie4,movie5),3,9);

        assertEquals(expected,actual);
    }

    @Test
    void getMoviesSeven(){
        HomeController controller=new HomeController();
        //                         ID           Title                Description                                   Genres                 ImgURL                                 Directors                                      Writers                                       Actors               rating         ReleaseYear         Length
        Movie movie1=new Movie("An ID1","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,4,120);
        Movie movie2=new Movie("An ID2","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,7,120);
        Movie movie3=new Movie("An ID3","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Joe","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,9,120);
        Movie movie4=new Movie("An ID4","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("John","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2,120);
        Movie movie5=new Movie("An ID5","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,4,120);
        //4,7,9,2,4

        List<Movie> expected = new ArrayList<>(List.of(movie2));
        List<Movie> actual = controller.getMoviesBetweenYears(List.of(movie1,movie2,movie3,movie4,movie5),7,7);

        assertEquals(expected,actual);
    }

    @Test
    void getMoviesSixToOver(){
        HomeController controller=new HomeController();
        //                         ID           Title                Description                                   Genres                 ImgURL                                 Directors                                      Writers                                       Actors               rating         ReleaseYear         Length
        Movie movie1=new Movie("An ID1","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,4,120);
        Movie movie2=new Movie("An ID2","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,7,120);
        Movie movie3=new Movie("An ID3","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Joe","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,9,120);
        Movie movie4=new Movie("An ID4","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("John","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2,120);
        Movie movie5=new Movie("An ID5","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,4,120);
        //4,7,9,2,4

        List<Movie> expected = new ArrayList<>(List.of(movie2,movie3));
        List<Movie> actual = controller.getMoviesBetweenYears(List.of(movie1,movie2,movie3,movie4,movie5),6,200);

        assertEquals(expected,actual);
    }

    @Test
    void getMoviesNegativToFive(){
        HomeController controller=new HomeController();
        //                         ID           Title                Description                                   Genres                 ImgURL                                 Directors                                      Writers                                       Actors               rating         ReleaseYear         Length
        Movie movie1=new Movie("An ID1","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,4,120);
        Movie movie2=new Movie("An ID2","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,7,120);
        Movie movie3=new Movie("An ID3","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Joe","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,9,120);
        Movie movie4=new Movie("An ID4","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("John","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,2,120);
        Movie movie5=new Movie("An ID5","A Movie","A Description",new ArrayList<Genres>(List.of(Genres.ACTION)),"ImgURL",new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),new ArrayList<String>(List.of("Steve","Bob")),5.5f,4,120);
        //4,7,9,2,4

        List<Movie> expected = new ArrayList<>(List.of(movie1, movie4,movie5));
        List<Movie> actual = controller.getMoviesBetweenYears(List.of(movie1,movie2,movie3,movie4,movie5),-10,5);

        assertEquals(expected,actual);
    }
}

