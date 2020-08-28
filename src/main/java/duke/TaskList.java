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

    public ArrayList<Task> getThingsToDo() {
        return thingsToDo;
    }

    public String printTodoList() {
        String message = "";
        for (int i = 0; i < thingsToDo.size(); i++) {
            String sign = thingsToDo.get(i).isDone() ? "✓" : "✗";
            message = message
                    + "     "
                    + (i + 1)
                    + "."
                    + thingsToDo.get(i).toString()
                    + "\n";
        }
        return message;
    }

    public void markAsDone(int i) {
        this.thingsToDo.get(i).setDone(true);
    }

    public int length() {
        return thingsToDo.size();
    }

    public Task getTask(int i) {
        return this.thingsToDo.get(i);
    }

    public void delete(int i) {
        this.thingsToDo.remove(i);
    }

    @Override
    public String toString() {
        return "Task{" +
                "thingsToDo=" + thingsToDo +
                '}';
    }

}
