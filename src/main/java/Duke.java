public class Duke {

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("data/storage/duke.txt");
        Parser parser = new Parser();
        String input = "";
        TaskList list = new TaskList(storage.load());
        String output;

        ui.showWelcome();
        
        while (!input.equals("bye")) {
            input = ui.readInput();

            try {
                output = parser.parse(input, list, storage);
            } catch (DukeException e) {
                output = e.getMessage();
            }

            ui.showOutput(output);
        }
    }
}
