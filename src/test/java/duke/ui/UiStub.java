package duke.ui;

import duke.Duke;
import duke.task.Task;

public class UiStub extends Ui {

    private UiSideEffects sideEffects = UiSideEffects.getInstance();

    public UiStub(Duke duke) {
        super(duke);
    }

    @Override
    public void greet() {
        sideEffects.uiGreet = true;
    }

    @Override
    public void echo(String str) {
        sideEffects.uiEcho = true;
    }

    public void reportCurrentTasks() {
        sideEffects.uiReportCurrentTasks = true;
    }

    public void reportNewTask(Task task) {
        sideEffects.uiReportNewTask = true;
    }

    public void reportDoneTask(Task task) {
        sideEffects.uiReportDoneTask = true;
    }

    public void reportDeleteTask(Task task) {
        sideEffects.uiReportDeleteTask = true;
    }

    public void reportExit() {
        sideEffects.uiReportExit = true;
    }
}
