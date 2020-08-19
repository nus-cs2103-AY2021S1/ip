import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum TaskType {
    // toodoo, deadline, event
    T, D, E
}

class Task {
    private String message;
    private boolean isDone;
    private TaskType tasktype;

    Task() {}

    Task(TaskType tasktype, String message) {
        this.tasktype = tasktype;
        this.message = message;
        this.isDone = false;
    }

    public String getMessage() {
        return this.message;
    }

    public TaskType getTasktype() {
        return this.tasktype;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String print() {
        return "[" + this.getTasktype() + "][" + this.getStatusIcon()
                + "] " + this.getMessage();
    }

}

class UnknownCommandException extends IndexOutOfBoundsException {
    UnknownCommandException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    }
}

class EmptyBodyException extends IndexOutOfBoundsException {
    EmptyBodyException() {}
}

class TodoEmptyBodyException extends EmptyBodyException {
    TodoEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}

class EventEmptyBodyException extends EmptyBodyException {
    EventEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of an event cannot be empty.";
    }
}

class DeadlineEmptyBodyException extends EmptyBodyException {
    DeadlineEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }
}

class DeleteEmptyBodyException extends EmptyBodyException {
    DeleteEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! Empty deletion is invalid.";
    }
}

class ManipulateTime {
    public static String convertBy(String s) {
        String first = s.split("/by")[0];
        String second = s.split("/by")[1];
        return first + "(by:" + second + ")";
    }
    public static String convertAt(String s) {
        String first = s.split("/at")[0];
        String second = s.split("/at")[1];
        return first + "(at:" + second + ")";
    }
}

public class Duke {
    // add outer frame lines
    public static String format(String input_string) {
        return "    ____________________________________________________________\n" +
               "     " + input_string + "\n" +
               "    ____________________________________________________________\n";
    }

    public static void main(String[] args) throws IndexOutOfBoundsException {
        // greeting and exit messages strings
        String hello_message = "Hello! I'm Duke\n" + "     " + "What can I do for you?";
        String byebye_message = "Bye. Hope to see you again soon!";

        // set up toodoo list
        List<Task> list = new ArrayList<>();

        // set up scanner
        Scanner scanner = new Scanner(System.in);

        // start to serve
        System.out.println(format(hello_message));

        // if have more commands
        while (scanner.hasNextLine()) {
            String current_command = scanner.nextLine();
            String check_command = current_command.split(" ", 2)[0];
            // if exit command
            if (check_command.equals("bye")) {
                System.out.println(format(byebye_message));
            } else if (check_command.equals("list")) {
                // if list command
                // iteratively print through list
                int count = 1;
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the task(s) in your list:");
                for (Task task : list) {
                    System.out.println("     " + count + ". " + task.print());
                    count++;
                }
                System.out.println("    ____________________________________________________________\n");
            } else {
                try {
                    String extra_command = current_command.split(" ", 2)[1];
                    if (check_command.equals("done")) {
                        int list_index = Integer.parseInt(extra_command) - 1;
                        String done_message = "Nice! I've marked this task as done:\n";
                        Task current_task = list.get(list_index);
                        current_task.markAsDone();
                        String num_of_tasks_message = "\n     Now you have " + list.size() + " task(s) in the list.";
                        System.out.println(format(done_message + "     " + current_task.print() +
                                num_of_tasks_message));

                    } else if (check_command.equals("delete")) {
                        int list_index = Integer.parseInt(extra_command) - 1;
                        String delete_message = "Noted. I've removed this task:\n";
                        Task current_task = list.get(list_index);
                        list.remove(list_index);
                        String num_of_tasks_message = "\n     Now you have " + list.size() + " task(s) in the list.";
                        System.out.println(format(delete_message + "     " + current_task.print() +
                                        num_of_tasks_message));
                    } else {
                        // check whether it is toodoo or deadline or event
                        Task newTask = new Task();
                        switch (check_command) {
                            case "todo":
                                newTask = new Task(TaskType.T, extra_command);
                                break;
                            case "deadline":
                                // extra_command need to handle time string
                                // by
                                newTask = new Task(TaskType.D, ManipulateTime.convertBy(extra_command));
                                break;
                            case "event":
                                // extra_command need to handle time string
                                // at
                                newTask = new Task(TaskType.E, ManipulateTime.convertAt(extra_command));
                                break;
                            default:
                                // new types of exception
                                System.out.println(format("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
                                //throw new UnknownCommandException("");
                                break;
                        }
                        list.add(newTask);
                        System.out.println(format("Got it. I've added this task:\n"
                                + "       " + newTask.print() + "\n" + "     Now you have "
                                + list.size() + " task(s) in the list."));
                    }
                } catch (IndexOutOfBoundsException e) {
                    IndexOutOfBoundsException ex;
                    switch (check_command) {
                        case "todo":
                            ex = new TodoEmptyBodyException();
                            break;
                        case "event":
                            ex = new EventEmptyBodyException();
                            break;
                        case "deadline":
                            ex = new DeadlineEmptyBodyException();
                            break;
                        case "delete":
                            ex = new DeleteEmptyBodyException();
                            break;
                        default:
                            ex = new UnknownCommandException();
                            break;
                    }
                    System.out.println(format(ex.toString()));
                } catch (Exception e) {
                    System.out.println(format(e.toString()));
                }
            }
        }
        scanner.close();
    }
}
