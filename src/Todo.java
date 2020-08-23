public class Todo extends Task {

    public Todo(String description){
        super(description);
    }

    public Todo(String description, boolean isDone){
        super(description);
        this.isDone = isDone;
    }
    public String saveText(){
        return "T | " + getStatusIcon() + " | " + description + " |\n";
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
