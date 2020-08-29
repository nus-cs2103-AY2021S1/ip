package duke.task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

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
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + ". " + task);
        }
    }

    private void printTotalNumberOfTasks() {
        int numTasks = tasks.size();
        if (numTasks < 2) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
    }

    public void setDoneTask(int index, Storage storage) {
        Task task = tasks.get(index - 1); // index - 1 to match the index in ArrayList
        task.markDone();
        System.out.println("Nice! I've marked this task as done:"
                + "\n\t" + task);
        try {
            storage.rewriteFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a Task from the TaskList and storage.
     *
     * @param index index of Task to be deleted from ArrayList
     * @param storage storage to save and load the Tasks when the program runs
     * @throws DukeException
     */
    public void deleteTask(int index, Storage storage) throws DukeException {
        try {
            Task task = tasks.get(index - 1); // index -1 to match the index in ArrayList
            tasks.remove(index - 1); // index - 1 to match the index in ArrayList
            System.out.println("Noted. I've deleted this task:"
                    + "\n\t" + task);
            printTotalNumberOfTasks();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing or invalid item number!");
        }
        try {
            storage.rewriteFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param taskName  name of the Task
     * @param storage storage to save and load the Tasks when the program runs
     * @throws DukeException DukeException
     * @throws IOException IOException
     */
    public void addTodo(String taskName, Storage storage) throws DukeException, IOException {
        if (taskName.isBlank()) {
            throw new DukeException("Description cannot be only empty spaces!");
        }
        Task task = new Todo(taskName);
        tasks.add(task);
        Ui.printAddSuccess(task);

        printTotalNumberOfTasks();
        storage.appendToFile(task.toText());
    }

    /**
     * Adds a Deadline into the TaskList.
     *
     * @param taskName name of the Deadline to be added
     * @param storage storage to save and load the Tasks when the program runs
     * @throws DukeException DukeException
     * @throws IOException IOException
     */
    public void addDeadline(String taskName, Storage storage) throws DukeException, IOException {
        if (taskName.isBlank()) {
            throw new DukeException("Description cannot be only empty spaces!");
        }
        String[] taskArray = taskName.split(" /by ");
        taskName = taskArray[0];
        String timeBy = taskArray[1].replace(' ', 'T');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HHmm");
        Task task = new Deadline(taskName, LocalDateTime.parse(timeBy, formatter));
        tasks.add(task);
        Ui.printAddSuccess(task);

        printTotalNumberOfTasks();
        storage.appendToFile(task.toText());
    }

    /**
     * Adds an Event into the TaskList.
     *
     * @param taskName name of the Event to be added
     * @param storage storage to save and load the Tasks when the program runs
     * @throws DukeException DukeException
     * @throws IOException IOException
     */
    public void addEvent(String taskName, Storage storage) throws DukeException, IOException {
        if (taskName.isBlank()) {
            throw new DukeException("Description cannot be only empty spaces!");
        }
        String[] taskArray = taskName.split(" /at ");
        taskName = taskArray[0];
        String timeAt = taskArray[1].replace(' ', 'T');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HHmm");
        Task task = new Event(taskName, LocalDateTime.parse(timeAt, formatter));
        tasks.add(task);
        Ui.printAddSuccess(task);

        printTotalNumberOfTasks();
        storage.appendToFile(task.toText());
    }

    /**
     * Finds tasks that match the keyword.
     *
     * @param keyWord keyword to search for task
     */
    public void findTasks(String keyWord) {
        ArrayList<String> matchedTasks = new ArrayList<>();

        for (Task task: tasks) {
            if (task.toString().contains(keyWord)) {
                matchedTasks.add(task.toString());
            }
        }
        System.out.println("Here are the tasks that matched your search:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            String string = matchedTasks.get(i);
            System.out.println(i + 1 + ". " + string);
        }
    }
}
