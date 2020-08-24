import java.util.List;

public class AddCommand extends Command{
    private final Commands c;
    private final String description;

    AddCommand(Commands c, String description) {
        this.c = c;
        this.description = description;
    }
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        switch (c) {
            case TODO:
                tasklist.add(new Todo(description));
                ui.showMessage("Hi there, I've added this task:\n"
                        + tasklist.get(tasklist.getSize() - 1) + "\nYou have "
                        + tasklist.getSize() + " tasks in the list.");
                break;
            case DEADLINE:
                String[] descriptions = description.split("/by ");
                tasklist.add(new Deadline(descriptions[0], descriptions[1]));
                System.out.println("Hi there, I've added this task:\n"
                        + tasklist.get(tasklist.getSize() - 1) + "\nYou have "
                        + tasklist.getSize() + " tasks in the list.");
                break;
            case EVENT:
                String[] descriptions2 = description.split("/at ");
                tasklist.add(new Event(descriptions2[0], descriptions2[1]));
                System.out.println("Hi there, I've added this task:\n"
                        + tasklist.get(tasklist.getSize() - 1) + "\nYou have "
                        + tasklist.getSize() + " tasks in the list.");
                break;
        }
    }
}
