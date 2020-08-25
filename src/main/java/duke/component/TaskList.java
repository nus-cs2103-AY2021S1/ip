package duke.component;

import duke.task.Task;

import java.util.LinkedList;

public class TaskList{
    private LinkedList<Task> list;

    public TaskList() { this.list = new LinkedList<>(); }

    public TaskList(LinkedList<Task> list) {
        this.list = list;
    }

    public void add(Task newTask) { list.add(newTask); }

    public void remove(int index) { list.remove(index); }

    public Task get(int index) {
        return list.get(index);
    }

    public LinkedList<Task> getList() {
        return list;
    }

    public void markAsDone(int index) { list.get(index).markAsDone(); }

    public int size() { return list.size();}

}
