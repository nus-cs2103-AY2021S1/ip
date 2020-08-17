import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> list = new ArrayList<>();
    private static final String doneErrorMessage = "OOPS!!! Please choose a valid task index to mark as done.\n";
    private static final String deleteErrorMessage = "OOPS!!! Please choose a valid task index to delete.\n";

    protected void addTask(Task task) {
        list.add(task);
        System.out.println("added: " + task + "\n");
    }

    protected void markTaskDone(String command) throws DukeException {
        try {
            int listIndex = Integer.parseInt(command.substring(5));
            Task task = list.get(listIndex - 1);
            task.markDone();
            System.out.printf("Hurray! %s is now done.\n", task.getTask());
            System.out.println(task + "\n");
        } catch (Exception error) {
            throw new DukeException(doneErrorMessage);
        }
    }

    protected void deleteTask(String command) throws DukeException {
        try {
            int listIndex = Integer.parseInt(command.substring(7));
            Task task = list.get(listIndex - 1);
            list.remove(listIndex - 1);
            System.out.printf("Okay %s has been deleted.\n", task.getTask());
            System.out.println(task);
            System.out.println("You now have " + list.size() + " tasks.\n");
        } catch (Exception error) {
            throw new DukeException(deleteErrorMessage);
        }
    }

    @Override
    public String toString() {
        String message = "";
        int count = 1;
        for (Task task : list) {
            message += String.format("%d.%s\n"
                    , count++, task);
        }
        return message;
    }
}
