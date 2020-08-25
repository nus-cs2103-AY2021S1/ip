package duke;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    public void markDone(int index) throws IndexOutOfBoundsException {
        try {
            get(index).markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No task with this number");
        }
    }

    public void delete(int index) throws IndexOutOfBoundsException {
        try {
            remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No task with this number");
        }
    }

    public TaskList find(String s) {
        TaskList newList = new TaskList();
        for (int i = 0; i < size(); i++) {
            if (get(i).contains(s)) {
                newList.add(get(i));
            }
        }
        return newList;
    }
}
