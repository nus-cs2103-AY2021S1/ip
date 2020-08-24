import java.util.List;
import java.util.ArrayList;

public class StringIdentifier {
    private static boolean isProgramRunning = true;
    private static List<Task> lst = new ArrayList<>();

    public StringIdentifier() {}
    public StringIdentifier(List<Task> lst) {
        this.lst = lst;
    }

    public boolean isRunning() {
        return this.isProgramRunning;
    }

    public void checker(String str) throws DukeException {
        System.out.println("    _______________________________________________________________________");

        int commandSpace = str.indexOf(" ");

        if (str.equals("bye")) {
            close();

        } else if (str.equals("list")) {
            displayList();

        } else if (commandSpace < 0) {
            throw new DukeException("       *Invalid command.*\n     Commands: bye, list, todo, event, deadline, delete\n");

        } else if (str.substring(0, 5).equals("done ")) {
            int length = str.length();
            String index = str.substring(5, length);
            int realIndex = Integer.parseInt(index) - 1;

            if (realIndex >= this.lst.size() || realIndex < 0) {
                throw new DukeException("       *Invalid task index, please try again.*\n");
            }
            markDone(realIndex);

        } else if (str.substring(0, 5).equals("todo ")) {
            int length = str.length();
            if (length == 5) {
                throw new DukeException("       *Please fill in todo description*\n");
            }

            Todo newTodo = new Todo(str.substring(5, length));
            store(newTodo);

        } else if (commandSpace <= 4) {
            throw new DukeException("       *Invalid command.*\n     Commands: bye, list, todo, event, deadline, delete\n");

        } else if (str.substring(0, 6).equals("event ")) {
            int length = str.length();
            if (length == 6) {
                throw new DukeException("       *Please fill in event description*\n");
            }

            int end = str.indexOf("/at");
            if (end < 0) {
                throw new DukeException("       *Please fill in event completion time in the following format:*\n" +
                        "     eg. event CCA meeting /at 4th July 2020\n");
            }
            Event newEvent = new Event(str.substring(6, end),
                    str.substring(end + 3, length));
            store(newEvent);

        } else if (commandSpace <= 5) {
            throw new DukeException("       *Invalid command.*\n     Commands: bye, list, todo, event, deadline, delete\n");

        } else if (str.substring(0, 7).equals("delete ")) {
            int length = str.length();
            String index = str.substring(7, length);
            int realIndex = Integer.parseInt(index) - 1;

            if (realIndex >= this.lst.size() || realIndex < 0) {
                throw new DukeException("       *Invalid task index, please try again.*\n");
            }
            delete(realIndex);

        } else if (commandSpace <= 6) {
            throw new DukeException("       *Invalid command.*\n     Commands: bye, list, todo, event, deadline, delete\n");

        } else if (str.substring(0, 9).equals("deadline ")) {
            int length = str.length();
            if (length == 9) {
                throw new DukeException("       *Please fill in deadline description*\n");
            }

            int end = str.indexOf("/by");
            if (end < 0) {
                throw new DukeException("       *Please fill in deadline completion time in the following format:*\n" +
                                        "     eg. deadline return book to Jurong Regional Library /by 6th June 2020\n");
            }

            Deadline newDeadline = new Deadline(str.substring(9, end),
                    str.substring(end + 3, length));
            store(newDeadline);

        } else {
            throw new DukeException("       *Invalid command.*\n     Commands: bye, list, todo, event, deadline, delete\n");
        }

        System.out.println("    _______________________________________________________________________\n");
    }

    public void store(Task task) {
        this.lst.add(task);
        System.out.println("     Got it. I've added this task:\n"
                         + "       " + task);
        System.out.println("     Now you have " + this.lst.size() + " task(s) in the list.");
    }

    public void displayList() {
        int size = this.lst.size();
        System.out.println("     Here are the tasks in your list:");

        int index = 1;
        for (int i = 0; i < size; i++) {
            System.out.println("     " + index + "." + this.lst.get(i));
            index ++;
        }
        index ++;
    }

    public void markDone(int index) {
        Task taskSubject = this.lst.get(index);
        taskSubject.markAsDone();
        System.out.println("     Nice! I've marked this task as done:\n"
                         + "       " + taskSubject);
    }

    public void delete(int index) {
        Task taskSubject = this.lst.get(index);
        this.lst.remove(index);
        System.out.println("     Noted. I've removed this task:\n"
                + "       " + taskSubject
                + "\n     Now you have " + this.lst.size() + " task(s) in the list.");

    }

    public void close() {
        this.isProgramRunning = false;
        System.out.println("     Bye. Hope to see you again soon!");
    }
}