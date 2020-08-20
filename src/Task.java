public class Task {
    protected final String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone
                ? "[" + "Y" + "]"
                : "[" + "N" + "]";
    }


    public void setDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " "
                + this.description;
    }
}
