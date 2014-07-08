package com.brotherlogic.shopping.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main entry point for the system
 *
 * @author simon
 *
 */
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 3672184742163119028L;

    @Override
    protected final void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        printVersion(resp);
    }

    /**
     * Prints out the version number of this server instance
     *
     * @param resp
     *            The response to print to
     * @throws IOException
     *             if we can't write to the response stream
     */
    private void printVersion(final HttpServletResponse resp)
            throws IOException {
        Properties props = new Properties();
        InputStream is = null;
        try {
            is = MainServlet.class
                    .getResourceAsStream("application.properties");
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        PrintStream ps = new PrintStream(resp.getOutputStream());
        ps.print(props.get("application.version"));
        ps.close();
    }
}
