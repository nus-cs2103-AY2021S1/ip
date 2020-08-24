package task;

public class Task {
    protected String content;
    protected boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public void markTaskAsDone(){
        isDone = true;
    };

    public String getContent() {
        return this.content;
    }

    public boolean getIsDone(){
        return this.isDone;
    }

    public String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] ";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getStatusIcon());
        stringBuilder.append(this.content);
        return stringBuilder.toString();
    }
}
