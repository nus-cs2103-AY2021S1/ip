public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }
    public String getTaskIdentifier(){
        return "T";
    }

    public static void todoCommand(TaskList taskList, String[] args) throws  DukeException {
        String description = args[0];
        if(description.equals(""))
            throw new DukeException(DukeException.Errors.TODO_EMPTY_DESCRIPTION);
        taskList.add(new Todo(description));
    }
}
