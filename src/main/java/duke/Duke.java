package duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Scanner sc;

    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        sc = new Scanner(System.in);
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayGreeting();

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                parser.parse(input).execute(tasks, ui);
            } catch (DukeException e) {
                System.err.println(e);
            }
            input = sc.nextLine();
        }

        ui.displayExit();

        try {
            storage.storeTaskList(tasks);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}