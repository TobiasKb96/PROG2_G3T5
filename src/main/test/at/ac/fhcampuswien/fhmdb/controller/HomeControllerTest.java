package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.controller.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

//TODO Julian create new tests for the stream methods
class HomeControllerTest {

    //for testing JUnit
    @Test
    void GenreFilterAction() {
        List<Movie> toTest = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("Avatar", "A paraplegic ...", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE))));
        expected.add(new Movie("Herr der Ringe: Die Rückkehr des Königs", "Gandalf und Aragorn führen die Männer der Mittelerde in den Kampf gegen Saurons Armee, um ihn von Frodo und Sam abzulenken, die sich gerade dem Schicksalsberg mit dem Einen Ring nähern.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.DRAMA, Genres.FANTASY))));
        expected.add(new Movie("Fight Club", "Ein unter Schlaflosigkeit leidender Büroangestellter sucht nach einer Möglichkeit, sein Leben zu ändern, und trifft dabei auf einen sorglosen Seifenhändler, der im Untergrund einen Kampfclub unterhält, der sich als etwas noch viel Größeres herausstellt.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.DRAMA))));
        expected.add(new Movie("Star Wars: Episode V", "Nachdem die Rebellen auf ihrem neuen Stützpunkt brutal vom Imperium überwältigt worden sind, macht Luke Skywalker bei Meister Yoda einen Jedi-Kurs für Fortgeschrittene, während seine Freunde von Darth Vader verfolgt werden, der dadurch Luke gefangen zu nehmen hofft.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.SCIENCE_FICTION))));
        expected.add(new Movie("Matrix", "Ein Computerhacker erfährt von mysteriösen Rebellen die Wahrheit über seine Realität und seine Rolle im Krieg gegen deren Kontrolleure.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.SCIENCE_FICTION))));
        expected.add(new Movie("Spider-Man: Across the Spider-Verse", "Miles Morales kehrt im 2. Teil der Spider-Verse-Saga zurück, reist durch das Multiversum und trifft auf ein Team von Spider-People. Uneinig über den Umgang mit einer neuen Bedrohung definiert Miles neu, was es bedeutet, ein Held zu sein.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.ANIMATION))));

        HomeController controller = new HomeController();
        controller.genreFilter(Genres.ACTION, toTest);
        assertEquals(toTest, expected);
    }

    @Test
    void GenreFilterFantasy() {
        List<Movie> toTest = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("Herr der Ringe: Die Rückkehr des Königs", "Gandalf und Aragorn führen die Männer der Mittelerde in den Kampf gegen Saurons Armee, um ihn von Frodo und Sam abzulenken, die sich gerade dem Schicksalsberg mit dem Einen Ring nähern.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.DRAMA, Genres.FANTASY))));

        HomeController controller = new HomeController();
        toTest = controller.genreFilter(Genres.FANTASY, toTest);
        assertEquals(toTest, expected);
    }

    @Test
    void GenreFilterDrama() {
        List<Movie> toTest = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("Herr der Ringe: Die Rückkehr des Königs", "Gandalf und Aragorn führen die Männer der Mittelerde in den Kampf gegen Saurons Armee, um ihn von Frodo und Sam abzulenken, die sich gerade dem Schicksalsberg mit dem Einen Ring nähern.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.DRAMA, Genres.FANTASY))));
        expected.add(new Movie("Fight Club", "Ein unter Schlaflosigkeit leidender Büroangestellter sucht nach einer Möglichkeit, sein Leben zu ändern, und trifft dabei auf einen sorglosen Seifenhändler, der im Untergrund einen Kampfclub unterhält, der sich als etwas noch viel Größeres herausstellt.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.DRAMA))));


        HomeController controller = new HomeController();
        toTest = controller.genreFilter(Genres.DRAMA, toTest);
        assertEquals(toTest, expected);
    }

    @Test
    void GenreFilterAnimation() {
        List<Movie> toTest = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();
        expected.add(new Movie("Spider-Man: Across the Spider-Verse", "Miles Morales kehrt im 2. Teil der Spider-Verse-Saga zurück, reist durch das Multiversum und trifft auf ein Team von Spider-People. Uneinig über den Umgang mit einer neuen Bedrohung definiert Miles neu, was es bedeutet, ein Held zu sein.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.ANIMATION))));

        HomeController controller = new HomeController();
        toTest = controller.genreFilter(Genres.ANIMATION, toTest);
        assertEquals(toTest, expected);
    }

    @Test
    void GenreFilterComedy() {
        List<Movie> toTest = Movie.initializeMovies();
        List<Movie> expected = new ArrayList<>();

        HomeController controller = new HomeController();
        toTest = controller.genreFilter(Genres.COMEDY, toTest);
        assertEquals(toTest, expected);
    }

    @Test
    void test_if_the_text_filter_an_empty_input() {
        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = Movie.initializeMovies();
        List<Movie> expectedList = new ArrayList<>(actualList);

        //when
        actualList = controller.textFilter("", actualList);

        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void test_if_the_text_filter_filters_the_titel_and_description() {
        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = Movie.initializeMovies();
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("Fight Club", "Ein unter Schlaflosigkeit leidender Büroangestellter sucht nach einer Möglichkeit, sein Leben zu ändern, und trifft dabei auf einen sorglosen Seifenhändler, der im Untergrund einen Kampfclub unterhält, der sich als etwas noch viel Größeres herausstellt.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.DRAMA))));
        expectedList.add(new Movie("Matrix", "Ein Computerhacker erfährt von mysteriösen Rebellen die Wahrheit über seine Realität und seine Rolle im Krieg gegen deren Kontrolleure.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.SCIENCE_FICTION))));
        expectedList.add(new Movie("Spider-Man: Across the Spider-Verse", "Miles Morales kehrt im 2. Teil der Spider-Verse-Saga zurück, reist durch das Multiversum und trifft auf ein Team von Spider-People. Uneinig über den Umgang mit einer neuen Bedrohung definiert Miles neu, was es bedeutet, ein Held zu sein.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.ANIMATION))));

        //when
        actualList = controller.textFilter("tri", actualList);

        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void test_if_the_filter_ignores_upper_and_lower_case_letters() {
        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = Movie.initializeMovies();
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("Avatar", "A paraplegic ...", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE))));
        expectedList.add(new Movie("Herr der Ringe: Die Rückkehr des Königs", "Gandalf und Aragorn führen die Männer der Mittelerde in den Kampf gegen Saurons Armee, um ihn von Frodo und Sam abzulenken, die sich gerade dem Schicksalsberg mit dem Einen Ring nähern.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.DRAMA, Genres.FANTASY))));
        expectedList.add(new Movie("Star Wars: Episode V", "Nachdem die Rebellen auf ihrem neuen Stützpunkt brutal vom Imperium überwältigt worden sind, macht Luke Skywalker bei Meister Yoda einen Jedi-Kurs für Fortgeschrittene, während seine Freunde von Darth Vader verfolgt werden, der dadurch Luke gefangen zu nehmen hofft.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.SCIENCE_FICTION))));

        //when
        actualList = controller.textFilter("ar", actualList);

        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void test_if_the_sort_function_works_asc() {
        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = Movie.initializeMovies();
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("Avatar", "A paraplegic ...", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE))));
        expectedList.add(new Movie("Fight Club", "Ein unter Schlaflosigkeit leidender Büroangestellter sucht nach einer Möglichkeit, sein Leben zu ändern, und trifft dabei auf einen sorglosen Seifenhändler, der im Untergrund einen Kampfclub unterhält, der sich als etwas noch viel Größeres herausstellt.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.DRAMA))));
        expectedList.add(new Movie("Herr der Ringe: Die Rückkehr des Königs", "Gandalf und Aragorn führen die Männer der Mittelerde in den Kampf gegen Saurons Armee, um ihn von Frodo und Sam abzulenken, die sich gerade dem Schicksalsberg mit dem Einen Ring nähern.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.DRAMA, Genres.FANTASY))));
        expectedList.add(new Movie("Matrix", "Ein Computerhacker erfährt von mysteriösen Rebellen die Wahrheit über seine Realität und seine Rolle im Krieg gegen deren Kontrolleure.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.SCIENCE_FICTION))));
        expectedList.add(new Movie("Spider-Man: Across the Spider-Verse", "Miles Morales kehrt im 2. Teil der Spider-Verse-Saga zurück, reist durch das Multiversum und trifft auf ein Team von Spider-People. Uneinig über den Umgang mit einer neuen Bedrohung definiert Miles neu, was es bedeutet, ein Held zu sein.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.ANIMATION))));
        expectedList.add(new Movie("Star Wars: Episode V", "Nachdem die Rebellen auf ihrem neuen Stützpunkt brutal vom Imperium überwältigt worden sind, macht Luke Skywalker bei Meister Yoda einen Jedi-Kurs für Fortgeschrittene, während seine Freunde von Darth Vader verfolgt werden, der dadurch Luke gefangen zu nehmen hofft.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.SCIENCE_FICTION))));

        //when
        controller.sortMovies_asc(actualList);

        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void test_if_the_sort_function_works_dsc() {
        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = Movie.initializeMovies();
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("Star Wars: Episode V", "Nachdem die Rebellen auf ihrem neuen Stützpunkt brutal vom Imperium überwältigt worden sind, macht Luke Skywalker bei Meister Yoda einen Jedi-Kurs für Fortgeschrittene, während seine Freunde von Darth Vader verfolgt werden, der dadurch Luke gefangen zu nehmen hofft.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.SCIENCE_FICTION))));
        expectedList.add(new Movie("Spider-Man: Across the Spider-Verse", "Miles Morales kehrt im 2. Teil der Spider-Verse-Saga zurück, reist durch das Multiversum und trifft auf ein Team von Spider-People. Uneinig über den Umgang mit einer neuen Bedrohung definiert Miles neu, was es bedeutet, ein Held zu sein.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.ANIMATION))));
        expectedList.add(new Movie("Matrix", "Ein Computerhacker erfährt von mysteriösen Rebellen die Wahrheit über seine Realität und seine Rolle im Krieg gegen deren Kontrolleure.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.SCIENCE_FICTION))));
        expectedList.add(new Movie("Herr der Ringe: Die Rückkehr des Königs", "Gandalf und Aragorn führen die Männer der Mittelerde in den Kampf gegen Saurons Armee, um ihn von Frodo und Sam abzulenken, die sich gerade dem Schicksalsberg mit dem Einen Ring nähern.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE, Genres.DRAMA, Genres.FANTASY))));
        expectedList.add(new Movie("Fight Club", "Ein unter Schlaflosigkeit leidender Büroangestellter sucht nach einer Möglichkeit, sein Leben zu ändern, und trifft dabei auf einen sorglosen Seifenhändler, der im Untergrund einen Kampfclub unterhält, der sich als etwas noch viel Größeres herausstellt.", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.DRAMA))));
        expectedList.add(new Movie("Avatar", "A paraplegic ...", new ArrayList<Genres>(List.of(Genres.ACTION, Genres.ADVENTURE))));

        //when
        controller.sortMovies_dsc(actualList);

        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void test_for_searchBtnAction() {
        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = Movie.initializeMovies();
        Genres genreToFilter = Genres.ACTION;
        List<Movie> expectedList = new ArrayList<>();
        expectedList.add(new Movie("Avatar",
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
        List<Movie> actualList = Movie.initializeMovies(emptyMap);
        List<Movie> expectedList = Movie.initializeMovies(emptyMap);
        //when
        controller.searchBtnAction(null, null, null, null);
        //then
        assertEquals(expectedList, actualList);
    }

    @Test
    void most_frequently_occurring_person(){
        //given
        HomeController controller = new HomeController();
        List<Movie> actualList = Movie.initializeMovies();
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
        List<Movie> actualList = Movie.initializeMovies();
        //Star Wars: Episode V - The Empire Strikes Back
        int expectedTitleLength = 46;
        //when
        int actualTitleLength = controller.getLongestMovieTitle(actualList);
        //then
        assertEquals(expectedTitleLength, actualTitleLength);
    }

}

