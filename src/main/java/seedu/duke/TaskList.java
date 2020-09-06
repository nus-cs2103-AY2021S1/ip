package seedu.duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import seedu.duke.exception.DuplicateTaskException;
import seedu.duke.exception.InvalidTaskException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.ToDo;

/**
 * Represents a list of <code>Task</code>s in a fixed order.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Class constructor.
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to this task list.
     *
     * @param task the task to be added
     */
    public void add(Task task) throws DuplicateTaskException {
        for (Task t : this.tasks) {
            if (task.equals(t)) {
                throw new DuplicateTaskException("Task not added because duplicate found: " + t);
            }
        }
        this.tasks.add(task);
    }

    /**
     * Adds a new task to this task list.
     *
     * @param task the string representation of the task in a file
     * @throws IOException if tasks cannot be read from the file correctly
     */
    public void add(String task) throws IOException {
        // for reading from file
        String[] input = task.split("\\s\\|\\s");
        assert input.length >= 3;
        boolean isDone = input[1].equals("1");
        switch (input[0]) {
        case "T":
            ToDo todo = new ToDo(input[2], isDone);
            this.tasks.add(todo);
            break;
        case "D":
            assert input.length == 4;
            Deadline deadline = new Deadline(input[2], isDone, LocalDate.parse(input[3]));
            this.tasks.add(deadline);
            break;
        case "E":
            assert input.length == 4;
            Event event = new Event(input[2], isDone, LocalDate.parse(input[3]));
            this.tasks.add(event);
            break;
        default:
            throw new IOException("Error reading tasks from file.");
        }
    }

    /**
     * Marks a task in the list as completed.
     *
     * @param position the position of the task in the list (starting from 1)
     * @return the task that was marked as done
     * @throws InvalidTaskException if there is no task at the given position
     */
    public Task markAsDone(int position) throws InvalidTaskException {
        if (position <= 0 || position > this.tasks.size()) {
            throw new InvalidTaskException("Invalid task number.");
        }
        return this.tasks.get(position - 1).markAsDone();
    }

    /**
     * Deletes a task in the task list.
     *
     * @param position the position of the task in the list (starting from 1)
     * @return the task that was deleted
     * @throws InvalidTaskException if there is no task at the given position
     */
    public Task delete(int position) throws InvalidTaskException {
        if (position <= 0 || position > this.tasks.size()) {
            throw new InvalidTaskException("Invalid task number.");
        }
        return this.tasks.remove(position - 1);
    }

    /**
     * Prints out all the tasks in this list.
     *
     * @return a message with all tasks
     */
    public Message showList() {
        if (this.tasks.size() <= 0) {
            return new Message("No tasks added.");
        } else {
            String[] response = new String[this.tasks.size()];
            int position = 1;
            for (Task task : this.tasks) {
                response[position - 1] = position + ". " + task;
                position++;
            }
            return new Message(response);
        }
    }

    /**
     * Prints out tasks that take place or are due by a certain date.
     *
     * @param date the date entered by the user
     * @return a message with the appropriate tasks
     */
    public Message showList(LocalDate date) {
        if (this.tasks.size() <= 0) {
            return new Message("No tasks added.");
        } else {
            ArrayList<String> response = new ArrayList<>();
            int totalPosition = 1;
            boolean hasTask = false;
            for (Task task : this.tasks) {
                if (task.hasSameDate(date)) {
                    response.add(totalPosition + ". " + task);
                    hasTask = true;
                }
                totalPosition++;
            }
            if (!hasTask) {
                return new Message("No tasks found.");
            }
            return new Message(response.toArray(new String[0]));
        }
    }

    /**
     * Searches for tasks which contain a certain keyword
     *
     * @param keyword the keyword the user is looking for
     * @return a message with the appropriate tasks
     */
    public Message find(String keyword) {
        if (this.tasks.size() <= 0) {
            return new Message("No tasks added.");
        } else {
            ArrayList<String> response = new ArrayList<>();
            int totalPosition = 1;
            boolean hasTask = false;
            for (Task task : this.tasks) {
                if (task.containsKeyword(keyword)) {
                    response.add(totalPosition + ". " + task);
                    hasTask = true;
                }
                totalPosition++;
            }
            if (!hasTask) {
                return new Message("No tasks found.");
            }
            return new Message(response.toArray(new String[0]));
        }
    }

    /**
     * Iterates through the <code>Task</code>s in this list.
     *
     * @param consumer the action to be executed on each <code>Task</code>
     */
    public void forEach(Consumer<? super Task> consumer) {
        this.tasks.forEach(consumer);
    }
}
