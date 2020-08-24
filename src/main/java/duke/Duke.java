package main.java.duke;

import main.java.duke.command.Command;
import main.java.duke.command.ExitCommand;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        String pathName = "./data/duke_data.csv";

        Storage storage = new Storage(pathName);
        TaskList tasks = storage.loadTasks();
        Ui ui = new Ui();
        ui.printHello();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Command command = Parser.parse(input);
            command.execute(storage, tasks, ui);
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }
}
