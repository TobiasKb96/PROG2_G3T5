package at.ac.fhcampuswien.fhmdb.controller;

// Logic for sending and receiving requests/responses
// Creation of URLs

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO Tobias Implementation of API communication
//TODO Konstantin Implementation of postman tests
public class MovieAPI {

    private String baseURL = "https://prog2.fh-campuswien.ac.at/movies";

    public String getApiRequest() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseURL)
                .header("User-Agent", "http.agent")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> jsonToMovieList(String Json){
        Gson gson = new Gson();
        Movie[] movies = gson.fromJson(Json, Movie[].class);
        return new ArrayList<>(Arrays.asList(movies));
    }

}
