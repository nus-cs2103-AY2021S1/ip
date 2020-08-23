package duke.task;
public class Task {
    protected String description;
    protected boolean done = false;

    public Task(String description){
        this.description = description;

    }

    public String getDescription() {
        return this.description;
    }

    public void completeTask() {
        done = true;
    }

    @Override
    public String toString() {

        return (this.done ? "[✓]":"[✘]")+" "+this.description;
    }

    public String toFileString() {
        StringBuilder str = new StringBuilder();
        str.append(this.done ? "T" :"F");
        str.append("\n");
        str.append(this.description);
        str.append("\n");
        return str.toString();
    }
}
