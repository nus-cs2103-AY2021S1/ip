/**
 * The main class which invokes all the necessary classes to start the chatbot.
 *
 * @author Chen Ler
 */
public class Duke {

    private Ui ui;
    private Parser parser;

    /**
     * Constructor to initialise all of the necessary variables.
     */
    public Duke() {
        ListOfItems listOfItems = new ListOfItems();
        parser = new Parser(listOfItems);
        ui = new Ui(parser);
    }

    /**
     * Public getter method for JUnit testing purpose only.
     *
     * @return parser
     */
    public Parser getParser() {
        return this.parser;
    }

    public static void main(String[] args) {
        new Duke();
    }

    public String getResponse(String input) {
        return this.ui.run(input);
    }
}

