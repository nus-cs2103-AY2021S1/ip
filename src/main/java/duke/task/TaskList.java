package duke.task;

import java.util.ArrayList;

import duke.common.Pair;

public class TaskList {
    private ArrayList<Task> thingsToDo;

    public TaskList() {
        this.thingsToDo = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * @param task the task to be added
     * @return a Pair, containing s String representation of the Task and the index number of the Task.
     */
    public Pair<String, Integer> add(Task task) {
        this.thingsToDo.add(task);
        return new Pair<>(task.toString() + "\n", thingsToDo.size());
        // TODO: 26/8/20   change this to length
    }

    public ArrayList<Task> getThingsToDo() {
        return thingsToDo;
    }

    /**
     * Formats the list for printing purposes.
     * @return a String representation of the list.
     */
    public String printTaskList() {
        String message = "";
        for (int i = 0; i < thingsToDo.size(); i++) {
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
        return "Task{" + "thingsToDo=" + thingsToDo + '}';
    }

    /**
     * Formats the list to a String for saving purposes.
     * @return a String representation of the list.
     */
    public String toSave() {
        String res = "";
        for (Task task : thingsToDo) {
            res = res
                    + task.getType().toString() + " "
                    + task.getDescription() + " "
                    + task.getDelimiter() + " "
                    + task.getDate() + "\n";
        }
        return res.trim();
    }
}
