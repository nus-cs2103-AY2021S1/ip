import java.io.IOException;
import java.util.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;



public class Duke {
    //functions to edit file
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException, DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }



    public void run() throws DukeException, IOException {
        /*** welcoming message */

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! Duke at your service. Please name your request." +
                "\nAll dates should be in the form of YYYY-MM-DD: ");

        /**** User Input */
        ui.takeUserInput(storage);

    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("out/todo.txt").run();

    }
}
