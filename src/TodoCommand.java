public class TodoCommand extends Command {

    public TodoCommand() {
        names = new String[] { "todo" };
    }

    @Override
    public void execute(String str) {
        ToDo newToDo = new ToDo(UIPrint.todoIcon, str);

        Duke.tasks.add(newToDo);
        Duke.reportNewTask(newToDo);
    }
}
