package duke;

import java.util.LinkedList;
import duke.task.*;

public class TaskList {
    private LinkedList<Task> list;

    public TaskList(LinkedList<Task> list) {
        this.list = list;
    }

    public TaskList() { this.list = new LinkedList<>(); }

    public LinkedList<duke.task.Task> getList() {
        return list;
    }

    public int size() { return list.size();}

    public void markAsDone(int index) { list.get(index).markAsDone(); }

    public void remove(int index) { list.remove(index); }

    public void add(Task newTask) { list.add(newTask); }
}
