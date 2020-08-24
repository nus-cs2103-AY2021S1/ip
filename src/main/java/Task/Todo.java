package Task;

public class Todo extends Task {

    public Todo(String name) {
        super(name, false);
    }

    public Todo (String name , boolean isDone) {
        super(name, isDone);
    }

    @Override
    public Task setToTrue(){
        return new Todo(this.name, true);
    }

    @Override
    public String getType(){
        return "T";
    }

    @Override
    public String getEnd(){
        return null;
    }

    @Override
    public String toString(){
        return isDone
                ? "[T][✓] " + this.getName()
                : "[T][✗] " + this.getName();
    }
}
