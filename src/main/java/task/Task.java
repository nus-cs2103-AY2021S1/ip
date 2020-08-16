package task;

public class Task {
    private String content;
    private boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public Task(String content, boolean isDone){
        this.content = content;
        this.isDone = isDone;
    }

    public String getContent() {
        return this.content;
    }

    public Task markTaskAsComplete(){
        return new Task(this.content, true);
    }

    public String getStatusIcon(){
        return "[" + (isDone ? "\u2713" : "\u2718") + "] ";
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getStatusIcon());
        stringBuilder.append(this.content);
        return stringBuilder.toString();
    }
}
