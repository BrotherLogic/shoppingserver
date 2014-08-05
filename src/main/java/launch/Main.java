package launch;

import java.io.File;

import org.apache.catalina.startup.Tomcat;

/**
 * Main Heroku Runner
 *
 * @author simon
 *
 */
public final class Main {

    /**
     * Main method
     *
     * @param args
     *            No command line arguments are used
     * @throws Exception
     *             If something goes wrong somewhere
     */
    public static void main(final String[] args) throws Exception {

        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        // The port that we should run on can be set into an environment
        // variable
        // Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty())
            webPort = "8080";

        tomcat.setPort(Integer.parseInt(webPort));

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: "
                + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     * Blocking constructor
     */
    private Main() {

    }
}