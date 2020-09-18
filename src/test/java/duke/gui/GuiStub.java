package duke.gui;

import duke.Duke;
import duke.data.DukeCommandSet;
import duke.task.Task;

public class GuiStub extends GuiResponse {

    private GuiSideEffects sideEffects = GuiSideEffects.getInstance();

    public GuiStub(Duke duke) {
        super(duke);
    }

    @Override
    public void greet() {
        sideEffects.guiGreet = true;
    }

    @Override
    public void reportCurrentTasks() {
        sideEffects.guiReportCurrentTasks = true;
    }

    @Override
    public void reportNewTask(Task task) {
        sideEffects.guiReportNewTask = true;
    }

    @Override
    public void reportDoneTask(Task task) {
        sideEffects.guiReportDoneTask = true;
    }

    @Override
    public void reportDeleteTask(Task task) {
        sideEffects.guiReportDeleteTask = true;
    }

    @Override
    public void reportExit() {
        sideEffects.guiReportExit = true;
    }

    @Override
    public void showAllCommands(DukeCommandSet commandSet) {
        sideEffects.guiShowAllCommands = true;
    }

    @Override
    public void reportTagTask(Task task) {
        sideEffects.guiReportTagTask = true;
    }

    @Override
    public void reportUntagTask(Task task, boolean wasTagged) {
        sideEffects.guiReportUntagTask = true;
    }
}
