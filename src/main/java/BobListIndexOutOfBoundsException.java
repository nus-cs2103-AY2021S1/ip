public class BobListIndexOutOfBoundsException extends Exception {
    private final int totalNoOfTasks;
    private final int taskNo;


    public BobListIndexOutOfBoundsException (int totalNoOfTasks, int taskNo) {
        super("Erm, you are asking me to mark task " + taskNo + " but there are only "+ totalNoOfTasks +" tasks. Please enter the correct number instead.");
        this.totalNoOfTasks = totalNoOfTasks;
        this.taskNo = taskNo;
    }


    @Override
    public String toString() {
        String errMsg = "Erm, you are asking me to mark task " + taskNo + " but there are only "+ totalNoOfTasks +" tasks. Please enter the correct number instead.\n";
        return "==========================================================================================================\n" +
                errMsg +
                "==========================================================================================================\n";
    }
}
