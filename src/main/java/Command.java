public class Command {
    protected CommandType commandType;
    protected String[] inputs;
    protected boolean isExit;

    public Command(CommandType commandType, String[] inputs) {
        this.commandType = commandType;
        this.inputs = inputs;
    }

    public boolean isExit() {
        return isExit;
    }

    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        switch (commandType) {
            case BYE:
                ui.printBye();
                isExit = true;
                return;
            case HELP:
                ui.printHelp();
                break;
            case LIST:
                ui.showList(tasks);
                break;
            case DONE:
                if (inputs.length <= 1) {
                    throw new DukeException("OOPS! Task number cannot be empty for done action!");
                }
                try {
                    Task doneTask = tasks.markTaskAsDone(Integer.parseInt(inputs[1]));
                    ui.showMarkedAsDone(doneTask);
                    storage.saveTaskListToFile(tasks);
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    throw new DukeException("OOPS! Task number is invalid!");
                }
                break;
            case DELETE:
                if (inputs.length <= 1) {
                    throw new DukeException("OOPS! Task number cannot be empty for delete action!");
                }
                try {
                    Task deleteTask = tasks.deleteTask(Integer.parseInt(inputs[1]));
                    ui.showDeletedTask(deleteTask, tasks);
                    storage.saveTaskListToFile(tasks);
                } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                    throw new DukeException("OOPS! Task number is invalid!");
                }
                break;
            case TODO:
                if (inputs.length <= 1) {
                    throw new DukeException("OOPS! The description of a todo cannot be empty!");
                }
                Task addedToDo = tasks.addTask(new ToDo(inputs[1]));
                ui.showAddedTask(addedToDo, tasks);
                storage.saveTaskListToFile(tasks);
                break;
            case DEADLINE:
                if (inputs.length <= 1) {
                    throw new DukeException("OOPS! The description of a deadline cannot be empty!");
                }
                String[] deadlineDetails = inputs[1].split(" /by ", 2);
                if (deadlineDetails.length <= 1) {
                    throw new DukeException("OOPS! The date of a deadline cannot be empty!");
                }
                Task addedDeadline = tasks.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                ui.showAddedTask(addedDeadline, tasks);
                storage.saveTaskListToFile(tasks);
                break;
            case EVENT:
                if (inputs.length <= 1) {
                    throw new DukeException("OOPS! The description of an event cannot be empty!");
                }
                String[] eventDetails = inputs[1].split(" /at ", 2);
                if (eventDetails.length <= 1) {
                    throw new DukeException("OOPS! The time of an event cannot be empty!");
                }
                Task addedEvent = tasks.addTask(new Event(eventDetails[0], eventDetails[1]));
                ui.showAddedTask(addedEvent, tasks);
                storage.saveTaskListToFile(tasks);
                break;
            default:
                throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");
        }
    }
}
