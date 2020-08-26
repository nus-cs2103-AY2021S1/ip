package duke;

import java.io.IOException;

public class DoneCommand extends Command {
    String userInput;

    DoneCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException {
        int len = userInput.length();
        int pos = Integer.parseInt(userInput.substring(5, len));
        if (pos <= taskList.size() && pos > 0) {
            taskList.getTasks().get(pos - 1).markAsDone(); //marking task as done
            System.out.println(Ui.getLine());
            System.out.println(Ui.getBot());
            System.out.println("Great work! I've marked this task as done:");
            //System.out.println("[" + tasks.get(pos - 1).getStatusIcon() + "]" + " " + tasks.get(pos - 1).getTask());
            System.out.println(taskList.getTasks().get(pos - 1));
            System.out.println("Keep the ticks going! ^_^");
            storage.writeToFile(taskList.getTasks());
        } else {
            throw new DukeException("You have keyed in an invalid number!");
        }
    }
}
