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
     * Getter method for JUnit testing purpose only.
     *
     * @return Parser object
     */
    public Parser getParser() {
        return this.parser;
    }

    public static void main(String[] args) {
        new Duke();
    }

    /**
     * Retrieve response from Ui after receiving input.
     *
     * @param input user's command
     * @return output given by the chatbot
     */
    public String getResponse(String input) {
        return this.ui.run(input);
    }

    /**
     * Getter method to get instance of Ui.
     *
     * @return Ui object
     */
    public Ui getUi() {
        return this.ui;
    }
}

