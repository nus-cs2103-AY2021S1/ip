package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.ui.TextUi;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;


/**
 * Drives the personal assistant using a UI, along with a parser to make sense of user inputs and executing commands on
 * a list of tasks that are read/written from local storage
 */
public class Duke extends Thread {
    private TaskList tasks;
    private final Ui ui;
    private boolean canExit;
    private final Parser parser;
    
    /**
     * Creates UI and loads saved tasks from hard drive along with Duke
     */
    public Duke() {
        // save location has been hardcoded into Storage class
        Runtime currentRuntime = Runtime.getRuntime();
        this.ui = new TextUi();
        this.canExit = false;
        this.parser = new Parser();
        try {
            this.tasks = Storage.load();
        } catch (IOException e) {
            this.ui.displayLoadError();
            this.tasks = new TaskList();
        }
        
        currentRuntime.addShutdownHook(new Thread(() -> {
            try {
                Storage.save(tasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.ui.bidFarewell();
        }));
    }
    
    /**
     * Starts the application, invokes UI interaction and execution of Commands
     *
     * @throws IOException If there are issues with reading/writing onto file
     */
    public void runDuke() throws IOException {
        Scanner sc = new Scanner(System.in);
        this.ui.greet();
        while (!canExit) {
            String userInput = this.ui.readCommand(sc);
            try {
                Command command = parser.parseCommand(userInput);
                command.execute(this.tasks, this.ui);
                this.canExit = command.isExit();
            } catch (DukeException e) {
                this.ui.displayError(e.getMessage());
            }
            
        }
        sc.close();
        Storage.save(tasks);
        // todo: handle the ctrl-c event handling for sudden
    }
    
    public static void main(String[] args) throws IOException {
        new Duke().runDuke();
    }
}


