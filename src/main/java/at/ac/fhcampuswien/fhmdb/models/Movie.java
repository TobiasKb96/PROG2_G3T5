package at.ac.fhcampuswien.fhmdb.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class Movie {
    private String title;
    private String description;
    private List<Genres> genresList;

    public Movie(String title, String description, List<Genres> genres) {
        this.title = title;
        this.description = description;
        this.genresList = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Avatar", "A paraplegic ...", new ArrayList<Genres>(List.of(Genres.ACTION,Genres.ADVENTURE))));
        movies.add(new Movie("Herr der Ringe: Die Rückkehr des Königs", "Gandalf und Aragorn führen die Männer der Mittelerde in den Kampf gegen Saurons Armee, um ihn von Frodo und Sam abzulenken, die sich gerade dem Schicksalsberg mit dem Einen Ring nähern.", new ArrayList<Genres>(List.of(Genres.ACTION,Genres.ADVENTURE,Genres.DRAMA,Genres.FANTASY))));
        movies.add(new Movie("Fight Club", "Ein unter Schlaflosigkeit leidender Büroangestellter sucht nach einer Möglichkeit, sein Leben zu ändern, und trifft dabei auf einen sorglosen Seifenhändler, der im Untergrund einen Kampfclub unterhält, der sich als etwas noch viel Größeres herausstellt.", new ArrayList<Genres>(List.of(Genres.ACTION,Genres.DRAMA))));
        movies.add(new Movie("Star Wars: Episode V", "Nachdem die Rebellen auf ihrem neuen Stützpunkt brutal vom Imperium überwältigt worden sind, macht Luke Skywalker bei Meister Yoda einen Jedi-Kurs für Fortgeschrittene, während seine Freunde von Darth Vader verfolgt werden, der dadurch Luke gefangen zu nehmen hofft.", new ArrayList<Genres>(List.of(Genres.ACTION,Genres.ADVENTURE,Genres.SCIENCE_FICTION))));
        movies.add(new Movie("Matrix", "Ein Computerhacker erfährt von mysteriösen Rebellen die Wahrheit über seine Realität und seine Rolle im Krieg gegen deren Kontrolleure.", new ArrayList<Genres>(List.of(Genres.ACTION,Genres.SCIENCE_FICTION))));
        movies.add(new Movie("Spider-Man: Across the Spider-Verse", "Miles Morales kehrt im 2. Teil der Spider-Verse-Saga zurück, reist durch das Multiversum und trifft auf ein Team von Spider-People. Uneinig über den Umgang mit einer neuen Bedrohung definiert Miles neu, was es bedeutet, ein Held zu sein.", new ArrayList<Genres>(List.of(Genres.ACTION,Genres.ADVENTURE,Genres.ANIMATION))));
        //movies.add(new Movie("", "", new ArrayList<Genres>(List.of())));

        return movies;
    }
}
