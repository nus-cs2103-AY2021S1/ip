package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A chatbot that helps the user keep track of todo, deadline and event tasks 
 * by storing them in a list.
 */
public class Duke {
    
    
    static final String filepath = "duke.txt";
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a Duke object and initialises the TaskList, Ui and Storage.
     * @param filepath location of the save file containing the list of tasks.
     */
    public Duke (String filepath) {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            storage.pullList(taskList);
        } catch (Exception e) {
            ui.printError(e);
        }
    }

    /**
     * Starts running Duke by printing welcome.
     * Utilises Ui to scan for the next user input and the Parser to parse it.
     * Executes the command determined by the user's input.
     * Stops running if the next input is "bye".
     */
    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String nextCommand = ui.readCommand();
                Command c = Parser.parse(nextCommand);
                c.execute(taskList, ui, storage);
                isRunning = c.isRunning();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }
    
    public static void main (String[] args) {
        new Duke(filepath).run();
    }

    
    
    

//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
    
}