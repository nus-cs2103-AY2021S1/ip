package chatterbox;

import chatterbox.task.Task;
import chatterbox.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Chatterbox {
    private final TaskList tasks;
    private final Scanner scanner;

    public Chatterbox() {
        Storage store = new Storage();
        tasks = new TaskList(store);
        tasks.loadTasks();

        scanner = new Scanner(System.in);
    }

    public void run() {
        Ui.showWelcomeMessage();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            try {
                processInput(input);
            } catch (ChatterboxException | IOException e) {
                Ui.showErrorMessage(e.toString());
            }
            input = scanner.nextLine();
        }
        Ui.showFarewellMessage();
    }


    private void processInput(String input) throws ChatterboxException, IOException {
        // Check if input is just whitespace
        if (input.strip().equals("")) throw new ChatterboxException("Input cannot be empty.");

        // Get first word of input
        String command = (input + " ").split(" ")[0];

        // Process command
        if (command.equals("list")) {
            Ui.showMessage(tasks.getAllTasks());
        } else if (command.equals("done") || command.equals("delete")) {
            // Get the task number after the command, check if it is valid
            int taskNo;
            try {
                taskNo = Integer.parseInt(input.split(" ")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new ChatterboxException("Please enter a number after the command.");
            }

            // Mark as done or delete based on the command
            if (command.equals("done")) {
                tasks.setTaskAsDone(taskNo);
            } else {
                tasks.deleteTask(taskNo);
            }
        } else if (command.equals("deadline") || command.equals("todo") || command.equals("event")) {
            Task t = Parser.parse(input);
            tasks.addTask(t);
        } else {
            throw new ChatterboxException("That's not a valid command.");
        }
    }

    public static void main(String[] args) {
        new Chatterbox().run();
    }
}

