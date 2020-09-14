import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Entry point for model.Duke.
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        Application.launch(Main.class, args);
    }
}
