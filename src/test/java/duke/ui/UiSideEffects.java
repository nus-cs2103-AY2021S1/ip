package duke.ui;

public class UiSideEffects {

    private static UiSideEffects instance;
    public boolean uiGreet;
    public boolean uiEcho;
    public boolean uiReportCurrentTasks;
    public boolean uiReportNewTask;
    public boolean uiReportDoneTask;
    public boolean uiReportDeleteTask;
    public boolean uiReportExit;

    private UiSideEffects() {
        reset();
    }
    public static UiSideEffects getInstance() {
        if (instance == null) {
            instance = new UiSideEffects();
        }
        return instance;
    }

    /**
     * Resets all values to false.
     */
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
