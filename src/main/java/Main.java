/**
 * The main entry point to the application.
 *
 * This is a workaround for the following error when OliviaGui is made the
 * entry point of the application:
 *
 *     Error: JavaFX runtime components are missing, and are required to run this application
 *
 * The reason is that OliviaGui extends Application. In that case, the
 * LauncherHelper will check for the javafx.graphics module to be present
 * as a named module. We don't use JavaFX via the module system so it can't
 * find the javafx.graphics module, and so the launch is aborted.
 *
 * By having a separate main class (Main) that doesn't extend Application
 * to be the entry point of the application, we avoid this issue.
 *
 * Credit to AB3's Main.java.
 */

public class Main {

    public static void main(String[] args) {
        OliviaGui.main(args);
    }

}
