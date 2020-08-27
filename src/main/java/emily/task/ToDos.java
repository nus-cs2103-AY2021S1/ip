package main.java.emily.task;

/**
 * Represents a task that has a simple description
 */
public class ToDos extends Task{

    public ToDos(String description) {
        super(description);
        this.type = 'T';
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
