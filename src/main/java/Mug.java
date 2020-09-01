import java.util.Scanner;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * A tool to save task.
 */
public class Mug {

    private final Ui ui;
    private final TaskList tasks;
    /**
     * Constructs a Mug Object that create/read the file from the given filepath
     * and pass the information from the file to TaskList.
     */
    public Mug() {
        this.ui = new Ui();
        Storage storage = new Storage("mug.txt");
        this.tasks = new TaskList(storage);
    }

    /**
     * Runs user input.
     */
    public void run() {
        this.ui.welcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            System.out.println(this.ui.readCommand(input, this.tasks));
            if (input.trim().toUpperCase().equals("BYE")) {
                break;
            }
        }
    }

    /**
     * Main.
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Mug().run();
    }

    public String getResponse(String input) {
        new Mug();
        return this.ui.readCommand(input, this.tasks);
    }
}
