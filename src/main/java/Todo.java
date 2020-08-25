public class Todo extends Task {
    protected Todo(String name, boolean isCompleted) {
        super(name, isCompleted);
    }

    public static Todo newTodo(String name){
        return new Todo(name, false);
    }

    public static Todo existingTodo(String name, boolean isCompleted){
        return new Todo(name, isCompleted);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toSaveString(){
        return "T" + " | " + super.toSaveString() + "\n";
    }
}
