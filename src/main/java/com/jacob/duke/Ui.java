package main.java.com.jacob.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

import main.java.com.jacob.duke.task.Task;

public class Ui {
    /**
     * UI of the Done command.
     *
     * @param taskDescription The currently operated task's status.
     */
    public String showDone(String taskDescription) {
        return String.format(" Nice! I've marked this task as done: \n" + taskDescription);
    }

    /**
     * Show UI for the newly added tasks.
     *
     * @param taskDescription The currently operated task's status.
     * @param taskList List representation of the current task list.
     */
    public String showNewTaskAdded(String taskDescription, List<Task> taskList) {
        return String.format(
                " Got it. I've added this task: \n   %s\n Now you have %d tasks in the list.\n",
                taskDescription, taskList.size());
    }

    /**
     * Show UI for the delete command.
     *
     * @param taskDescription The currently operated task's status.
     * @param taskList List representation of the current task list.
     */
    public String showTaskDeleted(String taskDescription, List<Task> taskList) {
        return String.format(" Noted. I've removed this task:\n "
                + "   %s\n"
                + " Now you have %d tasks in the list.\n", taskDescription, taskList.size());

    }

    /**
     * Show UI of the print list command.
     *
     * @param taskList List representation of the current task list.
     */
    public String showFullList(List<Task> taskList) {
        int count = 1;
        StringBuffer currentTaskList = new StringBuffer();
        currentTaskList.append(" Here are the tasks in your list:\n");
        for (Task t: taskList) {
            currentTaskList.append("  " + count + ". " + t.getCurrentStatus() + "\n");
            count++;
        }
        return currentTaskList.toString();
    }

    /**
     * Show UI of the print filtered list command.
     *
     * @param inputCommand Command includes the date time it is filtering for.
     * @param taskList List representation of the current task list.
     * @throws DukeException Duke Exception thrown if the command is incorrect.
     */
    @SuppressWarnings("checkstyle:JavadocMethod")
    public String showFilteredDateTimeList(String inputCommand, List<Task> taskList) throws DukeException {
        if (inputCommand.length() <= "list-due ".length()) {
            throw new DukeException(" list-due command cannot be empty!!");
        }
        //get the date time string from the initial string
        String dateTime = inputCommand.substring("list-due ".length());

        LocalDateTime dateTimeFilter = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));

        Predicate<LocalDateTime> dateTimePredicate = x -> x.equals(dateTimeFilter);

        int count = 1;
        StringBuffer dueDateList = new StringBuffer();
        dueDateList.append(" Here are the tasks in your filtered list:\n");
        for (Task t : taskList) {
            if (t.getDueDateTime() != null && dateTimePredicate.test(t.getDueDateTime())) {
                dueDateList.append("  " + count + ". " + t.getCurrentStatus() + "\n");
                count++;
            }
        }
        return dueDateList.toString();
    }

    /**
     * Show List of tasks with keyword.
     *
     * @param inputCommand Command includes keyword.
     * @param taskList List representation of the current task list.
     * @throws DukeException Throws Exception if the search string is empty.
     */
    public String showKeywordList(String inputCommand, List<Task> taskList) throws DukeException {

        if (inputCommand.length() <= "find ".length()) {
            throw new DukeException(" find command cannot be empty!!");
        }
        String keyword = inputCommand.substring("find ".length());

        if (keyword.equals("")) {
            throw new DukeException(" Search String cannot be empty!");
        }
        Predicate<String> searchStringPredicate = x -> x.contains(keyword);

        int count = 1;
        StringBuffer keywordList = new StringBuffer();
        keywordList.append(" Here are the tasks in your filtered list:\n");
        for (Task t : taskList) {
            if (t.getDescription() != null && searchStringPredicate.test(t.getDescription())) {
                keywordList.append("  " + count + ". " + t.getCurrentStatus() + "\n");
                count++;
            }
        }
        return keywordList.toString();
    }

    /**
     * UI of the bye command
     */
    public String sayBye() {
        return " Bye. Hope to see you again soon!";
    }
}
    /*
     * UI of the line printing
    public return printLines() {
        System.out.println(" -----------------");
    }
     * UI of the welcome message
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Omo!! hello from\n" + logo);
    }
     * Handles console input
     * @return console input as String to be operated on
    public String getConsoleInput() {
        //get console inputs
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }*/
