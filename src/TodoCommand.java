import exception.NoDescriptionException;

public class TodoCommand extends Command {

    public TodoCommand() {
        names = new String[] { "todo" };
    }

    @Override
    public void execute(String str) throws NoDescriptionException {
        if (str.isBlank()) {
            String line = UIPrint.getLine(UIPrint.star, 50);
            String errMessage =
                    line + "OOPS!!! The description of a todo cannot be empty." + line;

            throw new NoDescriptionException(errMessage);
        }

        ToDo newToDo = new ToDo(UIPrint.todoIcon, str);

        Duke.tasks.add(newToDo);
        Duke.reportNewTask(newToDo);
    }
}
