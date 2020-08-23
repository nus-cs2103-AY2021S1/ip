package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private void printDashes() {
        int length = 60;
        System.out.println("_".repeat(length));
    }

    public void printWithDashes(String str) {
        printDashes();
        System.out.println(str);
        printDashes();
    }

    public void showWelcome() {
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n\n";
        String greeting = logo + " Hello! I'm duke.Duke\n" + " What can I do for you?";
        printWithDashes(greeting);
    }

    public void printExitMessage() {
        String bye = "Bye. Hope to see you again soon!";
        printWithDashes(" " + bye);
    }

    public void printAddTask(Task task, int length) {
        String message = " Got it. I've added this task:\n";
        message += " " + task;
        message += String.format("\n Now you have %s task%s in the list.", length, length > 1 ? "s" : "");
        printWithDashes(message);
    }

    private StringBuilder printListItems(StringBuilder builder, ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            builder.append(String.format(" %s. %s\n", i + 1, list.get(i)));
        }
        return builder;
    }

    public void printList(Tasks tasks) {
        StringBuilder builder = new StringBuilder(" Here are the tasks in your list:\n");
        printWithDashes(printListItems(builder, tasks.getTasks()).toString());
    }

    public void printFound(LocalDate localDate, ArrayList<Task> list) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDate = localDate.format(formatter);
        String intro = String.format(" Here are the tasks that I've found on %s:\n", formattedDate);
        StringBuilder builder = new StringBuilder(intro);
        printWithDashes(printListItems(builder, list).toString());
    }

    public void printMarkTaskAsDone(Task task) {
        printWithDashes(" Nice! I've marked this task as done:\n " + task);
    }

    public void printDeleteTask(Task task, int length) {
        String message = " Noted. I've removed this task:\n " + task;
        message += String.format("\n Now you have %s task%s in the list.", length, length > 1 ? "s" : "");
        printWithDashes(message);
    }

    public void printDukeException(DukeException ex) {
        printWithDashes(" ERROR: " + ex.getMessage());
    }

    public String readCommand() {
        if (this.scanner.hasNextLine()) {
            return this.scanner.nextLine(); 
        } else {
            this.scanner.close();
            return "bye";
        }
    }
}
