package tasks;

import DukeException.DukeException;
import storage.Storage;

import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class TaskList {

    protected ArrayList<Task> tasks;
    protected Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
    }

    public Storage getStorage() {
        return this.storage;
    }

    protected void addToList(Task task) {
        tasks.add(task);
    }

    protected int getNumList() {
        return this.tasks.size();
    }

    public void setDoneList(String command) {
        try {
            int index = parseInt(command.split(" ")[1]);
            Task doneTask = tasks.get(index - 1);
            doneTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            ;
        } catch (NumberFormatException e) {
            ;
        }

    }

    public void deleteList(String command) {
        try {
            int index = parseInt(command.split(" ")[1]);
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            ;
        } catch (NumberFormatException e) {
            ;
        }

    }

    protected static String processTasks(Task task) {
        String result = "";
        int isDone = task.getIsDone().equals("[\u2713] ") ? 1 : 0;
        if (task instanceof Todo) {
            result = "T | " + isDone + " | " + task.getTaskName();
        } else if (task instanceof Deadline) {
            result = "D | " + isDone + " | " + task.getTaskName() + " | " + ((Deadline) task).getByDate();
        } else if (task instanceof Event) {
            result = "E | " + isDone + " | " + task.getTaskName() + " | " + ((Event) task).getAtDate();
        }
        return result;
    }

    public void addToFile(Task task) {
        String taskString = processTasks(task);
        storage.saveData(taskString);
        addToList(task);

        System.out.println("New task added!");
        System.out.println(task);
        System.out.println("You now have " + getNumList() + " tasks.");
    }

    /*public void readList() {
        if (storage.getNumOfTasks() == 0) {
            System.out.println("Looks like you don't have any tasks! Go on and add some!");
        } else {
            System.out.println("Here's all your tasks to complete:");
            storage.readFile();
            System.out.println("Time to get to work! :D");
        }
    }*/

    public void readList() {
        if (tasks.isEmpty()) {
            System.out.println("Looks like you don't have any tasks! Go on and add some!");
        } else {
            System.out.println("Here's all your tasks to complete:");
            int i = 1;
            for (Task task : tasks) {
                System.out.println(i + ". " + task);
                i++;
            }
            System.out.println("Time to get to work! :D");
        }
    }

}
