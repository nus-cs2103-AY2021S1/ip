package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * TaskList class to store all the tasks in Duke.
 */
public class TaskList {
    private ArrayList<Task> items;
    private Storage storage;

    /**
     * Constructor for TaskList class.
     */
    public TaskList() {
        this.storage = new Storage();
        this.items = new ArrayList<>();
    }

    public void initialise() throws IOException, InvalidTypeException, InvalidDataException {
        try {
            this.items = this.storage.readData();
        } catch (IOException e) {
            throw e;
        } catch (InvalidTypeException e) {
            throw e;
        } catch (InvalidDataException e) {
            throw e;
        }
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    /**
     * Exit function for TaskList class, writes data to .txt file.
     */
    public void saveData() throws IOException {
        try {
            storage.writeData(this.items);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Adds a task to the current TaskList.
     *
     * @param toAdd Task to be added.
     */
    public String add(Task toAdd) {
        this.items.add(toAdd);
        return String.format("    Got it. I've added this task:\n    %s\n    "
                +
                "Now you have %d tasks in the list.", toAdd, this.items.size());
    }

    /**
     * Gets all tasks in TaskList
     * @return A formatted string.
     */
    public String display() {
        String res = "Here are your tasks:\n";
        for (int i = 0; i < this.items.size(); i++) {
            res += String.format("    %d.%s\n", i + 1, this.items.get(i));
        }
        return res;
    }

    /**
     * Overloads the display method.
     *
     * @param arrayList ArrayList of tasks to be displayed.
     * @return A formatted string.
     */
    public String display(ArrayList<Task> arrayList) {
        String res = "Here are your tasks:\n";
        for (int i = 0; i < arrayList.size(); i++) {
            res += String.format("    %d.%s\n", i + 1, arrayList.get(i));
        }
        return res;
    }

    /**
     * Function to find all tasks with specified keyword.
     *
     * @param keyWord Keyword to search for.
     * @return A String containing all relevant tasks.
     * @throws InvalidDescriptionException InvalidDescriptionException In case keyword is empty.
     */
    public String find(String keyWord) throws InvalidDescriptionException {
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
        return this.display(temp);
    }

    /**
     * Completes the specified task.
     *
     * @param idx Index of task to be completed.
     * @return A string to validate completion of task.
     * @throws InvalidIndexException In case idx is out of bounds.
     */
    public String completeTask(int idx) throws InvalidIndexException {
        if (idx < 0 || idx >= this.items.size()) {
            throw new InvalidIndexException();
        }
        Task t = this.items.get(idx);
        t.complete();
        return String.format("    Nice! I've marked this task as done:\n    %s", t);
    }

    /**
     * Deletes the specified task.
     *
     * @param idx Index of task to be deleted.
     * @return A string to validate deletion of task.
     * @throws InvalidIndexException In case idx is out of bounds.
     */
    public String deleteTask(int idx) throws InvalidIndexException {
        if (idx < 0 || idx >= this.items.size()) {
            throw new InvalidIndexException();
        }
        Task t = this.items.get(idx);
        this.items.remove(idx);
        return String.format("    Nice! I've removed this task:\n    %s\n    "
                +
                "Now you have %d tasks in the list.", t, this.items.size());
    }
}
