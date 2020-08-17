public class Task {
    protected String description;
    protected boolean done = false;

    public Task(String description){
        this.description = description;

    }

    public void completeTask() {
        done = true;
    }

    @Override
    public String toString() {

        return (this.done ? "[✓]":"[✘]")+" "+this.description;
    }
}
