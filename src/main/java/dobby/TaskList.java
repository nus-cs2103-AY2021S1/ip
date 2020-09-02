package dobby;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
            String dt = by.substring(0, thirdIndex);
            String tm = by.substring(thirdIndex + 1);
            LocalDate date = LocalDate.parse(dt, DateTimeFormatter.ofPattern("MMM d yyyy"));

            task = new Deadline(description, tm, date);
            tasks.add(task);
        } else { // EVENT
            String description = str.substring(str.indexOf(' ') + 1, str.indexOf("(at: ") - 1);
            String at = str.substring(str.indexOf("(at: ") + 5, str.length() - 1);

            int thirdIndex = at.indexOf(' ', 10);
            String dt = at.substring(0, thirdIndex);
            String tm = at.substring(thirdIndex + 1);
            LocalDate date = LocalDate.parse(dt, DateTimeFormatter.ofPattern("MMM d yyyy"));

            task = new Event(description, tm, date);
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
        int i = 0;
        String allTasks = "";
        for (Task task : (this.tasks)) {
            i++;
            allTasks = allTasks + i + ". " + task.getDescription();
            if (i < tasks.size()) {
                allTasks = allTasks + "\n";
            }
        }

        if (i == 0) {
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

        int counter = 0;
        for (Task task: this.tasks) {
            if ((task.getTag()).equals("[" + type + "]")) {
                message = String.format("%d. %s", ++counter, task.getDescription());
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

        int counter = 0;
        for (Task task: this.tasks) {
            if ((task.getDescription()).indexOf(keyword) >= 0) {
                message = String.format("%d. %s\n", ++counter, task.getDescription());
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
