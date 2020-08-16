public class Todo {
    protected String description;
    protected boolean isDone;
    public Todo(String s) {
        this.description = s;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = !isDone;
    }

    @Override
    public String toString() {
        String checked = (isDone ? "[\u2713]" : "[\u2718]");
        return checked + " " + description;
    }
}
