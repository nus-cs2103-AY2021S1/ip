import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TaskList {
    private final String line = "---------------------------------------------";
    private ArrayList<Task> items;
    private int total;
    Storage storage;

    public TaskList() {
        this.storage = new Storage();
        this.items = storage.readData();
        this.total = items.size();
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
        this.total++;
        Ui.addLine(String.format("    Got it. I've added this task:\n    %s\n    Now you have %d tasks in the list.", toAdd, this.total));
    }

    public void display() {
        String res = "Here are your tasks:\n";
        for (int i = 0; i < this.total; i++) {
            res += String.format("    %d.%s\n", i + 1, this.items.get(i));
        }
        Ui.addLine(res);
    }

    public void completeTask(int idx) throws InvalidIndexException {
        if (idx < 0 || idx >= this.total) {
            throw new InvalidIndexException();
        }
        Task t = this.items.get(idx);
        t.complete();
        Ui.addLine(String.format("    Nice! I've marked this task as done:\n    %s", t));
    }

    public void deleteTask(int idx) throws InvalidIndexException {
        if (idx < 0 || idx >= this.total) {
            throw new InvalidIndexException();
        }
        Task t = this.items.get(idx);
        this.items.remove(idx);
        this.total--;
        Ui.addLine(String.format("    Nice! I've removed this task:\n    %s\n    Now you have %d tasks in the list.", t, this.total));
    }
}