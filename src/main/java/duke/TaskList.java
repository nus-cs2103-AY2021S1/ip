package duke;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> thingsToDo;

    public TaskList() {
        this.thingsToDo = new ArrayList<>();
    }

    // TODO: 17/8/20 print type, done
    public Pair<String, Integer> add(Task detail) {
        this.thingsToDo.add(detail);
        return new Pair<>(detail.toString() + "\n", thingsToDo.size());
        // TODO: 26/8/20   change this to length
    }

    public String printTodoList() {
        String message = "";
        for (int i = 0; i < thingsToDo.size(); i++) {
            String sign = thingsToDo.get(i).done ? "✓" : "✗";
            message = message
                    + "     "
                    + (i + 1)
                    + "."
                    + thingsToDo.get(i).toString()
                    + "\n";
        }
        return message;
    }

    public String markAsDone(int i) {
        this.thingsToDo.get(i - 1).done = true;
        String message = "       " + this.thingsToDo.get(i - 1).toString() + "\n";
        return message;
    }

    public int length() {
        return thingsToDo.size();
    }

    public Pair<String, Integer> delete(int i) {
        String desc = this.thingsToDo.get(i - 1).toString();
        this.thingsToDo.remove(i - 1);
        return new Pair<>(desc + "\n", thingsToDo.size());
    }

    @Override
    public String toString() {
        return "Task{" +
                "thingsToDo=" + thingsToDo +
                '}';
    }

}
