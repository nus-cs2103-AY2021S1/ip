import java.io.IOException;

public class AddCommand extends Command {
    String[] parsedStrings;

    public AddCommand(String[] parsedStrings) {
        this.parsedStrings = parsedStrings;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        String taskType = parsedStrings[0];
        String description = parsedStrings[1];
        String due;
        Task newTask;
        switch (taskType) {
            case "todo":
                newTask = new TodoTask(description);
                break;
            case "deadline":
                due = parsedStrings[2];
//                System.out.println(description);
//                System.out.println(due);
                newTask = new DeadlineTask(description, due);
                break;
            case "event":
                due = parsedStrings[2];
                newTask = new EventTask(description, due);
                break;
            default:
                throw new DukeException("I don't know what type of task this is :(");
        }
        taskList.add(newTask);
        ui.printAddMessage(newTask, taskList);
        super.execute(taskList, ui, storage);
    }
}
