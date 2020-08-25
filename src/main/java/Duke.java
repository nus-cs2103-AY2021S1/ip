import ui.UI;
import parser.Parser;

/**
 * Main class / Entry point of Duke Personal Assistant Chatbot (Kim Jong Duke).
 */
public class Duke {
    /**
     * Starts Kim Jong Duke.
     */
    public static void run() {
        UI.summonSupremeLeaderAndGreet();
        Parser.run();
        UI.farewell();
    }

    /**
     * Code entry point.
     *
     * @param args Entry point argument.
     */
    public static void main(String[] args) {
        Duke.run();
    }
}
