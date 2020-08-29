package duke;

public class ToDo extends Task{

    ToDo(String name) {
        super(name);
    }

    @Override
    public String toString(){
        if (super.isDone){
            return "[T]" + "[" + "✓" + "] " + super.getName();
        } else {
            return "[T]" + "[" + "✗" + "] " + super.getName();
        }
    }
}
