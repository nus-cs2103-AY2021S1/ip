import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {
    static final String GREET = "greet";
    static final String EXIT = "bye";
    static final String LIST = "list";
    static final String DONE = "done";
    static final String TODO = "todo";
    static final String EVENT = "event";
    static final String DEADLINE = "deadline";
    static final String DELETE = "delete";

    private static String formatDate(String date) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate localDate = LocalDate.parse(date);
        return localDate.format(pattern);
    }

    protected static Task assignTask(String[] command) throws DukeException {
        Task t;
        String type = command[0];

        if (command.length > 1) {
            String description = command[1];
            if (type.equals(DONE)) {
                int taskNum = Integer.parseInt(description) - 1;
                t = new Done(taskNum);
            } else if (type.equals(TODO)) {
                t = new ToDo(description);
            } else if (type.equals(EVENT)) {
                String[] s = description.split(" /at ");
                String desc = s[0];
                String at = s[1];
                t = new Event(desc, at);
            } else if (type.equals(DEADLINE)) {
                String[] s = description.split(" /by ");
                String desc = s[0];
                String by = formatDate(s[1]);
                t = new Deadline(desc, by);
            } else if (type.equals(DELETE)) {
                int taskNum = Integer.parseInt(description) - 1;
                t = new Delete(taskNum);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(\n");
            }
        } else {
            if (type.equals(LIST)) {
                t = new List();
            } else if (type.equals(EXIT)) {
                t = new Exit();
            } else {
                if (type.equals(DONE) || type.equals(TODO) || type.equals(EVENT) || type.equals(DEADLINE)) {
                    String message = String.format("OOPS!!! The description of %s cannot be empty.\n", type);
                    throw new DukeException(message);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(\n");
                }
            }
        }

        return t;
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        Echo echo = new LoadFile().loadFile();
        echo.addTask(new Greet());
        String res = echo.replyUser();
        System.out.println(res);

        while (sc.hasNextLine()) {
            String[] command = sc.nextLine().split(" ", 2);

            try {
                Task t = assignTask(command);
                echo.addTask(t);
                String response = echo.replyUser();
                System.out.println(response);
                if (echo.toExit()) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        sc.close();
    }
}
