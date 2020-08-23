import java.io.*;
import java.util.Scanner;

public class Duke {

    private final Scanner sc;
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke() {
        sc = new Scanner(System.in);
        storage = new Storage();
        tasks = new TaskList();
        ui = new Ui();
    }

    private void start() {
        try {
            storage.loadTasks(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = sc.nextLine();
                Command c = Parser.parse(fullCommand);
                c.execute(storage, tasks, ui);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
