public class ToDoCommand extends AddTaskCommand {

    public ToDoCommand(String input) {
        this.input = input;
    }

    @Override
    public void addTask(TaskList taskList, String input) throws DukeException {
        taskList.addToDo(input);
    }
}
