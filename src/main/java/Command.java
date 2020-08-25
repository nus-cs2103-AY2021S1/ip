import java.util.ArrayList;
import java.io.IOException;

public class Command {

    private String currentInput;

    public Command() {
        this.currentInput = "";
    }

    public void receive(String s) {
        this.currentInput = s;
    }

    public boolean exit() {
        return this.currentInput.equals("bye");
    }

    public void executeTask(Parser parser, TaskList taskList, Storage storage, Ui ui)
            throws IOException, InvalidTaskArgumentException, InvalidDoneException, InvalidCommandException,
            InvalidDeleteException, DateException {
        ArrayList<String> lst = parser.processString(currentInput, taskList.getLength());
        if (lst.get(0).equals("Show")) {
            taskList.showList(ui);
            storage.save(taskList.getTasks());
        } else if (lst.get(0).equals("Done")) {
            taskList.markDone(Integer.parseInt(lst.get(1)), ui);
            storage.save(taskList.getTasks());
        } else if (lst.get(0).equals("Add")) {
            if (lst.get(1).equals("ToDo")) {
                taskList.addTask(new ToDo(lst.get(2)), ui);
            } else if (lst.get(1).equals("Deadline")) {
                taskList.addTask(new Deadline(lst.get(2), lst.get(3)), ui);
            } else {
                taskList.addTask(new Event(lst.get(2), lst.get(3)), ui);
            }
            storage.save(taskList.getTasks());
        } else {
            taskList.deleteTask(Integer.parseInt(lst.get(1)), ui);
            storage.save(taskList.getTasks());
        }
    }
}
