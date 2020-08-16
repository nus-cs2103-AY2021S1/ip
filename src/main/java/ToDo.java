public class ToDo extends Task {

    public static ToDo createNewToDo(String argument) {
        return new ToDo(argument);
    }
    
    private ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
