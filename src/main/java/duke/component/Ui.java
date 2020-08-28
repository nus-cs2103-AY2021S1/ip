package duke.component;

import java.util.function.Predicate;

import duke.task.Task;

public interface Ui {
    public void print(String str);
    public String printList(TaskList list, Predicate<Task> predicate, String note);
    public void output(String message);
    public String readInput();
    public String greeting();
    public void close();
}
