package duke.task;

import java.util.HashMap;

public class Task {
    private String name;
    private boolean isDone;

    public Task() {
        this.name = "";
        this.isDone = false;
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void doTask() {
        this.isDone = true;
    }

    public HashMap<String, String> convertToHashMap() {
        HashMap<String, String> dict = new HashMap<>();
        dict.put("type", "duke.task.Task");
        dict.put("name", this.name);
        dict.put("done", this.isDone ? "true" : "false");
        return dict;
    }

    public String toString() {

        String checkMark;

        // Print tick if done and cross if not done
        if (this.isDone) {
            checkMark = "\u2713";
        } else {
            checkMark = "\u2718";
        }

        return "[" + checkMark + "] " + this.name;
    }
}
