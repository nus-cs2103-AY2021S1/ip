package tickbot;

import tickbot.ui.text.TextUi;

/**
 * The main class of the application.
 */
public class Tickbot {
    public static void main(String[] args) {
        TextUi ui = new TextUi();
        ui.mainLoop();
    }
}
