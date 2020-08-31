package Duke.main;

/**
 * This is the main class that
 * is directly called by the users.
 */
public class Duke {
    public static void main(String[] args) {

        Ui ui = new Ui();

        ui.greet();

        Parser parser = new Parser();
        String clientInput = ui.conciseInput();
        while (!parser.isEnd(clientInput)) {
            parser.run(clientInput);
            clientInput = ui.conciseInput();
        }

        ui.exit();
    }
}