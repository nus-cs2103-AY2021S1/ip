package main.java;

import java.util.Scanner;

public class Duke {

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Duke() {
        // Create storage
        storage = new Storage();

        // Read from hard disk
        tasks = new TaskList(storage.readFromHardDisk());

        // Initialize Ui
        ui = new Ui();
    }

    public void run() {
        // Display greeting message
        ui.showLine();
        ui.showGreetingMessage();
        ui.showLine();

        // Process user input
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String input = sc.nextLine();
            try {
                Command command = Parser.parse(input);
                ui.showLine();
                command.perform(tasks);
                ui.showLine();
                storage.writeToHardDisk(tasks);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showLine();
                System.out.println(e.getMessage());
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
