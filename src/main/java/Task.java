public class Task {

    private String task;
    private boolean done;

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
            return String.format ("[\u2713] %s", this.task);
        } else {
            return String.format ("[\u2718] %s", this.task);
        }
    }
}
