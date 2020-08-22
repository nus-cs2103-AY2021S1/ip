package duke.task;

import java.util.HashMap;

public class Task {
    private String name;
    private boolean done;

    public Task() {
        this.name = "";
        this.done = false;
    }

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void doTask() {
        this.done = true;
    }

    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> dict = new HashMap<>();
        dict.put("type", "duke.task.Task");
        dict.put("name", this.name);
        dict.put("done", this.done ? "true" : "false");
        return dict;
    }

    public String toString() {
        String check;
        if (this.done) {
            check = "\u2713";
        } else {
            check = "\u2718";
        }
        return "[" + check + "] " + this.name;
    }
}
