package duke.task; 

import java.util.List;
import duke.DukeException;
import duke.util.Parser;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String keyword, String description) throws DukeException {
        switch (keyword) {
        case "todo":
            Task task = new ToDo(description, false);
            this.tasks.add(task);
            printAddedConfirmation(task);
            break;
        case "deadline":
            String[] splitSlash = description.split(" /by ");
            if (splitSlash.length != 2) {
                throw new DukeException("\tPaise! :') Please use the format: deadline <task> /by <time>" +
                        "\n\t\t*time format: <yyyy-mm-dd> or  <yyyy-mm-dd HH:mm>");
            }
            task = new Deadline(splitSlash[0], false, Parser.parseDate(splitSlash[1]));
            this.tasks.add(task);
            printAddedConfirmation(task);
            break;
        case "event":
            splitSlash = description.split(" /at ");
            if (splitSlash.length != 2) {
                throw new DukeException("\tPaise! :') Please use the correct format: event <task> /at <time> +" +
                        "\n\t\t*time format: <yyyy-mm-dd> or  <yyyy-mm-dd HH:mm>");
            }
            task = new Event(splitSlash[0], false, Parser.parseDate(splitSlash[1]));
            this.tasks.add(task);
            printAddedConfirmation(task);
            break;
        default:
            break;
        }
    }

    public void printAddedConfirmation(Task task) {
        int size = this.tasks.size();
        System.out.println("\tOkay! I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println(String.format("\tNow you have %d %s in the list. Jiayous! :D", size, size > 1 ? "tasks" : "task"));
    }

    public void markTaskAsDone(int index) throws DukeException {
        Task task = this.tasks.get(index);
        task.toggleIsDone();
        try {
            this.tasks.set(index, task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry! THe index is out of bounds! :')");
        }
        System.out.println("\tNice! I've marked this task as done:\n\t\t" + task);
    }

    public void deleteTask(int index) throws DukeException {
        Task task = this.tasks.get(index);
        int size = this.tasks.size();
        try {
            this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry! The index is out of bounds! :')");
        }
        System.out.println("\tOkay! I've removed this task:\n\t\t" + task.toString());
        System.out.println(String.format("\tNow you have %d %s in the list. Jiayous! :D", size - 1, size - 1 > 1 ? "tasks" : "task"));

    }

    public void displayTasks() {
        if (tasks.size() > 0) {
            System.out.println("\tThese are the tasks in your list. Jiayous! :)");
        } else {
            System.out.println("\tYou have no task in your list. :D");
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("\t\t%d. %s", i + 1, this.tasks.get(i).toString()));
        }
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

}
