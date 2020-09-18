public class Todo extends Task{
    private Todo(String name) {
        super(name);
    }

    public static Todo createTodo(String name) {
        return new Todo(name);
    }

    @Override
    public String returnMissingNameError() {
        return "The description of a todo task cannot be empty.";
    }

    @Override
    public String getSaveDataString() {
        String saveData = "";
        saveData += this.isDone ? 1 : 0;
        saveData += " T " + this.name;
        return saveData;
    }

    @Override
    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        return "[T]" + marked + this.name;
    }
}
