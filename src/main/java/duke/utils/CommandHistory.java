package duke.utils;

/**
 * Tracks the user input history along with the command history navigation state.
 */
public class CommandHistory {
    /** An {@code EvictingStack} object to keep track of the command history. */
    private final EvictingStack<String> commandHistory;
    /** A reference to the current {@code LinkedListNode} in the current navigation state. */
    private LinkedListNode<String> currentNode;

    /**
     * Constructs a new {@code CommandHistory} object which holds up to the maximum amount
     * of commands specified.
     *
     * @param maxCommands the maximum number of commands to keep track of.
     */
    public CommandHistory(int maxCommands) {
        commandHistory = new EvictingStack<>(maxCommands);
    }

    /**
     * Adds a command to the command history and resets the state of the command navigation.
     *
     * @param command the command to be added.
     */
    public void addCommand(String command) {
        commandHistory.push(command);
        currentNode = null;
    }

    /**
     * Returns the previous command. If the current command is already the earliest command,
     * then the current command is returned. If the command history is empty, returns {@code null}.
     *
     * @return the previous command.
     */
    public String navigateUp() {
        if (currentNode == null && commandHistory.getTopNode() == null) {
            return null;
        } else if (currentNode == null) {
            currentNode = commandHistory.getTopNode();
        } else if (currentNode.getPreviousNode() != null) {
            currentNode = currentNode.getPreviousNode();
        }
        return currentNode.getValue();
    }

    /**
     * Returns the next command. If the current command is already the latest command, then the
     * current command is returned. If the command history is empty, returns {@code null}.
     *
     * @return the next command.
     */
    public String navigateDown() {
        if (currentNode == null) {
            return null;
        } else if (currentNode.getNextNode() != null) {
            currentNode = currentNode.getNextNode();
        }
        return currentNode.getValue();
    }
}
