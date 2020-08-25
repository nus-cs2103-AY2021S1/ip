class EmptyTodo extends DukeException {
    public EmptyTodo() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}