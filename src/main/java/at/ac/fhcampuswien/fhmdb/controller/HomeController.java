package at.ac.fhcampuswien.fhmdb.controller;

import at.ac.fhcampuswien.fhmdb.models.*;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.ui.WatchlistCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

//DONE Konstantin Calling the API instead of the static lists
public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public Map<String, Object> queryParams = new HashMap<>();

    public List<Movie> allMovies = Movie.initializeMovies(); // key= query, genre, releaseYear, ratingFrom

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes
    public JFXComboBox releaseYearComboBox;
    public JFXComboBox ratingComboBox;

    protected Genres selectedGenre;

    private MovieRepository movieRepo;

    {
        try {
            movieRepo = new MovieRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private WatchlistRepository watchlistRepository = new WatchlistRepository();



    @FXML
    private HBox searchHbox;
    @FXML
    private VBox mainBox;
    @FXML
    private MenuButton menuButton;



    @FXML
    public void switch_to_main_menu() {
        System.out.println("Option 1 selected");
        // Implement your logic here for Option 1

        if (!mainBox.getChildren().contains(searchHbox)) {
            mainBox.getChildren().add(1, searchHbox);
        }

        observableMovies.addAll(allMovies);         // add dummy data to observable list
        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        observableMovies.clear();
        observableMovies.addAll(searchBtnAction(genreComboBox.getValue(), searchField.getText(), releaseYearComboBox.getValue(), ratingComboBox.getValue()));

        menuButton.setText("Menu");
    }
    @FXML
    private void switch_to_watchlist() {
        List<Movie> watchlistMovieObjects = new ArrayList<>();
        if (searchHbox.getParent() != null) {
            ((Pane) searchHbox.getParent()).getChildren().remove(searchHbox);
        }
            try {
                List<WatchlistMovieEntity> watchlistEntities = watchlistRepository.getWatchlist();

                for (WatchlistMovieEntity entity : watchlistEntities) {
                    MovieEntity movieEntity = movieRepo.getMovie(entity.getApiId());
                    watchlistMovieObjects.add(MovieRepository.entityToMovie(movieEntity));
                }
            }catch (SQLException sqle){
                System.out.println("SQLException "+sqle);
            }

        observableMovies.clear();
        observableMovies.addAll(watchlistMovieObjects);         // add dummy data to observable list
        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new WatchlistCell()); // use custom cell factory to display data

        menuButton.setText("Watchlist");
    }
    @FXML
    private void switch_to_about_page() {
        System.out.println("Option 3 selected");
        // Implement your logic here for Option 1
        menuButton.setText("About");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list
        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        try {
            movieRepo.addAllMovies(allMovies);
            //movieRepo.removeAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // DONE add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genres.values());

        releaseYearComboBox.getItems().add("No_Filter");
        releaseYearComboBox.getItems().addAll(allMovies.stream()
                .map(Movie::getReleaseYear)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .toList());

        ratingComboBox.getItems().add("No_Filter");
        ratingComboBox.getItems().addAll(allMovies.stream()
                .map(Movie::getRating)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .toList());

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                sortMovies_asc(observableMovies);
                sortBtn.setText("Sort (desc)");
            } else {
                sortMovies_dsc(observableMovies);
                sortBtn.setText("Sort (asc)");
            }
        });

        //Reset the observable Movies
        searchBtn.setOnAction(actionEvent -> {
            observableMovies.clear();
            observableMovies.addAll(searchBtnAction(genreComboBox.getValue(), searchField.getText(), releaseYearComboBox.getValue(), ratingComboBox.getValue()));
        });
    }

    public List<Movie> searchBtnAction(Object genreToFilter, Object searchText, Object releaseYear, Object rating) {
        queryParams.clear();
        queryParams.put("query", searchText);
        queryParams.put("genre", genreToFilter);
        queryParams.put("releaseYear", releaseYear);
        queryParams.put("ratingFrom", rating);
        return Movie.initializeMovies(queryParams);
    }

    public void sortMovies_asc(List<Movie> movieList) {
        movieList.sort((movie1, movie2) -> movie1.getTitle().compareToIgnoreCase(movie2.getTitle()));
    }

    public void sortMovies_dsc(List<Movie> movieList) {
        movieList.sort((movie1, movie2) -> movie2.getTitle().compareToIgnoreCase(movie1.getTitle()));
    }

    public String getMostPopularActor(List<Movie> movieList) {
        Map<String, Long> actorsMap = movieList.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));

        return actorsMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public int getLongestMovieTitle(List<Movie> movieList) {

        return movieList.stream()
                .mapToInt(movies -> movies.getTitle().length())
                .max().orElse(0);
   }

   public long countMoviesFrom(List<Movie> movieList, String director){
        return movieList.stream().filter(m->m.getDirectors().contains(director)).count();
   }

   public List<Movie> getMoviesBetweenYears(List<Movie> movieList, int startYear, int endYear){
        return movieList.stream().filter(m->m.getReleaseYear()>=startYear&&m.getReleaseYear()<=endYear).toList();
   }
}
