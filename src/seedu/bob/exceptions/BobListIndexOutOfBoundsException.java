package seedu.bob.exceptions;

import seedu.bob.common.Messages;

/**
 * Exception representing index out of bounds for DONE and DELETE commands
 * with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobListIndexOutOfBoundsException extends BobException {
    private final int totalNoOfTasks;
    private final int taskNo;
    private final String action;

    /**
     * Creates a BobListIndexOutOfBoundsException.
     * @param totalNoOfTasks Total number of task in Bob's list.
     * @param taskNo Task number that was inputted.
     * @param action Intended action based on command.
     */
    public BobListIndexOutOfBoundsException (int totalNoOfTasks, int taskNo, String action) {
        super(Messages.listIndexOutOfBoundsMsgGenerator(totalNoOfTasks, taskNo, action));
        this.totalNoOfTasks = totalNoOfTasks;
        this.taskNo = taskNo;
        this.action = action;
    }

    /**
     * Overridden toString method.
     * @return String value of the BobListIndexOutOfBoundsException.
     */
    @Override
    public String toString() {
        String errMsg = Messages.listIndexOutOfBoundsMsgGenerator(totalNoOfTasks, taskNo, action);
        return errMsg;
    }
}
