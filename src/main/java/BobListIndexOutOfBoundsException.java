/**
 * Exception representing index out of bounds for DONE and DELETE commands
 * with inherited functionalities from Exception.
 * @author Lim Zi Yang
 */
public class BobListIndexOutOfBoundsException extends Exception {
    private final int totalNoOfTasks;
    private final int taskNo;
    private final String action;

    /** Divider */
    private static final String DIVIDER =
            "============================================================================================"
                + "==============\n";

    /**
     * Creates a BobListIndexOutOfBoundsException.
     * @param totalNoOfTasks Total number of task in Bob's list.
     * @param taskNo Task number that was inputted.
     * @param action Intended action based on command.
     */
    public BobListIndexOutOfBoundsException (int totalNoOfTasks, int taskNo, String action) {
        super("Erm, you are asking me to " + action + " task " + taskNo + " but there is/are only "
                + totalNoOfTasks + " tasks. Please enter the correct number instead.");
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
        String errMsg = "Erm, you are asking me to " + action + " task "  + taskNo + " but there are only "
                + totalNoOfTasks +" tasks. Please enter the correct number instead.\n";
        return DIVIDER + errMsg + DIVIDER;
    }
}
