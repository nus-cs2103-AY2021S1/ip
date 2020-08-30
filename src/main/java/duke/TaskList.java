package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.CalendarException;
import duke.exception.DeleteException;
import duke.exception.DoneException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists all the tasks in the list.
     * @return All the tasks in the list.
     */
    public String listItems() {
        if (this.tasks.size() == 0) {
            return "You don't have any task in your list.";
        } else {
            StringBuilder todoList = new StringBuilder("Here are the task(s) in your list: \n");
            int num = 1;
            for (Task item : this.tasks) {
                todoList.append(num + ". " + item.toString() + "\n");
                num++;
            }
            return todoList.toString();
        }
    }

    /**
     * Deletes a task based on the corresponding number.
     *
     * @param index Index of the list containing the task
     * @return Delete message.
     * @throws DeleteException If the index < 0 or more than the size.
     */
    public String deleteTask(int index) throws DeleteException {
        if (index < 0 || index > tasks.size()) {
            throw new DeleteException("Please enter a valid task number.");
        } else {
            Task task = this.tasks.get(index);
            String msg;
            this.tasks.remove(task);
            Storage.updateData(this.tasks);
            msg = "Noted. I've removed this task for you: \n"
                + task.toString() + "\n"
                + "Now you have " + this.tasks.size() + " task(s) in the list.";
            return msg;
        }
    }

    /**
     * Saves the task to the list.
     * @param task Task to save to the list.
     * @return Save message.
     */
    public String saveToList(Task task) {
        this.tasks.add(task);
        Storage.updateData(this.tasks);
        String msg;
        msg = "Okay~ I've added this task: \n" + task.toString()
            + "\nNow you have " + this.tasks.size() + " task(s) in the list.";
        return msg;
    }

    /**
     * Returns the task corresponding to the task number.
     * @param taskNum Task number of the task.
     * @return The task corresponding to the task number.
     */
    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    /**
     * Marks the task corresponding to the task number as done.
     * @param taskNum Task number of the task.
     * @return Marked as done message.
     * @throws DoneException If the index < 0 or more than the size.
     */
    public String markTaskAsDone(int taskNum) throws DoneException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new DoneException("Please enter a valid task number.");
        } else {
            int index = taskNum - 1;
            String msg;

            Task oldTask = this.tasks.get(index);
            Task newTask = oldTask.markAsDone();

            this.tasks.remove(oldTask);
            this.tasks.add(index, newTask);
            Storage.updateData(this.tasks);

            msg = "YAYY! I've marked this task as done : \n"
                + newTask.toString();
            return msg;
        }
    }

    /**
     * Shows the all task(s) on the input day.
     * @param date Date to show.
     * @return All tasks on the input date.
     * @throws CalendarException If the date input is in invalid format.
     */
    public String showDate(String date) throws CalendarException {
        try {
            LocalDate localDate = LocalDate.parse(date);
            Boolean hasSomething = false;
            StringBuilder builder = new StringBuilder();
            builder.append("Task(s) on : " + localDate.getDayOfWeek() + ", "
                + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")));

            for (Task task : this.tasks) {
                String type = task.getType();
                if (type.equals("E")) {
                    Event event = (Event) task;
                    LocalDate eventDate = event.getDate();
                    if (eventDate.equals(localDate)) {
                        hasSomething = true;
                        builder.append("\n" + task.toString());
                    }

                } else if (type.equals("D")) {
                    Deadline deadline = (Deadline) task;
                    LocalDate deadlineDate = deadline.getDeadline();
                    if (deadlineDate.equals(localDate)) {
                        hasSomething = true;
                        builder.append("\n" + task.toString());
                    }

                } else {
                    continue;
                }
            }

            if (!hasSomething) {
                return "You have nothing to do on this day!";
            }

            return builder.toString();

        } catch (DateTimeParseException e) {
            throw new CalendarException("Invalid input date format. Please try again.");
        }
    }

    /**
     * Returns all the tasks in the list.
     * @return List containing all of the tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

}
