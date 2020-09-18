package duke.ui;

public class UiSideEffects {

    private static UiSideEffects instance;
    //CHECKSTYLE:OFF: VisibilityModifier
    public boolean uiGreet;
    public boolean uiReportCurrentTasks;
    public boolean uiReportNewTask;
    public boolean uiReportDoneTask;
    public boolean uiReportDeleteTask;
    public boolean uiReportExit;
    public boolean uiShowAllCommands;
    public boolean uiReportTagTask;
    public boolean uiReportUntagTask;
    //CHECKSTYLE:ON: VisibilityModifier

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
        uiReportCurrentTasks = false;
        uiReportNewTask = false;
        uiReportDeleteTask = false;
        uiReportDoneTask = false;
        uiReportExit = false;
        uiShowAllCommands = false;
        uiReportTagTask = false;
        uiReportUntagTask = false;
    }
}
