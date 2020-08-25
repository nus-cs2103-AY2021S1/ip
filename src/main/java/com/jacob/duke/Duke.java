package main.java.com.jacob.duke;

import main.java.com.jacob.duke.command.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke
     * @param filePath contains file where task list is saved
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFile());
    }

    /**
     * Basic execution command logic
     */
    public void run() {
        Parser parser = new Parser();

        ui.showWelcomeMessage();

        boolean isBye = false;

        //while the input is not the bye command, keep scanning for the next command
        while (!isBye) {
            try {
                String fullCommand = ui.getConsoleInput();
                ui.printLines();
                Command c = parser.parse(fullCommand);
                c.execute(ui, tasks, storage);

                //write to file
                storage.writeToFile();

                //check if its the bye command
                isBye = c.isBye();
            } catch (DukeException d) {
                System.out.println(d.getMessage());
            } finally {
                ui.printLines();
            }
        }
    }

    //Driver method
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
