import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

    public void run() {
        ui.printHello();
        tasks = storage.load();
        boolean running = true;
        while (running) {
            String input = ui.readInput();
            String[] splitString = input.split(" ", 2);
            String command = splitString[0];
            int num;

            try {
                switch (command) {
                    case "bye":
                        running = false;
                        break;
                    case "list":
                        tasks.listTasks();
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        if (splitString.length == 1) {
                            throw new InvalidDescriptionException();
                        }
                        tasks.addTask(command, splitString[1].trim());
                        break;
                    case "done":
                        num = Integer.parseInt(splitString[1]);
                        tasks.doneTask(num);
                        break;
                    case "delete":
                        num = Integer.parseInt(splitString[1]);
                        tasks.deleteTask(num);
                        break;
                    default:
                        throw new InvalidCommandException();
                }
                storage.save(tasks);
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
