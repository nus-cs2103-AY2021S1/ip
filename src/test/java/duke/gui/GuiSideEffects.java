package duke.gui;

public class GuiSideEffects {

    private static GuiSideEffects instance;
    //CHECKSTYLE:OFF: VisibilityModifier
    public boolean guiGreet;
    public boolean guiReportCurrentTasks;
    public boolean guiReportNewTask;
    public boolean guiReportDoneTask;
    public boolean guiReportDeleteTask;
    public boolean guiReportExit;
    public boolean guiShowAllCommands;
    public boolean guiReportTagTask;
    public boolean guiReportUntagTask;
    //CHECKSTYLE:ON: VisibilityModifier

    private GuiSideEffects() {
        reset();
    }
    public static GuiSideEffects getInstance() {
        if (instance == null) {
            instance = new GuiSideEffects();
        }
        return instance;
    }

    /**
     * Resets all values to false.
     */
    public void reset() {
        guiGreet = false;
        guiReportCurrentTasks = false;
        guiReportNewTask = false;
        guiReportDeleteTask = false;
        guiReportDoneTask = false;
        guiReportExit = false;
        guiShowAllCommands = false;
        guiReportTagTask = false;
        guiReportUntagTask = false;
    }
}
