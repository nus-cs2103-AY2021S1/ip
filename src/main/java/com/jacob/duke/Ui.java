package main.java.com.jacob.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import main.java.com.jacob.duke.task.Task;

public class Ui {
    /**
     * UI of the Done command
     * @param taskDescription The currently operated task's status
     */
    public void showDone(String taskDescription) {
        System.out.println(" Nice! I've marked this task as done: \n" + taskDescription);
    }

    /**
     * UI for the newly added tasks
     * @param taskDescription The currently operated task's status
     * @param taskList List representation of the current task list
     */
    public void showNewTaskAdded(String taskDescription, List<Task> taskList) {
        System.out.printf(
                " Got it. I've added this task: \n   %s\n Now you have %d tasks in the list.\n", taskDescription, taskList.size());
    }

    /**
     * UI for the delete command
     * @param taskDescription The currently operated task's status
     * @param taskList List representation of the current task list
     */
    public void showTaskDeleted(String taskDescription, List<Task> taskList) {
        System.out.printf(" Noted. I've removed this task:\n "
                + "   %s\n"
                + " Now you have %d tasks in the list.\n", taskDescription, taskList.size());

    }

    /**
     * UI of the print list command
     * @param taskList List representation of the current task list
     */
    public void showFullList(List<Task> taskList) {
        int count = 1;
        System.out.println(" Here are the tasks in your list:");
        for (Task t: taskList) {
            System.out.println("  " + count + ". " + t.getCurrentStatus());
            count++;
        }
    }

    /**
     * UI of the print filtered list command
     * @param inputCommand Command includes the date time it is filtering for
     * @param taskList List representation of the current task list
     */
    public void showFilteredDateTimeList(String inputCommand, List<Task> taskList) throws DukeException {
        if (inputCommand.length() <= "list-due ".length()) {
            throw new DukeException(" list-due command cannot be empty!!");
        }
        //get the date time string from the initial string
        String dateTime = inputCommand.substring("list-due ".length());

        //get the date time object for comparison
        LocalDateTime filterDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));

        //check that the date and time is the same before printing
        Predicate<LocalDateTime> dateTimePredicate = x -> x.equals(filterDateTime);

        //print out the filtered items
        int count = 1;
        System.out.println(" Here are the tasks in your filtered list:");
        for (Task t : taskList) {
            if (t.getDueDateTime() != null && dateTimePredicate.test(t.getDueDateTime())) {
                System.out.println("  " + count + ". " + t.getCurrentStatus());
                count++;
            }
        }
    }

    /**
     * Show List of tasks with keyword
     * @param inputCommand Command includes keyword
     * @param taskList List representation of the current task list
     * @throws DukeException Throws Exception if the search string is empty
     */
    public void showKeywordList(String inputCommand, List<Task> taskList) throws DukeException {

        if (inputCommand.length() <= "find ".length()) {
            throw new DukeException(" find command cannot be empty!!");
        }
        //get the keyword string from the initial string
        String keyword = inputCommand.substring("find ".length());

        if (keyword.equals("")) {
            throw new DukeException(" Search String cannot be empty!");
        }
        //check that the date and time is the same before printing
        Predicate<String> searchStringPredicate = x -> x.contains(keyword);

        //print out the filtered items
        int count = 1;
        System.out.println(" Here are the tasks in your filtered list:");
        for (Task t : taskList) {
            if (t.getDescription() != null && searchStringPredicate.test(t.getDescription())) {
                System.out.println("  " + count + ". " + t.getCurrentStatus());
                count++;
            }
        }
    }

    /**
     * UI of the bye command
     */
    public void sayBye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * UI of the line printing
     */
    public void printLines() {
        System.out.println(" -----------------");
    }

    /**
     * UI of the welcome message
     */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Omo!! hello from\n" + logo);
    }

    /**
     * Handles console input
     * @return console input as String to be operated on
     */
    public String getConsoleInput() {
        //get console inputs
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
