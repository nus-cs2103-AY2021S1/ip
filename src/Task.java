public class Task {
    protected String description;
    protected boolean isDone;
    protected int num;

    public Task(String description, int num) {
        this.description = description;
        this.isDone = false;
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }

    public String getIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    public void done() {
        this.isDone = true;
    }

    public void markAsDone() {
        done();
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getIcon(), description);
    }

}
