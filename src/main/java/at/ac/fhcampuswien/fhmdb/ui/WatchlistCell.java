package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.MovieRepository;
import at.ac.fhcampuswien.fhmdb.models.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.models.WatchlistRepository;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class WatchlistCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label releaseYear = new Label();
    private final Label detail = new Label();
    private final Label genres = new Label();
    private final Label rating = new Label();
    private final Button showDetails = new Button("Show Details");
    private final Button watchlistButton = new Button("Remove from Watchlist");
    private final HBox firstline = new HBox(title, releaseYear, showDetails, watchlistButton);
    private final HBox secondline = new HBox(detail);
    private final HBox thirdline = new HBox(genres, rating);
    private final VBox layout = new VBox(firstline, secondline, thirdline);

    MovieRepository movieRepository;

    {
        try {
            movieRepository = new MovieRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //TODO repository in movie cell ist nicht gut -> MovieCell Callback lambda expression die aufgerufen wird den der button geklickt wird (1:19:22 im Video)
    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            releaseYear.setText("(" + movie.getReleaseYear() + ")");
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );
            genres.setText(movie.getGenresListAsString());
            rating.setText("Rating: " + movie.getRating());
            // color scheme
            title.getStyleClass().add("text-yellow");
            releaseYear.getStyleClass().add("text-white");
            detail.getStyleClass().add("text-white");
            genres.getStyleClass().add("text-white");
            rating.getStyleClass().add("text-white");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout
            title.fontProperty().set(title.getFont().font(20));
            releaseYear.fontProperty().set(releaseYear.getFont().font(12));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);

            //first line
            firstline.setPadding(new Insets(0));
            firstline.spacingProperty().set(10);
            firstline.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            //second line

            //third line
            thirdline.setPadding(new Insets(0));
            thirdline.spacingProperty().set(10);
            thirdline.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);

            //whole Movie
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);


            watchlistButton.setOnMouseClicked(mouseEvent -> {
                WatchlistRepository watchlistRepository = new WatchlistRepository();
                        watchlistRepository.removeFromMovies(movie.getId());
                        setText(null);
                        setGraphic(null);
            });

        }
    }
}

