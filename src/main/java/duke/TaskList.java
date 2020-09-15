package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Stores the list of task for the current Duke instance.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> list;

    /**
     * Instantiates a new TaskList object.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    private TaskList(ArrayList<Task> arr) {
        this.list = arr;
    }

    /**
     * Returns an iterator() of tasks in this TaskList object.
     *
     * @return Tasks
     */
    @Override
    public Iterator<Task> iterator() {
        return this.list.iterator();
    }

    /**
     * Returns number of tasks in this TaskList object.
     *
     * @return size
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Returns true if no tasks in this TaskList object.
     * Otherwise, returns false.
     *
     * @return <code>true</code> if empty, <code>false</code> otherwise.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Creates and adds a new task to the TaskList object.
     *
     * @param type describing task type.
     * @param input describing task information.
     * @return Task object if successfully created and added.
     * @throws DukeException if failed to add task.
     */
    public Task addTask(String type, String input) throws DukeException {
        return this.addTask(type, input, false);
    }

    /**
     * Creates and adds a new task to the TaskList object.
     *
     * @param type describing task type.
     * @param input describing task information.
     * @param done describing status of task.
     * @return Task object if successfully created and added.
     * @throws DukeException if failed to add task.
     */
    public Task addTask(String type, String input, boolean done) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("OOPS!!! I'm sorry, the description cannot be empty :<");
        }
        Task task;
        String[] splitTags = input.split("#");
        switch (type) {
        case "todo":
            task = new Todo(splitTags[0]);
            break;
        case "deadline":
            String[] arr = splitTags[0].split("/by");
            try {
                task = new Deadline(arr[0].trim(), arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! I'm sorry, when is the deadline? (/by...)");
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date-time format! (/by...)");
            }
            break;
        case "event":
            arr = splitTags[0].split("/at");
            try {
                task = new Event(arr[0].trim(), arr[1].trim());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! I'm sorry, when is the event? (/at...)");
            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid date-time format! (/at...)");
            }
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, I don't know what that means :<");
        }
        task = done ? task.setDone() : task;
        if (splitTags.length > 1) {
            for (String i: splitTags) {
                if (i != splitTags[0]) {
                    task.addTag(i.trim());
                }
            }
        }
        this.list.add(task);
        return task;
    }

    /**
     * Returns a TaskList containing TimedTask instances with the input date.
     *
     * @param date date of the timed-tasks we wish to retrieve.
     * @return TaskList object if successfully retrieved.
     * @throws DukeException if no tasks on the input date.
     */
    public TaskList getTimedTasks(LocalDate date) throws DukeException { // input of form "dd-MM-yyyy"
        try {
            ArrayList<Task> timedTasks = new ArrayList<>(this.list);
            timedTasks.removeIf(i -> !(i instanceof TimedTask));
            Task[] timedTasksArr = timedTasks.toArray(new Task[0]);
            for (Task i : timedTasksArr) {
                assert i instanceof TimedTask : "Non-timed tasks found.";
                if (!((TimedTask) i).getDate().equals(date)) {
                    timedTasks.remove(i);
                }
            }
            if (timedTasks.isEmpty()) {
                throw new DukeException("OOPS!!! I'm sorry, no tasks on that date :<");
            } else {
                return new TaskList(timedTasks);
                //return new TaskList(new ArrayList<>(timedTasks));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date-time format! (/at...)");
        }
    }

    /**
     * Sets the i-th task as done and returns the task
     *
     * @param i index of the tasks that was done.
     * @return Task if task is in this TaskList object.
     * @throws DukeException if index out of bounds.
     */
    public Task setDone(int i) throws DukeException {
        try {
            return this.list.get(i).setDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, the task number is out of range :<");
        }
    }

    /**
     * Deletes the i-th task and returns the task
     *
     * @param i index of the tasks to be deleted.
     * @return Task if task is in this TaskList object.
     * @throws DukeException if index out of bounds.
     */
    public Task deleteTask(int i) throws DukeException {
        try {
            return this.list.remove(i);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, the task number is out of range :<");
        }
    }

    /**
     * Tags the i-th task with input tag.
     *
     * @param i index of the tasks to be deleted.
     * @param tag String tag for the ith item.
     * @return Task if task is in this TaskList object.
     * @throws DukeException if index out of bounds.
     */
    public Task tagTask(int i, String tag) throws DukeException {
        try {
            return this.list.get(i).addTag(tag);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, the task number is out of range :<");
        }
    }

    /**
     * Returns a string representation of object.
     *
     * @return String object.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            int taskNum = i + 1;
            output.append(taskNum);
            output.append(".");
            output.append(this.list.get(i).toString());
            output.append((i == this.size() - 1) ? "" : '\n');
        }
        return output.toString();
    }

    /**
     * Returns tasks containing the keyword.
     * @param keyword
     * @return list of tasks containing keyword
     * @throws DukeException if no such task
     */
    public TaskList findTask(String keyword) throws DukeException {
        ArrayList<Task> keywordTasks = new ArrayList<>(this.list);
        keywordTasks.removeIf(i -> !i.getDescription().contains(keyword));
        if (keywordTasks.isEmpty()) {
            throw new DukeException("OOPS!!! I'm sorry, no such keyword :<");
        }
        return new TaskList(keywordTasks);
    }

    /**
     * Returns tasks containing the specific tag.
     * @param tag tag of tasks we want to retrieve
     * @return list of tasks containing tag
     * @throws DukeException if no such task
     */
    public TaskList findTag(String tag) throws DukeException {
        ArrayList<Task> taggedTasks = new ArrayList<>(this.list);
        taggedTasks.removeIf(i -> !i.hasTag(tag));
        if (taggedTasks.isEmpty()) {
            throw new DukeException("OOPS!!! I'm sorry, no such tag :<");
        }
        return new TaskList(taggedTasks);
    }

    /**
     * Removes a specific tag from a specific task.
     * @param tag tag of tasks we want to retrieve
     * @param i index of task to be untagged
     * @return task that was untagged
     * @throws DukeException if no such task
     */
    public Task untagTask(int i, String tag) throws DukeException {
        try {
            if (this.list.get(i).hasTag(tag)) {
                return this.list.get(i).untag(tag);
            } else {
                throw new DukeException("No such tag for the task");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! I'm sorry, the task number is out of range :<");
        }
    }

    /**
     * Removes a specific tag from all task.
     * @param tag tag of tasks we want to retrieve
     * @return list of tasks containing tag
     * @throws DukeException if no such task
     */
    public TaskList untagAllTasks(String tag) throws DukeException {
        ArrayList<Task> taggedTasks = new ArrayList<>(this.list);
        taggedTasks.removeIf(i -> !i.hasTag(tag));
        if (taggedTasks.isEmpty()) {
            throw new DukeException("OOPS!!! I'm sorry, no such tag :<");
        }
        Object[] arr = taggedTasks.stream().map((Task i) -> i.untag(tag)).toArray();
        return new TaskList(new ArrayList(Arrays.asList(arr)));
    }

}
