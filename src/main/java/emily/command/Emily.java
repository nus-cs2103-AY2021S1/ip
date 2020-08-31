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
     * @param filePath location to store the information
     */
    public Emily(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readData());
        } catch (DukeException e) {
            File f = new File(FILE_PATH);
            f.getParentFile().mkdirs();
        }
    }

    /**
     * Interact with the user
     * Read the user commands, modifies the task list, prints out information
     */
    public void run() {

        String divider = "-------------------";
        boolean end = false;

        System.out.println("Hello, I am Emily\n"
                + "What can i do for you?\n"
                + divider);

        while (!end) {
            try {
                Scanner sc = new Scanner(System.in);

                String input = sc.nextLine();
                while (!input.equals("bye")) {
                    ui.reading(input, tasks);
                    input = sc.nextLine();
                    storage.reWrite(tasks.getStore());
                }
                end = true;
            } catch (DukeException e) {
                System.out.println("    OOPS! " + e.getMessage() + "\n" + divider);
            }
        }

        System.out.println("bye\n" + divider + "\nBye~, hope to see you again!");


    }

    public static void main(String[] args) {
        new Emily(FILE_PATH).run();
    }
}