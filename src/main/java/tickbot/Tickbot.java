package tickbot;

import java.util.Objects;

import tickbot.ui.Ui;
import tickbot.ui.graphics.GraphicsUi;
import tickbot.ui.text.TextUi;

/**
 * The main class of the application.
 */
public class Tickbot {
    /**
     * The main entrance of Tickbot.
     */
    public static void main(String[] args) {
        Ui ui;
        if (args.length > 1 && Objects.equals(args[1], "--cli")) {
            ui = new TextUi();
        } else {
            ui = new GraphicsUi();
        }
        ui.mainLoop(args);
    }
}
