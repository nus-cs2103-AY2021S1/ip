package duke;

import java.util.LinkedList;

import duke.task.TaskList;

/**
 * Class representing the past states of Duke's TaskList, used for undo and redo functionality.
 */
public class History {
    private LinkedList<TaskList> history;

    /**
     * Creates an empty end of History state, represented by a null object.
     */
    public History() {
        LinkedList<TaskList> newHistory = new LinkedList<>();
        newHistory.add(null);
        this.history = newHistory;
    }

    private boolean endOfHistory(TaskList taskList) {
        return taskList == null;
    }

    /**
     * Adds the input TaskList to history.
     *
     * @param taskList the TaskList to be added to History.
     */
    public void addToHistory(TaskList taskList) {
        // Deep copy
        TaskList newList = taskList.deepCopy();
        this.history.addLast(newList);
    }

    /**
     * Revert to previous state of Duke's TaskList.
     *
     * @return a TaskList representing the previous state of Duke's Tasks.
     * @throws DukeException when there are no more previous states to revert to.
     */
    public TaskList undoHistory() throws DukeException {
        TaskList previousState = this.history.pollLast();
        if (endOfHistory(previousState)) {
            throw new DukeException("I can not revert to any prior changes,"
                    + " you have reached the end of your history");
        }
        return previousState;
    }
}
