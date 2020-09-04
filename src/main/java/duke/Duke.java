package duke;


public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    
    public Duke () {
        ui = new Ui();
        storage = new Storage("data/data.txt");
        parser = new Parser();
        tasks = new TaskList(storage.load());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        parser.sortInput(input, tasks, storage, ui);
        return ui.getOutputMessage();
    }
}
