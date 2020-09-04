package duke.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import duke.task.Task;

public class TaskList implements Iterable<Task> {

    protected List<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public void add(Task task) {
        listOfTasks.add(task);
    }

    public Task get(int index) {
        return listOfTasks.get(index - 1);
    }

    public Task remove(int index) {
        return listOfTasks.remove(index - 1);
    }

    public Iterator<Task> iterator() {
        return listOfTasks.iterator();
    }

    public int size() {
        return listOfTasks.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (Task task : listOfTasks) {
            if (count != 1) {
                builder.append('\n');
            }
            builder.append(count++ + "." + task.toString());
        }
        return builder.toString();
    }


    public String createTaskNumberCountMessage() {
        return "Now you have " + listOfTasks.size() + " tasks in the list.";
    }

}
