import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final String border = Ui.getBorder();
    private static List<Task> storage;

    public TaskList() {
        storage = new ArrayList<>();
    }

    public boolean checkList(String s) {
        return s.equals("list");
    }

    public  void displayList() {
        int listLen = storage.size();
        System.out.println(border.replace("\n", ""));
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= listLen; i++) {
            Task curr = storage.get(i - 1);
            System.out.println(i + "." + curr);
        }
        System.out.println(border);
    }

    public void addTask(String s, String next) throws DukeException {
        Task toAdd = null;
        if (s.matches("todo|deadline|event|done|delete") && next.equals("")) {
            throw new DukeException("OOPS!!! The description of " + s + " cannot be empty.");
        }

        SimpleDateFormat formater = new SimpleDateFormat("dd MMM yyyy h:mma");
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HHmm");

        SimpleDateFormat dateFormater = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd");

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
                    try {
                        String date;
                        if (ls[1].contains(" ")) {
                            date = formater.format((parser.parse(ls[1])));
                        } else {
                            date = dateFormater.format(dateParser.parse(ls[1]));
                        }
                        toAdd = new Deadline(ls[0], date);
                        storage.add(toAdd);
                    } catch (ParseException e) {
                        System.out.println(
                                border + "Please input the time and date in\n"
                                        + dateParser.toPattern() + " or " + parser.toPattern() + "\n" + border);
                        return;
                    }
                } else {
                    throw new DukeException("Sorry, please specify expected deadline after \"/by\".");
                }
                break;
            }
            case "event": {
                if (next.contains("/at ")) {
                    String[] ls = next.split(" /at ");
                    try {
                        String date;
                        if (ls[1].contains(" ")) {
                            date = formater.format((parser.parse(ls[1])));
                        } else {
                            date = dateFormater.format(dateParser.parse(ls[1]));
                        }
                        toAdd = new Event(ls[0], date);
                        storage.add(toAdd);
                    } catch (ParseException e) {
                        System.out.println(
                                border + "Please input the time and date in\n" +
                                        dateParser.toPattern() + " or " + parser.toPattern() + "\n" + border);
                        return;
                    }
                } else {
                    throw new DukeException("Sorry, please specify event date after \"/at\".");
                }
                break;
            }
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }

        System.out.println(
                border + "Got it. I've added this task:\n"
                        + "  " + toAdd + "\n"
                        + "Now you have " + storage.size() + " tasks in the list.\n"
                        + border
        );
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
                        border + "Nice! I've marked this task as done:\n" + "  "
                                + completed + "\n" + border
                );
            }
        } catch (NumberFormatException nfe) {
            System.out.println(
                    border + "Please state the completed task number after \"done\".\n"
                            + border
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
                System.out.println(border + e.getMessage() + "\n" + border);
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
            System.out.println(
                    border + "Noted. I've removed this task:\n" + "  "
                            + t + "\n"
                            + "Now you have " + storage.size() + " tasks in the list.\n" + border
            );
        }
    }

    public static List<Task> getList() {
        return storage;
    }
}
