package duke.component;

import java.util.function.Predicate;

import duke.task.Task;

public interface Ui {
    static String ADD_TASK_OUTPUT_FORMAT = "Got it. I've added this task:\n%S\nNow you have %d%s in the list.";
    void print(String str);
    String printList(TaskList list, Predicate<Task> predicate, String note);
    void output(String message);
    String readInput();
    String greeting();
    void close();
}
