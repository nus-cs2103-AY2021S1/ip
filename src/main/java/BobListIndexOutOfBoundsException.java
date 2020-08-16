public class BobListIndexOutOfBoundsException extends Exception {
    private final int totalNoOfTasks;
    private final int taskNo;
    private final String action;


    public BobListIndexOutOfBoundsException (int totalNoOfTasks, int taskNo, String action) {
        super("Erm, you are asking me to " + action + " task " + taskNo + " but there is/are only "+ totalNoOfTasks +" tasks. Please enter the correct number instead.");
        this.totalNoOfTasks = totalNoOfTasks;
        this.taskNo = taskNo;
        this.action = action;
    }


    @Override
    public String toString() {
        String errMsg = "Erm, you are asking me to " + action + " task "  + taskNo + " but there are only "+ totalNoOfTasks +" tasks. Please enter the correct number instead.\n";
        return "==========================================================================================================\n" +
                errMsg +
                "==========================================================================================================\n";
    }
}
