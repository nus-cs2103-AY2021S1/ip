import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Application.launch(Main.class, args);
    }
}
