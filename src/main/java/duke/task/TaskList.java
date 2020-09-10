package duke.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;

/**
 * contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Lists out all the tasks in the TaskList.
     */
    public String listTasks() {
        StringBuilder reply = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            reply.append("\n").append(i + 1).append(". ").append(task);
        }
        return reply.toString();
    }

    private String printTotalNumberOfTasks() {
        if (tasks.size() < 2) {
            return "\nNow you have " + tasks.size() + " task in the list.";
        } else {
            return "\nNow you have " + tasks.size() + " tasks in the list.";
        }
    }

    public String setDoneTask(int index, Storage storage) throws DukeException {
        String reply;
        try {
            Task task = tasks.get(index - 1); // index - 1 to match the index in ArrayList
            task.markDone();
            reply = "Nice! I've marked this task as done:"
                    + "\n\t" + task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing or invalid item number!");
        }
        try {
            storage.rewriteFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    /**
     * Deletes a Task from the TaskList and storage.
     *
     * @param index index of Task to be deleted from ArrayList
     * @param storage storage to save and load the Tasks when the program runs
     * @throws DukeException DukeException when index number is invalid
     */
    public String deleteTask(int index, Storage storage) throws DukeException {
        String reply;
        try {
            Task task = tasks.get(index - 1); // index -1 to match the index in ArrayList
            tasks.remove(index - 1); // index - 1 to match the index in ArrayList
            reply = "Noted. I've deleted this task:"
                    + "\n\t" + task + printTotalNumberOfTasks();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing or invalid item number!");
        }
        try {
            storage.rewriteFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reply;
    }

    /**
     *
     * @param taskName  name of the Task
     * @param storage storage to save and load the Tasks when the program runs
     * @throws DukeException DukeException
     * @throws IOException IOException
     */
    public String addTodo(String taskName, Storage storage) throws DukeException, IOException {
        if (taskName.isBlank()) {
            throw new DukeException("Description cannot be only empty spaces!");
        }
        Task task;
        if (taskName.contains("@")) {
            String [] taskTokens = taskName.split(" @");
            task = new Todo(taskTokens[0], taskTokens[1]);
            Tag.addTagIfNew(taskTokens[1]);
        } else {
            task = new Todo(taskName, "");
        }
        tasks.add(task);
        storage.appendToFile(task.toText());

        return "Got it. I've added this task:"
                + "\n\t" + task
                + printTotalNumberOfTasks();
    }

    /**
     * Adds a Deadline into the TaskList.
     *
     * @param taskName name of the Deadline to be added
     * @param storage storage to save and load the Tasks when the program runs
     * @throws DukeException DukeException
     * @throws IOException IOException
     */
    public String addDeadline(String taskName, Storage storage) throws DukeException, IOException {
        if (taskName.isBlank()) {
            throw new DukeException("Description cannot be only empty spaces!");
        }
        String[] taskArray = taskName.split(" /by ");
        boolean hasTag = taskName.contains("@");
        String tag = "";
        taskName = taskArray[0];
        String timeBy;
        if (hasTag) {
            timeBy = taskArray[1].split(" @")[0]
                    .replace(' ', 'T');
            tag = taskArray[1].split(" @")[1];
            Tag.addTagIfNew(tag);
        } else {
            timeBy = taskArray[1]
                    .replace(' ', 'T');
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HHmm");
        Task task;
        task = new Deadline(taskName, LocalDateTime.parse(timeBy, formatter), tag);
        tasks.add(task);
        storage.appendToFile(task.toText());

        return "Got it. I've added this task:"
                + "\n\t" + task
                + printTotalNumberOfTasks();
    }

    /**
     * Adds an Event into the TaskList.
     *
     * @param taskName name of the Event to be added
     * @param storage storage to save and load the Tasks when the program runs
     * @throws DukeException DukeException
     * @throws IOException IOException
     */
    public String addEvent(String taskName, Storage storage) throws DukeException, IOException {
        if (taskName.isBlank()) {
            throw new DukeException("Description cannot be only empty spaces!");
        }
        String[] taskArray = taskName.split(" /at ");
        boolean hasTag = taskName.contains("@");
        taskName = taskArray[0];
        String timeAt;
        String tag = "";
        if (hasTag) {
            timeAt = taskArray[1].split(" @")[0]
                    .replace(' ', 'T');
            tag = taskArray[1].split(" @")[1];
            Tag.addTagIfNew(tag);
        } else {
            timeAt = taskArray[1]
                    .replace(' ', 'T');
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HHmm");
        Task task;
        task = new Event(taskName, LocalDateTime.parse(timeAt, formatter), tag);
        tasks.add(task);
        //Ui.printAddSuccess(task);

        //printTotalNumberOfTasks();
        storage.appendToFile(task.toText());
        return "Got it. I've added this task:"
                + "\n\t" + task
                + printTotalNumberOfTasks();
    }

    /**
     * Finds tasks that match the keyword.
     *
     * @param keyWord keyword to search for task
     */
    public String findTasks(String keyWord) {
        ArrayList<String> matchedTasks = new ArrayList<>();

        for (Task task: tasks) {
            if (task.getDescription().contains(keyWord)) {
                matchedTasks.add(task.toString());
            }
        }
        StringBuilder reply = new StringBuilder("Here are the tasks that matched your search:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            String string = matchedTasks.get(i);
            reply.append("\n").append(i + 1).append(". ").append(string);
        }
        return reply.toString();
    }

    /**
     * Finds tasks that have this tag.
     *
     * @param queryTag query tag
     * @return tasks
     * @throws DukeException DukeException
     */
    public String findTasksWithTag(String queryTag) throws DukeException {
        if (!Tag.containsTag(queryTag)) {
            throw new DukeException("Tag does not exist!");
        }
        ArrayList<String> matchedTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.tag.getTagName().equals(queryTag)) {
                matchedTasks.add(task.toString());
            }
        }
        StringBuilder reply = new StringBuilder("Here are the tasks that matched your search:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            String string = matchedTasks.get(i);
            reply.append("\n").append(i + 1).append(". ").append(string);
        }
        return reply.toString();
    }
}
