package dobby;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dobby.task.Deadline;
import dobby.task.Event;
import dobby.task.Task;
import dobby.task.TimedTask;
import dobby.task.Todo;

/**
 * Stores the tasks and performs operations or returns details depending on function called
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Create task with details from the input string and add to the tasks list
     */
    public void createFromStorage(String str) throws ParseException {
        boolean isDone = str.charAt(4) == '\u2713';
        Task task;
        if (str.charAt(1) == 'T') { // TODO
            String decription = str.substring(str.indexOf(' ') + 1);

            task = new Todo(decription);
            tasks.add(task);
        } else if (str.charAt(1) == 'D') { // DEADLINE
            String description = str.substring(str.indexOf(' ') + 1, str.indexOf("(by: ") - 1);
            String by = str.substring(str.indexOf("(by: ") + 5, str.length() - 1); //Aug 28 2020 4:00 pm

            int thirdIndex = by.indexOf(' ', 10);
            String dateText = by.substring(0, thirdIndex);
            String timeText = by.substring(thirdIndex + 1);
            LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("MMM d yyyy"));

            task = new Deadline(description, timeText, date);
            tasks.add(task);
        } else { // EVENT
            String description = str.substring(str.indexOf(' ') + 1, str.indexOf("(at: ") - 1);
            String at = str.substring(str.indexOf("(at: ") + 5, str.length() - 1);
            int thirdIndex = at.indexOf(' ', 10);

            String dateText = at.substring(0, thirdIndex);
            LocalDate date = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("MMM d yyyy"));

            String timeText = at.substring(thirdIndex + 1);
            task = new Event(description, timeText, date);

            tasks.add(task);
        }

        if (isDone) {
            task.setDone();
        }
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addToList(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns a string format of all the tasks in the list
     * @return String all tasks in the list in a particular format
     */
    public String getListedTasks() {
        int index = 0;
        String allTasks = "";
        for (Task task : (this.tasks)) {
            index++;
            allTasks = allTasks + index + ". " + task.getDescription();
            if (index < tasks.size()) {
                allTasks = allTasks + "\n";
            }
        }

        if (index == 0) {
            allTasks = "The task list is currently empty.";
        }

        return allTasks;
    }

    /**
     * Returns a string format of all the tasks in the list ,
     * scheduled on a particular date
     * @param date scheduled at
     * @return String all tasks in the list scheduled on given date,
     * in a particular format
     */
    public String getScheduledTasks(LocalDate date) {
        String message = "";
        int counter = 0;
        for (Task task: tasks) {
            if (task instanceof TimedTask) {
                TimedTask timedTask = (TimedTask) task;
                if (date.equals(timedTask.getDate())) {
                    message = message + String.format("%d. %s\n", ++counter, timedTask.getDescription());
                }
            }
        }

        return counter == 0 ? "The task list is currently empty." : message;
    }

    /**
     * Checks for tasks of the type of user requirement and returns a message of those tasks listed
     * @param type either D or T or E
     * @return message list of task descriptions of given type
     */
    public String findOfType(String type) {
        String message = "";
        assert List.of("T", "D", "E").contains(type) : "Type can be T, D, or E only";

        int counter = 0;
        for (Task task: this.tasks) {
            if ((task.getTag()).equals("[" + type + "]")) {
                message = message + String.format("%d. %s\n", ++counter, task.getDescription());
            }
        }

        if (counter == 0) {
            message = "There are no tasks of type " + type;
        }

        return message;
    }

    /**
     * Returns a message list of tasks whose descriptions contain the given keyword
     * @param keyword keyword to search for
     * @return message list of task descriptions of containing given keyword
     */
    public String findWithKeyword(String keyword) {
        String message = "";
        assert keyword != null : "String to search for cannot be empty";

        int counter = 0;
        for (Task task: this.tasks) {
            if ((task.getDescription()).contains(keyword)) {
                message = message + String.format("%d. %s\n", ++counter, task.getDescription());
            }
        }

        if (counter == 0) {
            message = "There are no tasks of containing the word - " + keyword;
        }

        return message;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }
}
