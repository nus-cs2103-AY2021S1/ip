package duke;

public class Duke {
    
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;
    private final Parser parser;
    

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }
    
    
    public void run() {
        ui.showWelcomeMessage();
        
        String next = ui.readUserInput();
        while (!next.equalsIgnoreCase("Bye")) {
            parser.sortInput(next, taskList, storage, ui);
            next = ui.readUserInput();
        }
        
        ui.showByeMessage();
    }
    
    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }
}

