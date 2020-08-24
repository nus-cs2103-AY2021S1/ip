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
            throw DukeException.Errors.TODO_EMPTY_DESCRIPTION.create();
        taskList.add(new Todo(description));
    }
}
