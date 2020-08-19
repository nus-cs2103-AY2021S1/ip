public class Done extends Task {
    protected int taskNum;

    static final String TYPE = "done";

    Done(int taskNum) {
        super(null, TYPE);
        this.taskNum = taskNum;
    }

    public int getTaskNum() {
        return this.taskNum;
    }

    @Override
    public String toString() {
        return "";
    }
}
