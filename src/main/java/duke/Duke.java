package duke;

import java.io.File;
import java.io.IOException;

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
    
    public static void main(String[] args) throws IOException {
        String path = "data/data.txt";
        
        try {
            File f = new File(path);
            f.getParentFile().mkdir();
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        new Duke(path).run(); 
    }
}

