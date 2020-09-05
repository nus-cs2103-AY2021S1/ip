package duke;

public class DukeStateManager {

    static class Node {
        Node next;
        Node previous;
        DukeState state;

        Node(DukeState state) {
            this.state = state;
        }
    }

    private Node currentNode;

    public DukeStateManager(DukeState initialState) {
        this.currentNode = new Node(initialState);
    }

    public DukeState getCurrentState() {
        return currentNode.state;
    }

    public void addState(DukeState newState) {
        Node newNode = new Node(newState);
        currentNode.next = newNode;
        newNode.previous = currentNode;
        currentNode = newNode;
    }

    /**
     * Undo by returning to a previous Node via a previous pointer.
     *
     * @return the new current DukeState after the undo is complete
     */
    public DukeState undo() {
        currentNode = currentNode.previous;
        return currentNode.state;
    }

}
