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
    /** Parser which handles user input */
    private final Parser parser;
    /** Storage which manages the saving of tasks from the session into the hard disk  */
    private final Storage taskStorage;
    private final Storage aliasStorage;

    /**
     * initialises Duke bot
     */
    public Duke() {
        Parser parser;
        TaskList taskList;
        this.taskStorage = new Storage("./data/data.txt");
        this.aliasStorage = new Storage("./data/alias.txt");
        try {
            parser = new Parser(this.aliasStorage.load());
            taskList = new TaskList(this.taskStorage.load());
        } catch (IOException e) {
            parser = new Parser();
            taskList = new TaskList();
        }
        this.parser = parser;
        this.taskList = taskList;
    }

    /**
     * Greets user
     * @return the greeting message
     */
    public String greet() {
        String greeting = "Sorry :( duke.Duke is getting some upgrades at the moment.\n"
                + "This is Tron, temporarily standing in for duke.Duke, how may I assist you ?\n";
        return greeting;
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
            this.taskStorage.save(this.taskList);
            this.aliasStorage.save(this.parser);
        } catch (IOException e) {
            System.out.println("Master i am unable to save the file!");
        }
    }

    /**
     *
     */
    private void exit() {
        this.updateFile();
        this.isRunning = false;
    }

    /**
     * Takes in a user input as a string, parses it and gets fed the appropriate commands to be handled.
     *
     * @param userInput commands and parameters that the user inputs through the user interface
     */
    public Result run(String userInput) {
        Command command = this.parser.parse(userInput);
        if (command.getClass() == ByeCommand.class) {
            this.exit();
        }
        Result result = command.execute(this.taskList, this.parser);
        return result;
    }

    /**
     * main method of duke.java where duke is runned
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in); //scans for input
        String command;
        while (duke.isRunning()) {
            command = sc.nextLine();
            Result result = duke.run(command);
            System.out.println(result.toString());
        }
    }
}
