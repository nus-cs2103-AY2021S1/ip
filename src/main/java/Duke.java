import java.util.*;

public class Duke {

    // attributes for task storage
    public static ArrayList<Task> lib = new ArrayList<>();
    public static int curr = 0;
    public static boolean takeInput = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println( "---------------\n" + "Hello! I'm\n" + logo + "\n" +
                "---------------\n" + "What can I do for you?\n" + "---------------");

        while (takeInput) {
            String echo = sc.nextLine();
            String[] modEcho = echo.split(" ", 2);

            try {
                if (modEcho.length == 1) {
                    CheckOneWord(echo);
                } else {
                    CheckCommand(modEcho);
                }
            } catch (DukeException ex) {
                System.out.println("---------------\n" +
                        ex.getMessage() + "\n" + "---------------");
            }

        }

        sc.close();
    }

    public static boolean isNumeric(String test) {
        try {
            Integer.parseInt(test);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static void CheckOneWord(String echo) throws DukeException {
        if (echo.equals("bye")) {
            System.out.println("---------------\n" + "Bye. Hope to see you again soon!\n"
                    + "---------------");
            takeInput = false;
        } else if (echo.equals("list")) {
            System.out.println("---------------\n" + "Here are the task(s) in your list:");

            for (int i = 1; i <= lib.size(); i++) {
                System.out.println(i + "." + lib.get(i - 1).toString());
            }
            System.out.println("---------------");
        } else if (echo.equals("todo") || echo.equals("deadline") || echo.equals("event")) {
            throw new DukeException("Please enter a valid description for your task!");
        } else if (echo.equals("done")) {
            throw new DukeException("Please enter the ID of the task you would like to complete.");
        } else if (echo.equals("delete")) {
            throw new DukeException("Please retry and enter the ID of the task to be deleted.");
        } else {
            throw new DukeException("Please enter a valid command into the console.");
        }
    }

    public static void CheckCommand(String[] modEcho) throws DukeException {
        String task = modEcho[0];

        if (task.equals("done")) {
            if (!isNumeric(modEcho[1])) {
                throw new DukeException("Please enter a valid task number to complete!");
            } else {
                int index = Integer.parseInt(modEcho[1]) - 1;

                if (index >= curr || curr == 0 || index < 0) {
                    throw new DukeException("This task ID does not exist in the database!");
                } else {
                    System.out.println("---------------\n" + "Nice! I've marked this task as done:");

                    lib.get(index).finishTask();
                    System.out.println(lib.get(index).toString() + "\n" + "---------------\n");
                }
            }

        } else if (task.equals("delete")) {
            if (!isNumeric(modEcho[1])) {
                throw new DukeException("Please enter a valid task number for deletion!");
            } else {
                int index = Integer.parseInt(modEcho[1]) - 1;

                if (index >= curr || curr == 0 || index < 0) {
                    throw new DukeException("This task ID does not exist in the database!");
                } else {
                    System.out.println("---------------\n" + "The following task has been deleted:\n" +
                            lib.get(index).toString());

                    lib.remove(index);
                    curr--;
                    System.out.println("Now you have " + curr + " task(s) in the list.\n" +
                            "---------------");
                }
            }
        } else {

            if (task.equals("todo")) {
                ToDo todo = new ToDo(modEcho[1]);
                lib.add(todo);
                System.out.println("---------------\n" +
                        "Got it. I've added this task:");
                System.out.println(todo.toString());
                curr++;
                System.out.println("Now you have " + curr + " task(s) in the list.\n" +
                        "---------------");

            } else if (task.equals("deadline") || task.equals("event")) {
                CheckValidTime(modEcho);
            } else {
                throw new DukeException("Please enter a valid task name to add into the list!");
            }

        }
    }

    public static void CheckValidTime(String[] modEcho) throws DukeException {
        String task = modEcho[0];
        String[] processTime = modEcho[1].split("/");

        if (processTime.length == 1) {
            throw new DukeException("You need to include '/by' or '/at' for this task to describe the time.");
        } else {
            String[] time = processTime[1].split(" ", 2);

            if (time.length == 1) {
                throw new DukeException("The time description cannot be left blank!");
            } else {

                System.out.println("---------------\n" +
                        "Got it. I've added this task:");

                if (task.equals("deadline")) {
                    Deadline deadline = new Deadline(processTime[0].trim(),
                            time[1].trim());
                    lib.add(deadline);
                    System.out.println(deadline.toString());
                } else {
                    Event event = new Event(processTime[0].trim(),
                            time[1].trim());
                    lib.add(event);
                    System.out.println(event.toString());
                }
                curr++;
                System.out.println("Now you have " + curr + " task(s) in the list.\n" +
                        "---------------");
            }
        }
    }

}
