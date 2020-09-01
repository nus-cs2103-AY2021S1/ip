package bob.exceptions;

import bob.common.MsgGenerator;

/**
 * Exception representing index out of bounds for DONE and DELETE commands.
 */
public class BobListIndexOutOfBoundsException extends BobException {
    private final int totalNoOfTasks;
    private final int taskNo;
    private final String action;

    /**
     * Creates a BobListIndexOutOfBoundsException.
     *
     * @param totalNoOfTasks Total number of task in Bob's list.
     * @param taskNo Task number that was inputted.
     * @param action Intended action based on command.
     */
    public BobListIndexOutOfBoundsException (int totalNoOfTasks, int taskNo, String action) {
        super(MsgGenerator.generateListIndexOutOfBoundsMsg(totalNoOfTasks, taskNo, action));
        this.totalNoOfTasks = totalNoOfTasks;
        this.taskNo = taskNo;
        this.action = action;
    }

    @Override
    public String toString() {
        return MsgGenerator.generateListIndexOutOfBoundsMsg(totalNoOfTasks, taskNo, action);
    }
}
