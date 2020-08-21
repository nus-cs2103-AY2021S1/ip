public class Task {
    protected String description;
    protected boolean isDone;
    protected int index;
    protected final String done = "[\u2713] ";
    protected final String start = "[\u2718] ";

    public Task(String description, int index) {
        this.description = description;
        this.isDone = false;
        this.index = index;
    }

    public String getStatusWithIndex() {
        return String.format("%s. %s%s", index, isDone ? this.done : this.start, this.description);
//        return isDone ? index + ". " + this.done /*"[\u2713] "*/ + this.description
//                : index + ". " + this.start/*"[\u2718] "*/ + this.description; //return tick or X symbols
    }

    public String toString() {
        return String.format("%s%s", isDone ? this.done : this.start, this.description);
        //return (isDone ? this.done/*"[\u2713] "*/ + this.description : this.start/*"[\u2718] "*/ + this.description);
    }
}