public class Task {

    protected String task;
    protected boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString(){
        if (done) {
            return String.format ("[DONE] %s", this.task);
        } else {
            return String.format ("[NOT DONE] %s", this.task);
        }
    }
}
