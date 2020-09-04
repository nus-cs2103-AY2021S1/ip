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
        return Ui.GREETING;
    }

    @Override
    public void close() { }

    @Override
    public String printList(TaskList list, Predicate<Task> predicate, String note) {
        String res = String.format(Ui.TASK_LIST_HEADING, note);
        return list.print(res, predicate);
    }
}
