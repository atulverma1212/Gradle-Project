package com.example;

import java.util.Random;

public class Joke {
    Random random;
    String[] jokes;

    public Joke() {
        jokes = new String[5];
        jokes[0] = "#What’s the difference between a new husband and a new dog? After a year, the dog is still excited to see you.";
        jokes[1] = "#Love may be blind, but marriage is a real eye-opener.";
        jokes[2] = "#I say no to alcohol, it just doesn’t listen.";
        jokes[3] = "#If you can’t convince them, confuse them.";
        jokes[4] = "#Whenever I find the key to success, someone changes the lock.";
        random = new Random();
    }

    public String[] getJokes() {
        return jokes;
    }

    public String getJoke(){
        return jokes[random.nextInt(jokes.length)];
    }
}
