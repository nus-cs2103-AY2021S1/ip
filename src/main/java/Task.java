public class Task {
    private boolean isDone = false;
    String description;


    public Task(String description) {
        this.description = description;
    }

    public void markAsDone(){
        isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[✓] " : "[✗] ") + description;
    }
}
