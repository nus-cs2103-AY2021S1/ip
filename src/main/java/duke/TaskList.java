package duke;

import java.util.ArrayList;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> items;
    Storage storage;

    public TaskList() {
        this.storage = new Storage();
        this.items = storage.readData();
    }

    public void bye() {
        try {
            storage.writeData(this.items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(Task toAdd) {
        this.items.add(toAdd);
        Ui.addLine(String.format("    Got it. I've added this task:\n    %s\n    Now you have %d tasks in the list.", toAdd, this.items.size()));
    }

    public void display() {
        String res = "Here are your tasks:\n";
        for (int i = 0; i < this.items.size(); i++) {
            res += String.format("    %d.%s\n", i + 1, this.items.get(i));
        }
        Ui.addLine(res);
    }

    public void display(ArrayList<Task> arrayList) {
        String res = "Here are your tasks:\n";
        for (int i = 0; i < arrayList.size(); i++) {
            res += String.format("    %d.%s\n", i + 1, arrayList.get(i));
        }
        Ui.addLine(res);
    }

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

    public void completeTask(int idx) throws InvalidIndexException {
        if (idx < 0 || idx >= this.items.size()) {
            throw new InvalidIndexException();
        }
        Task t = this.items.get(idx);
        t.complete();
        Ui.addLine(String.format("    Nice! I've marked this task as done:\n    %s", t));
    }

    public void deleteTask(int idx) throws InvalidIndexException {
        if (idx < 0 || idx >= this.items.size()) {
            throw new InvalidIndexException();
        }
        Task t = this.items.get(idx);
        this.items.remove(idx);
        Ui.addLine(String.format("    Nice! I've removed this task:\n    %s\n    Now you have %d tasks in the list.", t, this.items.size()));
    }
}