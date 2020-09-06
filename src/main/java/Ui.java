import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Greets user immediately when Duke is executed.
     * @return String to greet user.
     */
    public String greetings() {
        String greetings = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
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
}
