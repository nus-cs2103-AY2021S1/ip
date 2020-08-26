package duke;

import java.util.ArrayList;
import java.io.IOException;

/**
 * TaskList class to store all the tasks in Duke.
 */
public class TaskList {
    private ArrayList<Task> items;
    Storage storage;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.storage = new Storage();
        this.items = storage.readData();
    }

    /**
     * Exit function for TaskList class, writes data to .txt file.
     */
    public void bye() {
        try {
            storage.writeData(this.items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a task to the current TaskList.
     *
     * @param toAdd Task to be added.
     */
    public void add(Task toAdd) {
        this.items.add(toAdd);
        Ui.addLine(String.format("    Got it. I've added this task:\n    %s\n    Now you have %d tasks in the list.", toAdd, this.items.size()));
    }

    /**
     * Prints out all tasks in current TaskList.
     */
    public void display() {
        String res = "Here are your tasks:\n";
        for (int i = 0; i < this.items.size(); i++) {
            res += String.format("    %d.%s\n", i + 1, this.items.get(i));
        }
        Ui.addLine(res);
    }

    /**
     * Overloads display method.
     * @param arrayList ArrayList from which items are to be displayed.
     */
    public void display(ArrayList<Task> arrayList) {
        String res = "Here are your tasks:\n";
        for (int i = 0; i < arrayList.size(); i++) {
            res += String.format("    %d.%s\n", i + 1, arrayList.get(i));
        }
        Ui.addLine(res);
    }

    /**
     * Function to display all tasks with specified keyword.
     * @param keyWord Keyword to search for.
     * @throws InvalidDescriptionException In case keyword is empty.
     */
    public void find(String keyWord) throws InvalidDescriptionException {
        String[] arr = keyWord.split(" ");
        if (keyWord.length() == 4 || arr[1].equals("")) {
            throw new InvalidDescriptionException();
        }
        ArrayList<Task> temp = new ArrayList<>();
        for (Task task : this.items) {
            if (task.toString().contains(arr[1])) {
                temp.add(task);
            }
        }
        this.display(temp);
    }

    /**
     * Completes the specified task.
     *
     * @param idx Index of task to be completed.
     * @throws InvalidIndexException In case idx is out of bounds.
     */
    public void completeTask(int idx) throws InvalidIndexException {
        if (idx < 0 || idx >= this.items.size()) {
            throw new InvalidIndexException();
        }
        Task t = this.items.get(idx);
        t.complete();
        Ui.addLine(String.format("    Nice! I've marked this task as done:\n    %s", t));
    }

    /**
     * Deletes the specified task.
     *
     * @param idx Index of task to be deleted.
     * @throws InvalidIndexException In case idx is out of bounds.
     */
    public void deleteTask(int idx) throws InvalidIndexException {
        if (idx < 0 || idx >= this.items.size()) {
            throw new InvalidIndexException();
        }
        Task t = this.items.get(idx);
        this.items.remove(idx);
        Ui.addLine(String.format("    Nice! I've removed this task:\n    %s\n    Now you have %d tasks in the list.", t, this.items.size()));
    }
}