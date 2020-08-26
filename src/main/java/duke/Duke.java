package duke;

import duke.command.Command;

import java.util.Scanner;

/**
 * Duke is a friendly personal assistant that helps keep track of tasks to be done.
 * Duke has recently been renamed to Dude.
 */
public class Duke {
    private final String PATH = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Scanner scanner;

    /**
     * Initializes duke.
     */
    public void initialize() {
        try {
            Ui.printLogo();
            System.out.println("\tInitializing...");
            
            storage = new Storage(PATH);
            taskList = new TaskList(storage.getTasks());
            parser = new Parser();
            scanner = new Scanner(System.in);
            
            greet();
            
            while (true) {
                listen();
            }
        } catch (DukeException e) {
            System.out.println("\t" + e.getMessage());
        }
    }

    private void greet() {
        Ui.addMessage("Hello! My name is Dude.");
        Ui.addMessage("What can I do for you?");
        Ui.sendMessages();
    }
    
    private void listen() {
        String input = scanner.nextLine();
        try {
            Command command = parser.parse(input);
            command.execute(storage, taskList);
        } catch (DukeException e) {
            Ui.addMessage(e.getMessage());
            Ui.sendMessages();
        }
    }
}