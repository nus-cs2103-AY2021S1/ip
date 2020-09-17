package duke.utils;

import java.util.EmptyStackException;

/**
 * A stack with a fixed size. The bottom-most element is removed when pushing a new element onto
 * an already full {@code EvictingStack}.
 */
public class EvictingStack<E> {
    /** The maximum size of this {@code EvictingStack}. */
    private final int maxSize;
    /** The current size of this {@code EvictingStack}. */
    private int size;
    /** A reference to the top-most {@code LinkedListNode} of this {@code EvictingStack}. */
    private LinkedListNode<E> topNode;
    /** A reference to the bottom-most {@code LinkedListNode} of this {@code EvictingStack}. */
    private LinkedListNode<E> bottomNode;

    /**
     * Constructs a new {@code EvictingStack} object with the specified maximum size.
     *
     * @param maxSize the maximum size.
     */
    public EvictingStack(int maxSize) {
        this.maxSize = maxSize;
        size = 0;
    }

    /**
     * Pushes an element onto the top of this stack.
     *
     * @param element the element to be pushed onto this stack.
     * @return the {@code element} argument.
     */
    public E push(E element) {
        if (size == maxSize) {
            // Remove the bottom-most element if the stack is full.
            removeBottomElement();
        }

        // Add a new node.
        LinkedListNode<E> newNode = new LinkedListNode<>(element);
        LinkedListNode.linkNodes(topNode, newNode);
        topNode = newNode;
        size++;

        // Set bottom node if the node that is being added is the only node.
        if (size == 1) {
            bottomNode = topNode;
        }

        return element;
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     *
     * @return the object at the top of this stack.
     * @throws EmptyStackException if this stack is empty.
     */
    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return topNode.getValue();
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     *
     * @return the object at the top of this stack.
     * @throws EmptyStackException if this stack is empty.
     */
    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        // Remove the top-most node.
        E value = topNode.getValue();
        LinkedListNode<E> previousNode = topNode.getPreviousNode();
        topNode.removeLinks();
        topNode = previousNode;
        size--;

        return value;
    }

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if and only if this stack contains no elements; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the {@code LinkedListNode} representation of the top-most element in this stack.
     *
     * @return the {@code LinkedListNode} representation of the top-most element in this stack.
     */
    public LinkedListNode<E> getTopNode() {
        return topNode;
    }

    /**
     * Removes the bottom-most element in this stack.
     */
    private void removeBottomElement() {
        LinkedListNode<E> nextNode = bottomNode.getNextNode();
        bottomNode.removeLinks();
        bottomNode = nextNode;
        size--;
    }
}
