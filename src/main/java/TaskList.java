package main.java;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The TaskList class is used to keep track of the tasks given by the user.
 * Interacts with a storage object when changes to the task list is made.
 */

public class TaskList {
    private ArrayList<Task> todo;
    private Storage storage;
    final static String UNDERSCORE = "____________________________________________________________ \n";

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

    public void delete(String line) throws DukeException{
        String[] splits = line.split("delete ");
        try{
            if(splits.length > 1) {
                int taskNumber = Integer.parseInt(splits[1]);
                if (taskNumber - 1 >= todo.size() || taskNumber - 1 < 0 || todo.size() == 0){
                    throw new DukeException("Invalid task number");
                } else {
                    int size = todo.size() - 1;
                    System.out.println(UNDERSCORE + "Noted. I've removed this task: \n"
                            + todo.get(taskNumber - 1) + "\n" + "Now you have " + size + " task in the list \n"
                            + UNDERSCORE
                    );
                    todo.remove(taskNumber - 1);
    //                update storage file
                    storage.overwriteFile(todo);
                }
            } else {
                throw new DukeException("Please key in the task number to be marked done");
            }
        } catch (NumberFormatException err) {
            System.out.println("please input a valid number");
        }
    }

    /**
     * Adds an event task to the task list.
     *
     * @param line the line in which the command of adding event was given.
     * @throws DukeInvalidTaskException
     * @throws DukeInvalidDayException
     */

    public void addEvent (String line) throws DukeInvalidDayException, DukeInvalidTaskException {
        String[] splits = line.split("event |/at ");
        if (splits.length > 2){
            Event task = new Event(splits[1], splits[2]);
            todo.add(task);
            System.out.println(UNDERSCORE + "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n" + UNDERSCORE
            );
            String textToAppend = "\nE | 0 | " + splits[1] + " | " + splits[2];
            storage.appendFile(textToAppend);
        } else if (splits.length > 1){
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

    public void addDeadline (String line) throws DukeInvalidDateException, DukeInvalidTaskException {
        String[] splits = line.split("deadline |/by ");
        if (splits.length > 2) {
            Deadline task = new Deadline(splits[1], splits[2]);
            todo.add(task);
            System.out.println(UNDERSCORE + "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n" + UNDERSCORE
            );
            String textToAppend = "\nD | 0 | " + splits[1] + " | " + splits[2];
            storage.appendFile(textToAppend);
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

    public void addToDo (String line) throws DukeInvalidTaskException {
        String[] splits = line.split("todo ");
        if(splits.length > 1) {
            Todo task = new Todo(splits[1]);
            todo.add(task);
            System.out.println(UNDERSCORE + "Got it. I've added this to task: \n" + task + "\n"
                    + "Now you have " + todo.size() + " tasks in the list \n" + UNDERSCORE
            );
            String textToAppend = "\nT | 0 | " + splits[1];
            storage.appendFile(textToAppend);
        } else {
            throw new DukeInvalidTaskException();
        }
    }

    /**
     * Marks the task in the task list as done.
     *
     * @param taskNumber task number in which the task is done.
     */

    public void checkOff (Integer taskNumber) {
        todo.get(taskNumber - 1).checkOff();
        System.out.println(UNDERSCORE + "Nice! I've marked this task as done: \n" +
                todo.get(taskNumber - 1) + "\n" + UNDERSCORE
        );
        storage.overwriteFile(todo);
    }

    /**
     * Returns the Array list in which the tasks are stored
     */

    public ArrayList<Task> getList() {
        return this.todo;
    }
}
