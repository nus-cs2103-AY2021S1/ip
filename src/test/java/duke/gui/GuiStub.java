package duke.gui;

import duke.Duke;
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
    public void echo(String str) {
        sideEffects.guiEcho = true;
    }

    public void reportCurrentTasks() {
        sideEffects.guiReportCurrentTasks = true;
    }

    public void reportNewTask(Task task) {
        sideEffects.guiReportNewTask = true;
    }

    public void reportDoneTask(Task task) {
        sideEffects.guiReportDoneTask = true;
    }

    public void reportDeleteTask(Task task) {
        sideEffects.guiReportDeleteTask = true;
    }

    public void reportExit() {
        sideEffects.guiReportExit = true;
    }
}
