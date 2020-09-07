import java.io.IOException;
import java.util.ArrayList;

/**
 * Responsible for storing a list of Tasks and interaction with the Tasks.
 * A <code>TaskList</code> contains an <code>ArrayList</code> object.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    @Override
    public String toString() {
        String listString = "";
        for (Task task : tasks) {
            listString += task + "\n";
        }
        return listString;
    }

    /**
     *
     */
    public String getList(Ui ui) {
        if (tasks.size() == 0) {
            return ui.sayCurrentListIsEmpty();
        }
        else {
            String currentList = "";
            int count = 1;
            for (Task task : tasks) {
                currentList += count + ". " + task + "\n";
                count++;
            }
            return ui.sayCurrentList(currentList);
        }
    }

    /**
     *
     */
    public String markTaskDone(Ui ui, int taskNumber, Storage storage) throws IOException {
        Task task = tasks.get(taskNumber - 1);
        task.markDone();
        storage.writeFile(this);
        return ui.sayMarkedAsDone(task);
    }

    /**
     *
     */
    public String deleteTask(Ui ui, int taskNumber, Storage storage) throws IOException {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        storage.writeFile(this);
        return ui.sayDeletedTask(task, getListSize());
//        ui.say("I have deleted this task!");
//        System.out.println(task);
//        ui.say("You have " + getListSize() + " items in your task list now.");
    }

    /**
     * Adds a task to the back of the list.
     *
     * @param task The Task to be added.
     * @param ui A Ui object.
     */
    public String addTask(Task task, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.writeFile(this);
        return ui.sayAddedTask(task, getListSize());
    }

    /**
     *
     */
    public String findTask(Ui ui, String[] parsedInput) {
        if (tasks.size() > 0) {
            String body = parsedInput[1];
            String foundTasks = "";
            boolean noneFound = true;
            for (Task task : tasks) {
                if (task.toString().contains(body)) {
                    foundTasks += task + "\n";
                    noneFound = false;
                }
            }
            if (!noneFound) {
                return ui.sayFoundTasks(foundTasks);
            } else {
                return ui.sayNoMatchingFileFound();
            }
        } else {
            return ui.sayCurrentListIsEmpty();
        }
    }

    /**
     * Gets the size of the list.
     *
     * @return The list size.
     */
    public int getListSize() {
        return tasks.size();
    }
}