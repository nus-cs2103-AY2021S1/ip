import java.io.IOException;

public class AddCommand extends Command{


    public AddCommand(String command) {
        super(command, false);
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            String[] type = this.command.split(" ", 2);
            Task task;
            if (type[0].equals("todo")) {
                if(type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new Todo(type[1]);
                storage.addToFile("T | 0 | " + task.description);
            } else if (type[0].equals("deadline")) {
                if(type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String second = type[1];
                String[] secondSplit = second.split(" /by ", 2);
                task = new Deadline(secondSplit[0], secondSplit[1]);
                storage.addToFile("D | 0 | " + task.description + " | " + secondSplit[1]);
            } else if (type[0].equals("event")) {
                if(type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String second = type[1];
                String[] secondSplit = second.split(" /at ", 2);
                task = new Event(secondSplit[0], secondSplit[1]);
                storage.addToFile("E | 0 | " + task.description + " | " + secondSplit[1]);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            list.add(task);
            ui.addedTaskMessage(task, list.size());
        } catch (DukeException e) {
            ui.errorEncounter(e);
        } catch (IOException e) {
            ui.errorEncounter(e);
        }
    }
}
