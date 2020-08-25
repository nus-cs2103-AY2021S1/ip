import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Parser {
    private static boolean isProgramRunning = true;
    private static TaskList lst;

    public Parser(TaskList lst) {
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
            throw new DukeException("*Invalid command.*\n     Commands: bye, list, todo, event, deadline, delete");

        } else if (str.substring(0, 5).equals("done ")) {
            int length = str.length();
            String index = str.substring(5, length);
            int realIndex = Integer.parseInt(index) - 1;

            if (realIndex >= this.lst.getLength() || realIndex < 0) {
                throw new DukeException("*Invalid task index, please try again.*");
            }
            markDone(realIndex);

        } else if (str.substring(0, 5).equals("todo ")) {
            int length = str.length();
            if (length == 5) {
                throw new DukeException("*Please fill in todo description*");
            }

            Todo newTodo = new Todo(str.substring(5, length));
            store(newTodo);

        } else if (commandSpace <= 4) {
            throw new DukeException("*Invalid command.*\n     Commands: bye, list, todo, event, deadline, delete");

        } else if (str.substring(0, 6).equals("event ")) {
            int length = str.length();
            if (length == 6) {
                throw new DukeException("*Please fill in event description*");
            }

            int end = str.indexOf("/at");
            if (end < 0) {
                throw new DukeException("*Please fill in event completion time in the following format:*\n" +
                        "     eg. event CCA meeting /at YYYY-MM-DD");
            }

            LocalDate date;
            String dateString = str.substring(end + 4, length);
            try{
                date = LocalDate.parse(dateString);
            } catch (DateTimeParseException e) {
                throw new DukeException("*Please fill in the time in the YYYY-MM-DD format*");
            }

            Event newEvent = new Event(str.substring(6, end), date);
            store(newEvent);

        } else if (commandSpace <= 5) {
            throw new DukeException("*Invalid command.*\n     Commands: bye, list, todo, event, deadline, delete");

        } else if (str.substring(0, 7).equals("delete ")) {
            int length = str.length();
            String index = str.substring(7, length);
            int realIndex = Integer.parseInt(index) - 1;

            if (realIndex >= this.lst.getLength() || realIndex < 0) {
                throw new DukeException("       *Invalid task index, please try again.*");
            }
            delete(realIndex);

        } else if (commandSpace <= 6) {
            throw new DukeException("       *Invalid command.*\n     Commands: bye, list, todo, event, deadline, delete");

        } else if (str.substring(0, 9).equals("deadline ")) {
            int length = str.length();
            if (length == 9) {
                throw new DukeException("       *Please fill in deadline description*");
            }

            int end = str.indexOf("/by");
            if (end < 0) {
                throw new DukeException("*Please fill in deadline completion time in the following format:*\n" +
                                        "     eg. deadline return book to Jurong Regional Library /by YYYY-MM-DD");
            }

            LocalDate date;
            String dateString = str.substring(end + 4, length);
            try{
                date = LocalDate.parse(dateString);
            } catch (DateTimeParseException e) {
                throw new DukeException("*Please fill in the time in the YYYY-MM-DD format*");
            }

            Deadline newDeadline = new Deadline(str.substring(9, end), date);
            store(newDeadline);

        } else {
            throw new DukeException("*Invalid command.*\n     Commands: bye, list, todo, event, deadline, delete");
        }

        System.out.println("    _______________________________________________________________________\n");
    }

    public void store(Task task) {
        this.lst.store(task);
        System.out.println("     Got it. I've added this task:\n"
                         + "       " + task);
        System.out.println("     Now you have " + this.lst.getLength() + " task(s) in the list.");
    }

    public void displayList() {
        List<Task> currLst = this.lst.getTasks();
        int size = currLst.size();
        System.out.println("     Here are the task(s) in your list:");

        int index = 1;
        for (int i = 0; i < size; i++) {
            System.out.println("     " + index + "." + currLst.get(i));
            index ++;
        }
        index ++;
    }

    public void markDone(int index) {
        Task taskSubject = this.lst.markDone(index);
        System.out.println("     Nice! I've marked this task as done:\n"
                         + "       " + taskSubject);
    }

    public void delete(int index) {
        Task taskSubject = this.lst.remove(index);
        System.out.println("     Noted. I've removed this task:\n"
                + "       " + taskSubject
                + "\n     Now you have " + this.lst.getLength() + " task(s) in the list.");

    }

    public void close() {
        this.isProgramRunning = false;
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public List<Task> getTasks() {
        return lst.getTasks();
    }
}