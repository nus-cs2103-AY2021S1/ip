public class AddCommand extends Command {
    String fullCommand;
    EnumUserInstruction.userInstruction instructionCommand;

    public AddCommand (String fullCommand, EnumUserInstruction.userInstruction instructionCommand) {
        this.fullCommand = fullCommand;
        this.instructionCommand = instructionCommand;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] userInputArr = this.fullCommand.split(" ");
        switch (this.instructionCommand) {
            case TODO:
                try {
                    if (userInputArr[1].isBlank()) {
                        throw new DukeException("The description of a todo cannot be empty");
                    }
                    Task todoTask = new Todo(fullCommand.substring(5));
                    taskList.addItem(todoTask);
                    ui.addMessage(todoTask, taskList.getTasksLeft());

                    // change data file
                    storage.addTask(todoTask);

                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("The description of a todo cannot be empty");
                }
                break;
            case DEADLINE:
                try {
                    // Splitting string
                    String substring = fullCommand.substring(9);
                    String[] strArr = substring.split("/by");
                    String description = strArr[0];
                    String date = strArr[1];
                    Task deadlineTask = new Deadline(description, date);
                    taskList.addItem(deadlineTask);
                    ui.addMessage(deadlineTask, taskList.getTasksLeft());

                    // change data file
                    storage.addTask(deadlineTask);

                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please give me details for your deadline task");
                } catch (ArrayIndexOutOfBoundsException a) {
                    throw new DukeException("Please give date / time after '/by' for this deadline task");
                } catch (DukeException e) {
                    throw new DukeException(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    // Splitting string
                    String substring = fullCommand.substring(6);
                    String[] strArr = substring.split("/at");
                    String description = strArr[0];
                    String date = strArr[1];
                    Task eventTask = new Event(description, date);
                    taskList.addItem(eventTask);
                    ui.addMessage(eventTask, taskList.getTasksLeft());

                    // change data file
                    storage.addTask(eventTask);

                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please give me information about your event!");
                } catch (ArrayIndexOutOfBoundsException a) {
                    throw new DukeException("Please give date / time after '/at' for this event");
                } catch (DukeException e) {
                    throw new DukeException(e.getMessage());
                }
                break;
            default:

        }
    }
}
