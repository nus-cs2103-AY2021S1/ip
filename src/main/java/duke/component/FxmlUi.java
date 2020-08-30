package duke.component;

import java.util.function.Predicate;

import duke.task.Task;

public class FxmlUi implements Ui {
    @Override
    public void print(String str) { }

    @Override
    public void output(String message) { }

    @Override
    public String readInput() {
        return null;
    }

    @Override
    public String greeting() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    @Override
    public void close() { }

    @Override
    public String printList(TaskList list, Predicate<Task> predicate, String note) {
        String res = "Here are the tasks " + note + "in your list:\n\n";
        return list.print(res, predicate);
    }
}
