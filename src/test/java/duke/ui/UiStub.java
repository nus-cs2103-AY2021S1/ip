package duke.ui;

import duke.Duke;
import duke.data.DukeCommandSet;
import duke.task.Task;

public class UiStub extends UiResponse {

    private UiSideEffects sideEffects = UiSideEffects.getInstance();

    public UiStub(Duke duke) {
        super(duke);
    }

    @Override
    public void greet() {
        sideEffects.uiGreet = true;
    }

    @Override
    public void reportCurrentTasks() {
        sideEffects.uiReportCurrentTasks = true;
    }

    @Override
    public void reportNewTask(Task task) {
        sideEffects.uiReportNewTask = true;
    }

    @Override
    public void reportDoneTask(Task task) {
        sideEffects.uiReportDoneTask = true;
    }

    @Override
    public void reportDeleteTask(Task task) {
        sideEffects.uiReportDeleteTask = true;
    }

    @Override
    public void reportExit() {
        sideEffects.uiReportExit = true;
    }

    @Override
    public void showAllCommands(DukeCommandSet commandSet) {
        sideEffects.uiShowAllCommands = true;
    }

    @Override
    public void reportTagTask(Task task) {
        sideEffects.uiReportTagTask = true;
    }

    @Override
    public void reportUntagTask(Task task) {
        sideEffects.uiReportUntagTask = true;
    }
}
