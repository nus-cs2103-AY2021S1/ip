import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Greets user immediately when Duke is executed.
     * @return String to greet user.
     */
    public String greetings() {
        String greetings = "Ciao... *evil giggle*\nI'm your Dark Side...\n"
                + "How may I serve you? >:)\n";
        return greetings;
    }

    /**
     * Bids farewell to user when Duke is terminated with 'bye' command.
     * @return String to bid user goodbye.
     */
    public String goodbye() {
        // print goodbye message
        String goodbye = "Sayonara.\n";
        return goodbye;
    }

    /**
     * Adds dashed lines to improve separation between user and Duke responses.
     * @return String to be displayed for response.
     */
    public String formatResponse(String input) {
        return "- - - - - - - - - - - - Dark Side: - - - - - - - - - - - -\n\n"
                + input
                + "\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n";
    }
}
