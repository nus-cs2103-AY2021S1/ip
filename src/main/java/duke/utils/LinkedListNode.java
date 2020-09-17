package duke.utils;

/**
 * A node in a doubly-linked list.
 */
public class LinkedListNode<E> {
    /** The value of this {@code LinkedListNode}. */
    private final E value;
    /** A reference to the next {@code LinkedListNode}. */
    private LinkedListNode<E> nextNode;
    /** A reference to the previous {@code LinkedListNode}. */
    private LinkedListNode<E> previousNode;

    /**
     * Constructs a new {@code LinkedListNode} object with the specified value.
     *
     * @param value the value to be contained.
     */
    public LinkedListNode(E value) {
        this.value = value;
    }

    /**
     * Returns the value contained within this {@code LinkedListNode}.
     *
     * @return the value contained within this {@code LinkedListNode}.
     */
    public E getValue() {
        return value;
    }

    /**
     * Sets the next {@code LinkedListNode} to point to.
     *
     * @param nextNode the next {@code LinkedListNode}.
     */
    public void setNextNode(LinkedListNode<E> nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * Returns the next {@code LinkedListNode}.
     *
     * @return the next {@code LinkedListNode}.
     */
    public LinkedListNode<E> getNextNode() {
        return nextNode;
    }

    /**
     * Sets the previous {@code LinkedListNode} to point to.
     *
     * @param previousNode the previous {@code LinkedListNode}.
     */
    public void setPreviousNode(LinkedListNode<E> previousNode) {
        this.previousNode = previousNode;
    }

    /**
     * Returns the previous {@code LinkedListNode}.
     *
     * @return the previous {@code LinkedListNode}.
     */
    public LinkedListNode<E> getPreviousNode() {
        return previousNode;
    }

    /**
     * Deletes all incoming and outgoing links for this {@code LinkedListNode}.
     */
    public void removeLinks() {
        if (nextNode != null) {
            nextNode.setPreviousNode(null);
            nextNode = null;
        }
        if (previousNode != null) {
            previousNode.setNextNode(null);
            previousNode = null;
        }
    }

    /**
     * Creates a link between {@code previousNode} and {@code nextNode}. If there are already existing links,
     * returns {@code false} without creating the new link.
     *
     * @param previousNode the previous node to be linked.
     * @param nextNode the next node to be linked.
     * @param <E> the type of the {@code LinkedListNode}s.
     * @return {@code true} if the links were successfully created; {@code false} otherwise.
     */
    public static <E> boolean linkNodes(LinkedListNode<E> previousNode, LinkedListNode<E> nextNode) {
        if (previousNode == null || nextNode == null) {
            // Unable to link non-existent node(s).
            return false;
        }

        if (previousNode.getNextNode() != null || nextNode.getPreviousNode() != null) {
            // Do not override existing links.
            return false;
        }

        previousNode.setNextNode(nextNode);
        nextNode.setPreviousNode(previousNode);

        return true;
    }
}
