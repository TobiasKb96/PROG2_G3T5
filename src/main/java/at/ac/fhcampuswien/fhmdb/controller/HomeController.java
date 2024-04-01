package at.ac.fhcampuswien.fhmdb.controller;

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
import java.util.*;


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

    protected Genres selectedGenre;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list
        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // DONE add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Genres.values());

        // DONE add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here

        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                // DONE sort Julian observableMovies ascending
                sortMovies_asc(observableMovies);
                sortBtn.setText("Sort (desc)");
            } else {
                // DONE Julian sort observableMovies descending
                sortMovies_dsc(observableMovies);
                sortBtn.setText("Sort (asc)");
            }
        });

        //Reset the observable Movies
        searchBtn.setOnAction(actionEvent -> {
            observableMovies.clear();
            observableMovies.addAll(searchBtnAction((Genres) genreComboBox.getValue(),searchField.getText()));
        });
    }

    public List<Movie> searchBtnAction(Genres genreToFilter , String searchText){
        List<Movie> temp=Movie.initializeMovies();

        if(genreToFilter != Genres.No_Filter && genreToFilter != null){
            //apply filter
            temp = genreFilter(genreToFilter,temp);
        }
        if(searchText != null) {
            temp = textFilter(searchText,temp);
        }
        return temp;
    }


    //DONE Julian function to filter with search bar
    public List<Movie> textFilter(String searchText, List<Movie> movieList){
        List<Movie> tempList = new ArrayList<>(movieList);
        String text = searchText.toLowerCase();
        tempList.removeIf(movie ->
                !(movie.getDescription().toLowerCase().contains(text) || movie.getTitle().toLowerCase().contains(text)));
        return tempList;

    }

    //DONE Konstantin
    public List<Movie> genreFilter(Genres genre,List<Movie> movieList){
        List<Movie> temp=new ArrayList<>(movieList);
        temp.removeIf(m -> !(m.getGenresList().contains(genre)));
        return temp;
    }

    public void sortMovies_asc(List<Movie> movieList){
        movieList.sort((movie1, movie2) -> movie1.getTitle().compareToIgnoreCase(movie2.getTitle()));
    }

    public void sortMovies_dsc(List<Movie> movieList){
        movieList.sort((movie1, movie2) -> movie2.getTitle().compareToIgnoreCase(movie1.getTitle()));
    }

}