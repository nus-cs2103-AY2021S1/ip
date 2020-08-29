public class Task {
    protected String command;
    protected boolean isDone;

    public Task(String command){
        this.command = command;
        this.isDone = false;
    }

    public String getCommand() {
        return this.command;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[\u2713]" + this.command;
        } else {
            return "[\u2718]" + this.command;
        }
    }
}
