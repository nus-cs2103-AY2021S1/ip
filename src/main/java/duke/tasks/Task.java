package duke.tasks;

public class Task {

    protected String description;
    protected String type;
    protected boolean isDone;

    public Task(String description, String type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }
    
    public Task (String description, String type, boolean isDone) {
        this.description = description;
        this.type = type;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }
    
    public Boolean getDone(){
        return this.isDone;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getTime() {
        return "";
    }

    @Override
    public String toString(){
        if (isDone) {
            return String.format ("[DONE] %s", this.description);
        } else {
            return String.format ("[NOT DONE] %s", this.description);
        }
    }
}
