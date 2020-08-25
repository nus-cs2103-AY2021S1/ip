import java.util.List;

public class DeleteCommand implements Command {
    int index; // 0-based
    List<Task> list;

    public DeleteCommand(int index, List<Task> list) throws DukeException {
        if(index < 0) {
            throw new DukeException("That is not a valid item number.");
        }
        if(index >= list.size()) {
            throw new DukeException("There are only " + list.size() +  " item(s) in the list, try entering a valid item number");
        }
        this.index = index;
        this.list = list;
    }

    @Override
    public void execute() {
        Task task = list.get(index);
        list.remove(index);
        new Duke().print("The following task has been removed successfully:", "\t" + task.toString(),
                         "Now you have " + list.size() + " items(s) left in the list.");
        new Duke().writeFile(list);
    }
}
