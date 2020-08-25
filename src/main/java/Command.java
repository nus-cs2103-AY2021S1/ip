import java.util.ArrayList;
import java.io.IOException;

public class Command {

    private String currInput;

    public Command() {
        this.currInput = "";
    }

    public void receive(String s) {
        this.currInput = s;
    }

    public boolean exit() {
        return this.currInput.equals("bye");
    }

    public void executeTask(Parser parser, TaskList task, Storage storage, Ui ui)
            throws IOException, InvalidTaskArgumentException, InvalidDoneException, InvalidCommandException,
            InvalidDeleteException, DateException {
        ArrayList<String> lst = parser.processString(currInput, task.getLength());
        if (lst.get(0).equals("Show")) {
            task.showList(ui);
            storage.save(task.getTasks());
        } else if (lst.get(0).equals("Done")) {
            task.markDone(Integer.parseInt(lst.get(1)), ui);
            storage.save(task.getTasks());
        } else if (lst.get(0).equals("Add")) {
            if (lst.get(1).equals("ToDo")) {
                task.addTask(new ToDo(lst.get(2)), ui);
            } else if (lst.get(1).equals("Deadline")) {
                task.addTask(new Deadline(lst.get(2), lst.get(3)), ui);
            } else {
                task.addTask(new Event(lst.get(2), lst.get(3)), ui);
            }
            storage.save(task.getTasks());
        } else if (lst.get(0).equals("Find")) {
            task.findTask(lst.get(1), ui);
        } else {
            task.deleteTask(Integer.parseInt(lst.get(1)), ui);
            storage.save(task.getTasks());
        }
    }
}
