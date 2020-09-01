package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeInvalidDayException;
import duke.exception.DukeInvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * The TaskList class is used to keep track of the tasks given by the user.
 * Interacts with a storage object when changes to the task list is made.
 */

public class TaskList {
    private ArrayList<Task> todo;
    private Storage storage;

    /**
     * Initalizes a TaskList object
     *
     * @param storage object in which the file data is read, written and stored in.
     * @throws FileNotFoundException
     * @throws DukeException
     */
    public TaskList(Storage storage) throws FileNotFoundException, DukeException {
        this.storage = storage;
        this.todo = storage.load();
    }


    /**
     * Deletes the task from the task list.
     *
     * @param line the line in which the command of delete was given.
     * @throws DukeException
     */

    public String delete(String line) throws DukeException {
        String[] splits = line.split("delete ");
        String toReturn = Ui.showLine();
        try {
            if (splits.length > 1) {
                int taskNumber = Integer.parseInt(splits[1]);
                System.out.println(taskNumber);
                System.out.println(todo.size());
                if (taskNumber - 1 >= todo.size() || taskNumber - 1 < 0 || todo.size() == 0) {
                    throw new DukeException("Invalid task number");
                } else {
                    System.out.println("actually deleting");
                    int size = todo.size() - 1;
                    toReturn += "Noted. I've removed this task: \n"
                            + todo.get(taskNumber - 1) + "\n" + "Now you have " + size + " task in the list \n";
                    toReturn += Ui.showLine();
                    todo.remove(taskNumber - 1);
                    storage.overwriteFile(todo);
                    return toReturn;
                }
            } else {
                throw new DukeException("Please key in the task number to be marked done");
            }
        } catch (NumberFormatException err) {
            return "please input a valid number";
        }
    }

    /**
     * Adds an event task to the task list.
     *
     * @param line the line in which the command of adding event was given.
     * @throws DukeInvalidTaskException
     * @throws DukeInvalidDayException
     */

    public String addEvent (String line) throws DukeInvalidDayException, DukeInvalidTaskException {
        String[] splits = line.split("event |/at ");
        String toReturn = Ui.showLine();
        if (splits.length > 2) {
            Event task = new Event(splits[1], splits[2]);
            todo.add(task);
            toReturn += "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n"
                    + Ui.showLine();
            String textToAppend = "\nE | 0 | " + splits[1] + " | " + splits[2];
            storage.appendFile(textToAppend);
            return toReturn;
        } else if (splits.length > 1) {
            throw new DukeInvalidDayException();
        } else {
            throw new DukeInvalidTaskException();
        }
    }


    /**
     * Adds an deadline task to the task list.
     *
     * @param line the line in which the command of adding deadline task was given.
     * @throws DukeInvalidDateException
     * @throws DukeInvalidTaskException
     */
    public String addDeadline (String line) throws DukeInvalidDateException, DukeInvalidTaskException {
        String[] splits = line.split("deadline |/by ");
        String toReturn = Ui.showLine();
        if (splits.length > 2) {
            Deadline task = new Deadline(splits[1], splits[2]);
            todo.add(task);
            toReturn += "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n"
                    + Ui.showLine();
            String textToAppend = "\nD | 0 | " + splits[1] + " | " + splits[2];
            storage.appendFile(textToAppend);
            return toReturn;
        } else if (splits.length > 1) {
            throw new DukeInvalidDateException();
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    /**
     * Adds an todo task to the task list.
     *
     * @param line the line in which the command of adding todo task was given.
     * @throws DukeInvalidTaskException
     */
    public String addToDo (String line) throws DukeInvalidTaskException {
        String[] splits = line.split("todo ");
        String toReturn = Ui.showLine();
        if (splits.length > 1) {
            Todo task = new Todo(splits[1]);
            todo.add(task);
            toReturn += "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n"
                    + Ui.showLine();
            String textToAppend = "\nT | 0 | " + splits[1];
            storage.appendFile(textToAppend);
            return toReturn;
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    /**
     * Marks the task in the task list as done.
     *
     * @param taskNumber task number in which the task is done.
     */

    public String checkOff (Integer taskNumber) {
        todo.get(taskNumber - 1).checkOff();
        String toReturn = Ui.showLine();
        toReturn += "Nice! I've marked this task as done: \n"
                + todo.get(taskNumber - 1) + "\n";
        toReturn += Ui.showLine();
        storage.overwriteFile(todo);
        return toReturn;
    }

    /**
     * Returns the Array list in which the tasks are stored
     */

    public ArrayList<Task> getList() {
        return this.todo;
    }
}
