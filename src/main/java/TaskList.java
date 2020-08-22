import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final List<Task> storage = new ArrayList<>();
    private final Ui ui;

    public TaskList() {
        this.ui = new Ui();
    }

    public boolean checkList(String s) {
        return s.equals("list");
    }

    public  void displayList() {
        int listLen = storage.size();
        System.out.println(ui.getBorder().replace("\n", ""));
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i <= listLen; i++) {
            Task curr = storage.get(i - 1);
            System.out.println(i + "." + curr);
        }
        System.out.println(ui.getBorder());
    }

    public void addTask(String s, String next) throws DukeException {
        Task toAdd = null;
        if (s.matches("todo|deadline|event|done|delete") && next.equals("")) {
            throw new DukeException("OOPS!!! The description of " + s + " cannot be empty.");
        }

        switch (s) {
            case "todo": {
                ToDo todo = new ToDo(next);
                storage.add(todo);
                toAdd = todo;
                break;
            }
            case "deadline": {
                if (next.contains("/by ")) {
                    String[] ls = next.split(" /by ");
                    toAdd = new Deadline(ls[0], Parser.parseDateTime(ls[1]));
                    storage.add(toAdd);
                } else {
                    throw new DukeException("Sorry, please specify expected deadline after \"/by\".");
                }
                break;
            }
            case "event": {
                if (next.contains("/at ")) {
                    String[] ls = next.split(" /at ");
                    toAdd = new Event(ls[0], Parser.parseDateTime(ls[1]));
                    storage.add(toAdd);
                } else {
                    throw new DukeException("Sorry, please specify event date after \"/at\".");
                }
                break;
            }
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }

        ui.addTaskLine(toAdd, storage.size());
    }

    public boolean checkDone(String s) {
        return s.equals("done");
    }

    public void doneTask(String s) throws DukeException {
        try {
            int i = Integer.parseInt(s);
            if (i < 1 || i > storage.size()) {
                throw new DukeException("You have entered an invalid number: " + i
                        + ". Please try again.");
            } else {
                Task t = storage.get(i - 1);
                Task completed = t.setDone(true);
                storage.set(i - 1, completed);
                System.out.println(
                        ui.getBorder() + "Nice! I've marked this task as done:\n" + "  "
                                + completed + "\n" + ui.getBorder()
                );
            }
        } catch (NumberFormatException nfe) {
            System.out.println(
                    ui.getBorder() + "Please state the completed task number after \"done\".\n"
                            + ui.getBorder()
            );
        }
    }

    public boolean checkDel(String s) {
        return s.equals("delete");
    }

    public void delTask(String s) throws DukeException {
        if (s.equals("")) {
            try {
                addTask("delete", "");
            } catch (DukeException e) {
                System.out.println(ui.getBorder() + e.getMessage() + "\n" + ui.getBorder());
            }
            return;
        }

        int i = Integer.parseInt(s);
        if (i < 1 || i > storage.size()) {
            throw new DukeException("You have entered an invalid number: " + i
                    + ". Please try again.");
        } else {
            Task t = storage.get(i - 1);
            storage.remove(i - 1);
            ui.removeTaskLine(t, storage.size());
        }
    }

    public static List<Task> getList() {
        return storage;
    }
}
