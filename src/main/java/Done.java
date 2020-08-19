public class Done extends Task {
    protected int taskNum;

    static final String MESSAGE = "Nice! I've marked this task as done:\n";
    static final String TYPE = "done";

    Done(int taskNum) {
        super(MESSAGE, TYPE);
        this.taskNum = taskNum;
    }

    public int getTaskNum() {
        return this.taskNum;
    }

    @Override
    public String toString() {
        return MESSAGE;
    }
}
