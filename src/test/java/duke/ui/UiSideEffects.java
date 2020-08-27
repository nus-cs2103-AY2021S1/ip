package duke.ui;

public class UiSideEffects {

    public boolean uiGreet;
    public boolean uiEcho;
    public boolean uiReportCurrentTasks;
    public boolean uiReportNewTask;
    public boolean uiReportDoneTask;
    public boolean uiReportDeleteTask;
    public boolean uiReportExit;

    private UiSideEffects() {
        uiGreet = false;
        uiEcho = false;
        uiReportCurrentTasks = false;
        uiReportNewTask = false;
        uiReportDeleteTask = false;
        uiReportDoneTask = false;
        uiReportExit = false;
    }

    private static UiSideEffects instance;
    public static UiSideEffects getInstance() {
        if (instance == null) {
            instance = new UiSideEffects();
        }
        return instance;
    }

    public void reset() {
        uiGreet = false;
        uiEcho = false;
        uiReportCurrentTasks = false;
        uiReportNewTask = false;
        uiReportDeleteTask = false;
        uiReportDoneTask = false;
        uiReportExit = false;
    }
}
