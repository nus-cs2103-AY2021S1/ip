package tickbot;

import java.util.Objects;

import tickbot.ui.graphics.GraphicsUi;
import tickbot.ui.text.TextUi;
import tickbot.ui.Ui;

/**
 * The main class of the application.
 */
public class Tickbot {
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
