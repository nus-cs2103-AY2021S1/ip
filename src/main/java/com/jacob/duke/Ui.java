package main.java.com.jacob.duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

import main.java.com.jacob.duke.task.Task;

public class Ui {
    /**
     * UI of the Done command
     * @param taskDescription The currently operated task's status
     */
    public String showDone(String taskDescription) {
        assert (taskDescription != null);
        return String.format(" Nice! I've marked this task as done: \n" + taskDescription);
    }

    /**
     * UI for the newly added tasks
     * @param taskDescription The currently operated task's status
     * @param taskList List representation of the current task list
     */
    public String showNewTaskAdded(String taskDescription, List<Task> taskList) {
        assert (taskDescription != null && taskList != null);
        return String.format(
                " Got it. I've added this task: \n   %s\n Now you have %d tasks in the list.\n",
                taskDescription, taskList.size());
    }

    /**
     * UI for the delete command
     * @param taskDescription The currently operated task's status
     * @param taskList List representation of the current task list
     */
    public String showTaskDeleted(String taskDescription, List<Task> taskList) {
        assert (taskDescription != null && taskList != null);
        return String.format(" Noted. I've removed this task:\n "
                + "   %s\n"
                + " Now you have %d tasks in the list.\n", taskDescription, taskList.size());

    }

    /**
     * UI of the print list command
     * @param taskList List representation of the current task list
     */
    public String showFullList(List<Task> taskList) {
        assert (taskList != null);
        int count = 1;
        StringBuffer stringList = new StringBuffer();
        stringList.append(" Here are the tasks in your list:\n");
        for (Task t: taskList) {
            stringList.append("  " + count + ". " + t.getCurrentStatus() + "\n");
            count++;
        }
        return stringList.toString();
    }

    /**
     * UI of the print filtered list command
     * @param inputCommand Command includes the date time it is filtering for
     * @param taskList List representation of the current task list
     * @throws DukeException Duke Exception thrown if the command is incorrect
     */
    @SuppressWarnings("checkstyle:JavadocMethod")
    public String showFilteredDateTimeList(String inputCommand, List<Task> taskList) throws DukeException {
        assert (inputCommand != null && taskList != null);
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
        StringBuffer stringList = new StringBuffer();
        stringList.append(" Here are the tasks in your filtered list:\n");
        for (Task t : taskList) {
            if (t.getDueDateTime() != null && dateTimePredicate.test(t.getDueDateTime())) {
                stringList.append("  " + count + ". " + t.getCurrentStatus() + "\n");
                count++;
            }
        }
        return stringList.toString();
    }

    /**
     * Show List of tasks with keyword
     * @param inputCommand Command includes keyword
     * @param taskList List representation of the current task list
     * @throws DukeException Throws Exception if the search string is empty
     */
    public String showKeywordList(String inputCommand, List<Task> taskList) throws DukeException {
        assert (inputCommand != null && taskList != null);
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
        StringBuffer stringList = new StringBuffer();
        stringList.append(" Here are the tasks in your filtered list:\n");
        for (Task t : taskList) {
            if (t.getDescription() != null && searchStringPredicate.test(t.getDescription())) {
                stringList.append("  " + count + ". " + t.getCurrentStatus() + "\n");
                count++;
            }
        }
        return stringList.toString();
    }

    /**
     * UI of the bye command
     */
    public String sayBye() {
        return " Bye. Hope to see you again soon!";
    }
}
