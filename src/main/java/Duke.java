import command.Command;

import exception.DukeException;

import parser.Parser;

import storage.Storage;

import tasklist.TaskList;

import ui.Ui;

/**
 * Main class.
 */
public class Duke {
    public static Storage storage;
    public static TaskList taskList;
    public static Ui ui;
    public static boolean exit;

    public Duke(String filePath){
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Runs until the user commands for program to stop.
     */
    public void run() {
        ui.welcome();
        this.exit = false;
        while(!exit) {
            try {
                String userInput = ui.getUserInput();
                Command command = Parser.parse(userInput);
                command.execute(this.taskList, this.ui, this.storage);
                this.exit = command.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.goodbye();
    }

    public static void main(String[] args) {
        new Duke("src/main/java/data/duke.txt").run();
    }
}
