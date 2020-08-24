package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    private static final String horizontalLine = "      ===================================";
    private static final String indentation = "      ";
    private Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() throws DukeException {
        if (this.scanner.hasNext()) {
            return scanner.nextLine().trim();
        } else {
            throw new DukeException("No next line");
        }
    }

    public void showWelcome() {
        String logo = indentation + " ____        _        \n"
                + indentation + "|  _ \\ _   _| | _____ \n"
                + indentation + "| | | | | | | |/ / _ \\\n"
                + indentation + "| |_| | |_| |   <  __/\n"
                + indentation + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(horizontalLine);
        System.out.println(indentation + "Hello from\n" + logo);
        System.out.println(horizontalLine);
    }

    public void showGoodbye() {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Have a nice day.");
        System.out.println(horizontalLine);
    }

    public void showError(String message) {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Error: " + message);
        System.out.println(horizontalLine);
    }

    public void showTaskAdded(Task task) {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Task added: " + task);
        System.out.println(horizontalLine);
    }

    public void showTaskDeleted(Task task) {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Task deleted: " + task);
        System.out.println(horizontalLine);
    }

    public void showTaskMarkedDone(Task task) {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Task marked as done: " + task);
        System.out.println(horizontalLine);
    }

    public void showIndentedMessage(String message) {
        System.out.println(indentation + message);
    }

    public void showHorizontalLine() {
        System.out.println(horizontalLine);
    }
}
