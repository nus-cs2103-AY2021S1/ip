import java.time.DateTimeException;

public class AddCommand extends Command {
    protected final String taskType;
    protected final String description;

    public AddCommand(String taskType, String description) {
        this.taskType = taskType.trim();
        this.description = description.trim();
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        Task current;
        try {
            switch (this.taskType) {
                case "todo": {
                    current = new ToDo(this.description.trim());
                    break;
                }
                case "deadline": {
                    int byIndex = this.description.indexOf(" /by ");
                    if (byIndex == -1) {
                        throw new DukeException("I need a better description of the task");
                    }
                    String deadline = this.description.substring(byIndex + 4).trim();
                    String description = this.description.substring(0, byIndex).trim();
                    current = new Deadline(description, deadline);
                    break;
                }
                case "event": {
                    int atIndex = this.description.indexOf(" /at ");
                    if (atIndex == -1) {
                        throw new DukeException("I need a better description of the task");
                    }
                    String deadline = this.description.substring(atIndex + 4).trim();
                    String description = this.description.substring(0, atIndex).trim();
                    current = new Event(description, deadline);
                    break;
                }
                default: {
                    throw new DukeException("I don't understand you at all...");
                }
            }

            list.add(current);
            storage.appendTxt(current);
            ui.showAdd(current, list);

        } catch (NumberFormatException | DateTimeException e) {
            throw new DukeException("Invalid date format detected!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("I need a better description of the task");
        }


    }

    @Override
    public boolean isExit() {
        return false;
    }
}