import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static String line = "    ____________________________________________________________\n";
    static ArrayList<Task> taskList = new ArrayList<>();

    private static String format(String string) {
        return line + string + "\n" + line;
    }

    private static void list() {
        StringBuilder taskListString = new StringBuilder();
        taskListString.append("     Here are the tasks in your list:\n");
        for (int i = 1; i < taskList.size() + 1; i++) {
            taskListString.append("     ").append(i).append(".").append(taskList.get(i - 1)).append("\n");
        }
        taskListString.delete(taskListString.length() - 1, taskListString.length());
        System.out.println(format(taskListString.toString()));
    }

    private static void done(String input) throws DukeException{
        if (input.equals("done") || input.equals("done ")) {
            throw new InvalidTaskIndexException(input);
        } else if (input.startsWith("done ") && input.length() > 5) {
            try {
                int index = Integer.parseInt(input.substring(5));
                Task current = taskList.get(index - 1);
                current.markAsDone();
                System.out.println(format("     Nice! I've marked this task as done:\n       " + current));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new InvalidTaskIndexException(input);
            }
        } else {
            throw new DukeException();
        }
    }

    private static void delete(String input) throws DukeException{
        if (input.equals("delete") || input.equals("delete ")) {
            throw new InvalidTaskIndexException(input);
        } else if (input.startsWith("delete ") && input.length() > 7) {
            try {
                int index = Integer.parseInt(input.substring(7));
                Task current = taskList.get(index - 1);
                taskList.remove(current);
                System.out.println(format("     Noted. I've removed this task:\n       " + current +
                        "\n     Now you have " + taskList.size() + " tasks in the list."));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new InvalidTaskIndexException(input);
            }
        } else {
            throw new DukeException();
        }
    }

    private static void addNewTask(Task newTask) {
        taskList.add(newTask);
        System.out.println(format("     Got it. I've added this task:\n       " + newTask +
                "\n     Now you have " + taskList.size() + " tasks in the list."));
    }

    private static void newTodo(String input) throws DukeException {
        if (input.equals("todo") || input.equals("todo ")) {
            throw new EmptyDescriptionException("todo");
        } else if (input.startsWith("todo ") && input.length() > 5) {
            Todo newTodo = new Todo(input.substring(5));
            addNewTask(newTodo);
        } else {
            throw new DukeException();
        }
    }

    private static void newDeadline(String input) throws DukeException {
        if (input.equals("deadline") || input.equals("deadline ")) {
            throw new EmptyDescriptionException("deadline");
        } else if (input.startsWith("deadline ") && input.length() > 9) {
            if (!input.contains("/by ")) {
                throw new InvalidDateTimeException("deadline");
            } else {
                int index = input.indexOf("/by ");
                String description = input.substring(9, index);
                String time = input.substring(index + 4);
                Deadline newDeadline = new Deadline(description, time);
                addNewTask(newDeadline);
            }
        } else {
            throw new DukeException();
        }
    }

    private static void newEvent(String input) throws DukeException {
        if (input.equals("event") || input.equals("event ")) {
            throw new EmptyDescriptionException("event");
        } else if (input.startsWith("event ") && input.length() > 6) {
            if (!input.contains("/at ")) {
                throw new InvalidDateTimeException("event");
            } else {
                int index = input.indexOf("/at ");
                String description = input.substring(6, index);
                String time = input.substring(index + 4);
                Event newEvent = new Event(description, time);
                addNewTask(newEvent);
            }
        } else {
            throw new DukeException();
        }
    }

    private static void chat() {
        String input = sc.nextLine();
        if (input.equals("bye")) {
            System.out.println(format("     Bye. Hope to see you again soon!"));
        } else if (input.equals("list")) {
            list();
        } else {
            try {
                if (input.startsWith("done")) {
                    done(input);
                } else if (input.startsWith("delete")) {
                    delete(input);
                } else if (input.startsWith("todo")) {
                    newTodo(input);
                } else if (input.startsWith("deadline")) {
                    newDeadline(input);
                } else if (input.startsWith("event")) {
                    newEvent(input);
                } else {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println(format("     " + e.toString()));
            } finally {
                chat();
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(format("     Hello! I'm Duke\n" +
                "     What can I do for you?"));
        chat();
    }
}
