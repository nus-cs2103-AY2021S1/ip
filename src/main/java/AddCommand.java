import java.util.Scanner;

public class AddCommand extends Command {

    public AddCommand() {
        super(false);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws
            InvalidTaskTypeException, EmptyDescriptionException,
            WrongFormatException {
        ui.printTaskTypes();
        String type = ui.readCommand();
        TaskType taskType;
        switch (type.toLowerCase()) {
            case "todo":
                taskType = TaskType.TODO;
                ui.printEnterTaskPrompt();
                break;
            case "deadline":
                taskType = TaskType.DEADLINE;
                ui.printDeadlineExample();
                break;
            case "event":
                taskType = TaskType.EVENT;
                ui.printEventExample();
                break;
            default:
                throw new InvalidTaskTypeException();
        }
        createTask(taskList, taskType, ui);
        Storage.saveTaskChanges(taskList);
    }

    public static void createTask(TaskList taskList, TaskType taskType, Ui ui)
            throws EmptyDescriptionException, WrongFormatException {
        String inputToAdd = ui.readCommand();
        if (inputToAdd.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        try {
            switch (taskType) {
                case TODO:
                    taskList.addTask(new Todo(inputToAdd, taskType));
                    break;
                case DEADLINE:
                    String[] deadlineSplit = inputToAdd.split(",");
                    taskList.addTask(new Deadline(deadlineSplit[0], deadlineSplit[1], taskType));
                    break;
                case EVENT:
                    String[] eventSplit = inputToAdd.split(",");
                    taskList.addTask(new Event(eventSplit[0], eventSplit[1], taskType));
                    break;
                default:
            }
            ui.printAddAcknowledgement(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new WrongFormatException();
        }
    }
}