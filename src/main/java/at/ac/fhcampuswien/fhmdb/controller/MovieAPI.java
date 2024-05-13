package at.ac.fhcampuswien.fhmdb.controller;

// Logic for sending and receiving requests/responses
// Creation of URLs

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieAPIException;
import com.google.gson.Gson;
import okhttp3.*;
import java.io.IOException;
import java.util.*;

public class MovieAPI {

    private String queryUrl = "";

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setUrl(Map<String, Object> params){

        HttpUrl.Builder urlBuilder = new HttpUrl.Builder()
                .scheme("https")
                .host("prog2.fh-campuswien.ac.at")
                .addPathSegment("movies");


        params.entrySet().stream()
                .filter(entry -> entry.getValue() != null && !Objects.equals(entry.getValue().toString(),"") && !Objects.equals(entry.getValue().toString(), "No_Filter"))
                .forEach(entry -> urlBuilder.addQueryParameter(entry.getKey(), entry.getValue().toString()));

        this.queryUrl = urlBuilder.toString();
    }


    public String apiQuery(Map<String, Object> params) throws MovieAPIException {
        setUrl(params);
        OkHttpClient client = new OkHttpClient();

        System.out.println(queryUrl);

        Request request = new Request.Builder()
                .url(queryUrl)
                .header("User-Agent", "http.agent")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException ioe) {
            throw new MovieAPIException("MovieAPIException"+ioe);
        }
    }

    public List<Movie> jsonToMovieList(String Json){
        Gson gson = new Gson();
        Movie[] movies = gson.fromJson(Json, Movie[].class);
        return new ArrayList<>(Arrays.asList(movies));
    }

}
