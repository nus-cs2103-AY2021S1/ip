package duke.Tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone){
        super(description, isDone);
    }

    @Override
    public String getOriginal(){
        return "todo " + getTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
