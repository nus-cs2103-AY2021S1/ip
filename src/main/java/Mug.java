import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Scanner;

public class Mug {

    private final Ui ui;
    private final TaskList tasks;

    public Mug(String filePath) {
        this.ui = new Ui();
        Storage storage = new Storage(filePath);
        this.tasks = new TaskList(storage);
    }

    public void run() {
        this.ui.welcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            System.out.println(this.ui.readCommand(input, this.tasks));
            if (input.trim().equals("bye")) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Mug("mug.txt").run();
    }
}
