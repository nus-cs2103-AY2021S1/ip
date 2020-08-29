package duke.component;

import java.util.function.Predicate;

import duke.task.Task;

public interface Ui {
    void print(String str);
    String printList(TaskList list, Predicate<Task> predicate, String note);
    void output(String message);
    String readInput();
    String greeting();
    void close();
}
