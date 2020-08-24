public class Todo extends Task {

    public Todo (String name , boolean isDone) {
        super(name, isDone);
    }

    @Override
    public Task setToTrue(){
        return new Todo(this.name, true);
    }

    @Override
    public String toString(){
        return isDone
                ? "[T][✓] " + this.getName()
                : "[T][✗] " + this.getName();
    }
}
