package com.brotherlogic.shopping.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Base class for running integration tests
 *
 * @author simon
 *
 */
public class TestBase {

    private static final String BASE_URL = "http://localhost:8080/shoppingserver/";

    /**
     * Gets the resource at the specified path
     *
     * @param path
     *            The path to retrieve
     * @return The server response on this path
     * @throws IOException
     *             If we can't process the input
     */
    public final String get(final String path) throws IOException {
        URL url = new URL(BASE_URL + path);
        StringBuffer output = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            for (String line = reader.readLine(); line != null; line = reader
                    .readLine())
                output.append(line);
            reader.close();

            return output.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }

        return output.toString();
    }
}