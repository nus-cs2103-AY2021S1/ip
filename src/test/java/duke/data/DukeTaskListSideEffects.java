package duke.data;

public class DukeTaskListSideEffects {

    private static DukeTaskListSideEffects instance;

    //CHECKSTYLE:OFF: VisibilityModifier
    public boolean getTask;
    public boolean addTask;
    public boolean deleteTask;
    public boolean getSize;
    public boolean setTasks;
    public boolean getTasks;
    public boolean findTasks;
    //CHECKSTYLE:ON: VisibilityModifier

    private DukeTaskListSideEffects() {
        reset();
    }

    public static DukeTaskListSideEffects getInstance() {
        if (instance == null) {
            instance = new DukeTaskListSideEffects();
        }

        return instance;
    }

    /**
     * Resets all values to false.
     */
    public void reset() {
        getTask = false;
        addTask = false;
        deleteTask = false;
        getSize = false;
        setTasks = false;
        getTasks = false;
        findTasks = false;
    }
}
