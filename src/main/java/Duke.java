/**
 * Main class for Duke program.
 */
public class Duke {

    /**
     * Entry point of the duke program. Read from storage and creates a UI class instance.
     * Serves as intermediate between the UI and parser.
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        TaskList tasks = Storage.read();
        assert tasks != null : "error initializing tasks";
        UI ui = new UI();
        assert ui != null : "error initializing UI";
        ui.welcome(tasks);
        String input = ui.getInput();
        Parser parser = new Parser(tasks);
        assert ui != null : "error initializing parser";
        while (!input.equals("bye")) {
            try {
                if (input.isEmpty()) {
                    input = ui.getInput();
                    continue;
                }
                parser.parse(input);
            } catch (DukeException e) {
                UI.print(e.getMessage() + "\n");
            }

            input = ui.getInput();
        }
        ui.bye();
    }

}
