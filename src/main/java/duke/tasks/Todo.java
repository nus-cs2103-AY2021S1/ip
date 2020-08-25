package duke.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description, "Todo");
    }
    
    public Todo (String description, boolean isDone) {
        super (description,"Todo", isDone);
    }

    @Override
    public String toString(){
        if (isDone) {
            return String.format ("[T][DONE] %s", this.description);
        } else {
            return String.format ("[T][NOT DONE] %s", this.description);
        }
    }

}