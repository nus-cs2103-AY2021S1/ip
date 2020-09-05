package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.List;

/**
 * Manages the history of the Duke app.
 */
public class DukeStateManager {

    /**
     * Represents a point in the history of the app
     */
    static class Node {
        Node next;
        Node previous;
        DukeState state;

        Node(DukeState state) {
            this.state = state;
        }
    }

    /** Node containing the current DukeState **/
    private Node currentNode;

    public DukeStateManager(DukeState initialState) {
        this.currentNode = new Node(initialState);
    }

    public DukeState getCurrentState() {
        return currentNode.state;
    }

    /**
     * Stores a given DukeState as history.
     *
     * @param newState the DukeState to be stored as history
     */
    public void addHistory(DukeState newState) {
        Node newNode = new Node(newState);
        Node temp = currentNode.previous;
        newNode.previous = temp;
        newNode.next = currentNode;
        currentNode.previous = newNode;
    }

    /**
     * Undo a command by returning to a previous Node via a previous pointer.
     *
     * @return the new current DukeState after the undo is complete
     */
    public DukeState undo() throws IOException {
        Node original = currentNode;
        try {
            currentNode = currentNode.previous;
            List<Task> tasks = currentNode.state.getTaskList().getListOfTasks();
            currentNode.state.getStorage().updateTasks(tasks);
        } catch (IOException e) {
            currentNode = original;
            throw new IOException(e);
        }
        return currentNode.state;

    }

}
