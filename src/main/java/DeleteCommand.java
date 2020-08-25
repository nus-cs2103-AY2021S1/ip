import java.io.IOException;

public class DeleteCommand extends Command {
    String userInput;

    DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        if (!userInput.substring(6).isBlank()) {
            try {
                String toDelete = userInput.substring(7);
                int index = Integer.parseInt(toDelete);
                if (index <= taskList.size() && index > 0) {
                    System.out.println(Ui.line);
                    System.out.println(Ui.bot);
                    System.out.println("Noted! I've deleted this task:");
                    System.out.println(taskList.getTasks().get(index - 1));
                    taskList.getTasks().remove(index - 1);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    storage.writeToFile(taskList.getTasks());
                } else {
                    throw new IndexOutOfBoundsException();
                }
            } catch (NumberFormatException | IndexOutOfBoundsException | IOException ex){
                throw new DukeException("The number keyed in is invalid!");
            }
        } else {
            throw new DukeException("The description of a delete cannot be empty!");
        }
    }
}
