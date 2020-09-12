package duke;

import java.io.IOException;
import java.util.Scanner;

import command.ByeCommand;
import command.Command;
import command.Result;


/**
 * The Dukebot programme implements an application that assists the user in managing their tasks.
 *
 * @author  Ryan Lim
 */
public class Duke {
    /** variable to check if duke is still running */
    private boolean isRunning = true;
    /** ArrayList to hold all tasks keyed in by the user for the session */
    private TaskList taskList;
    /** UI where the user will interact with the Dukebot */
    private final Ui ui;
    /** Parser which handles user input */
    private final Parser parser;
    /** Storage which manages the saving of tasks from the session into the hard disk  */
    private final Storage storage;

    /**
     * initialises Duke bot
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage("./data/data.txt");
        try {
            this.taskList = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.printLoadingError();
            this.taskList = new TaskList();
        }
        this.ui.printGreeting();
    }

    public String greet() {

        return this.ui.printGreeting();
    }

    /**
     * Returns True if Duke bot is still running.
     *
     * @return boolean value indicating if Duke bot is still running or not.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * saves tasks to hard disk
     */
    private void updateFile() {
        try {
            this.storage.save(this.taskList);
        } catch (IOException e) {
            this.ui.printErrorInSaving();
        }
    }

    /**
     * Takes in a user input as a string, parses it and gets fed the appropriate commands to be handled.
     *
     * @param userInput commands and parameters that the user inputs through the user interface
     */
    public String run(String userInput) {
        Command command = this.parser.parse(userInput);
        if (command.getClass() == ByeCommand.class) {
            this.updateFile();
            this.isRunning = false;
        }
        Result result = command.execute(this.taskList, this.storage);
        return result.toString();
    }

    /**
     * main method of duke.java where duke is runned
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner sc = new Scanner(System.in); //scans for input
        String userInput;

        while (duke.isRunning()) {
            userInput = sc.nextLine();
            try {
                System.out.print(duke.run(userInput));
            } catch (IllegalArgumentException e) {
                DukeExceptions.printUnrecognizableCommandError();
            }
        }
    }

    public String returnUnrecognizableCommandError() {
        return DukeExceptions.printUnrecognizableCommandError();
    }
}
