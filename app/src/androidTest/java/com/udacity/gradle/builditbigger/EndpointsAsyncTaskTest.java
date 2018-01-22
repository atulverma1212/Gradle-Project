package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by HP on 30-12-2017.
 */

public class EndpointsAsyncTaskTest extends AndroidTestCase{
    public void testJoke() {

        try {
            EndpointsAsyncTask task = new EndpointsAsyncTask();
            task.execute();
            String joke = task.get(30, TimeUnit.SECONDS);
            assertThat(joke, notNullValue());
            assertTrue(joke.length() > 0);
            assertTrue(joke.startsWith("#"));

        } catch (Exception e) {
            fail("Test failed");
        }
    }
}
