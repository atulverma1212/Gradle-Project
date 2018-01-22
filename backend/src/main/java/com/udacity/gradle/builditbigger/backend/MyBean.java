package com.udacity.gradle.builditbigger.backend;

import com.example.Joke;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private Joke joke;

    public MyBean() { joke=new Joke(); }

    public String getData() {
        return joke.getJoke();
    }
}