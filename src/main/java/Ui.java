/**
 * Ui class deals with all of the interactions with the user.
 */
public class Ui {

    protected Parser parser;
    protected String divider = "____________________________________________________________";

    /**
     * Constructor creates an Ui object.
     *
     * @param parser parser object
     */
    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Greets the user when the program starts.
     *
     * @return hello message
     */
    protected String hello() {
        String intro = "Hello! I'm Bob\n"
                + "What can I do for you?\n";
        return divider + "\n" + intro + "\n" + divider;
    }

    /**
     * Handles all of the user inputs and passes it to the parser.
     *
     * @return output of chatbot
     */
    protected String run(String input) {
        if (!input.equals("bye")) {
            return this.parser.run(input);
        } else {
            return this.goodbye();
        }
    }

    /**
     * Handles it when the user wants to exit the program.
     *
     * @return goodbye message
     */
    protected String goodbye() {
        String message = divider + "\n" + "Bye. Hope to see you again soon! :)" + "\n" + divider;
        return message;
    }

}
