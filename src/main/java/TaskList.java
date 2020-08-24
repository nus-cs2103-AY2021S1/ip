package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> todo;
    private Storage storage;
    final static String UNDERSCORE = "____________________________________________________________ \n";

    public TaskList(Storage storage) throws FileNotFoundException, DukeException {
        this.storage = storage;
        this.todo = storage.load();
    }

    public void delete(String line) throws DukeException, IOException {
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

    public void addEvent (String line) throws DukeInvalidDayException, DukeInvalidTaskException, IOException {
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

    public void addDeadline (String line) throws DukeInvalidDateException, DukeInvalidTaskException, IOException {
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

    public void addToDo (String line) throws DukeInvalidTaskException, IOException {
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

    public void checkOff (Integer taskNumber) {
        todo.get(taskNumber - 1).checkOff();
        System.out.println(UNDERSCORE + "Nice! I've marked this task as done: \n" +
                todo.get(taskNumber - 1) + "\n" + UNDERSCORE
        );
        storage.overwriteFile(todo);
    }

    public ArrayList<Task> getList() {
        return this.todo;
    }
}
