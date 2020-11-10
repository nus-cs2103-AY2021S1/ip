package meimei;

import javafx.application.Application;
import meimei.gui.Gui;

/**
 * A launcher class to workaround classpath issues.
 * Reused from <a href="https://se-education.org/guides/tutorials/javaFxPart1.html">
 *     this guide.</a>
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
