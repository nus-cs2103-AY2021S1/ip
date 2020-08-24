public abstract class Command {
    TaskManager tm;

    public void setTaskManager(TaskManager tm) {
        this.tm = tm;
    }
    public abstract boolean execute();
}