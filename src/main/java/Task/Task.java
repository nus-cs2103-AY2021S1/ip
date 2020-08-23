package Task;

public class Task {
    private String content;
    private Boolean isDone;
    private String uiOutput;
    public Task(String content) {
        this.isDone = false;
        this.content = content;
    }
    public void setUiOutput(String uiOutput) {
        this.uiOutput = uiOutput;
    }
    public String getUiOutput() {
        return this.uiOutput;
    }
    public boolean getStatus() { return this.isDone; }
    public String getContent() { return this.content; }
    public String returnStringForm() {
        return "[" + getStatusIcon() +"] " + this.content;
    }
    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }
    public void markAsDone() {
        this.isDone = true;
    }
}
