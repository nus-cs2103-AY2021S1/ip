package chatbot.util.comparator;

import java.util.Comparator;

import chatbot.data.Task;

public class TaskByDateReverseComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        return new TaskByDateComparator().compare(t2, t1);
    }
}
