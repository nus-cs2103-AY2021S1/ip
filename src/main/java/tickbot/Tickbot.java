package tickbot;

import tickbot.ui.text.TextUi;

/**
 * The main class of the application.
 */
public class Tickbot {
    /**
     * The main entrance of Tockbot.
     */
    public static void main(String[] args) {
        TextUi ui = new TextUi();
        ui.mainLoop();
    }
}
