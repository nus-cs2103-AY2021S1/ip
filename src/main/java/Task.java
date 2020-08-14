public class Task {
    protected String desciption;
    protected boolean isDone;

    public Task(String description) {
        this.desciption = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.print(Duke.formatOut(Duke.donegreet() + "\n\t" + this.toString()));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(),this.desciption);
    }
}
