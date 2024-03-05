package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genres;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


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

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    private Genres selectedGenre;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().add("empty Filter");
        genreComboBox.getItems().addAll(Genres.values());
        /*JFXComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                selectedGenre = Genres.valueOf(t1);
            }
        });
        */

        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort Julian observableMovies ascending
                //observableMovies.sort();
                observableMovies.remove(0,2);
                sortBtn.setText("Sort (desc)");
            } else {
                // TODO Julian sort observableMovies descending
                //observableMovies.sort();
                sortBtn.setText("Sort (asc)");
            }
        });


    }

    //for testing JUnit
    public int addOne(int n){
        return n + 1;
    }


    //TODO Julian function to filter with search bar
    public void textFilter(String searchText, List<Movie> movieList){
        String text = searchText.toLowerCase();
        movieList.removeIf(movie ->
                !(movie.getDescription().toLowerCase().contains(text) || movie.getTitle().toLowerCase().contains(text)));
    }

    //TODO Konstantin
    public void genreFilter(Genres genre,List<Movie> movieList){
        movieList.removeIf(m -> !(m.getGenresList().contains(genre)));
    }


}