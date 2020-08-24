import java.util.Scanner;

/**
 * Ui class deals with all of the interactions with the user.
 */
public class Ui {

    protected Parser parser;
    protected String divider = "____________________________________________________________";

    /**
     * Constructor creates an Ui object.
     *
     * @param parser parser object.
     */
    public Ui(Parser parser) {
        this.parser = parser;
        this.hello();
        this.run();
    }

    /**
     * Greets the user when the program starts.
     */
    protected void hello() {
        String intro = "Hello! I'm Bob\n" +
                "What can I do for you?\n";
        System.out.println(divider + "\n" + intro + "\n" + divider);
    }

    /**
     * Handles all of the user inputs and passes it to the parser.
     */
    protected void run() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            this.parser.run(input);
            input = sc.nextLine();
        }
        this.goodbye();
    }

    /**
     * Handles it when the user wants to exit the program.
     */
    protected void goodbye() {
        String message = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + "\n" + message + "\n" + divider);
    }
}