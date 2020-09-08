package chatbot.util.comparator;

import chatbot.data.Task;

import java.util.Comparator;

public class TaskByDateReverseComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        return new TaskByDateComparator().compare(t2, t1);
    }
}
