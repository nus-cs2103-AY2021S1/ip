package main.java.emily.command;

import java.io.File;
import java.util.Scanner;


/**
 * Main program
 */
public class Emily {

    private static final String FILE_PATH = "data/emily.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Initialises Emily bot
     */
    public Emily() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            File f = new File(FILE_PATH);
            f.getParentFile().mkdirs();
        }
    }

    /**
     * Interact with the user
     * Read the user commands, modifies the task list, prints out information
     */
    public String receiveCommandLine(String userInputText) {

        boolean end = false;
        String output = "";

        while (!end) {
            try {
                output = ui.readsLine(userInputText, tasks);
                storage.reWriteData(tasks.getTaskArrayList());
                end = true;
            } catch (DukeException e) {
                return "    OOPS! " + e.getMessage() + "\n";
            }
        }

        //System.out.println("\nBye~, hope to see you again!");
        return output;


    }

    public static void main(String[] args) {
    }
}