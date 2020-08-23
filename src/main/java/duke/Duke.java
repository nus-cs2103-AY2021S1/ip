package duke;

import duke.exceptions.DukeException;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    protected TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        parser = new Parser();
    }

    public void run() {
        ui.welcome();
        tasks = new TaskList(storage.load());
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            ui.line();
            try {
                parser.command(str, tasks, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            ui.line();
            str = sc.nextLine();
        }
        sc.close();
        ui.exit();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }

}
