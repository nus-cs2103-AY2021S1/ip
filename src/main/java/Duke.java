import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greet = "\n____________________________________________________________"
                + "\n Hello! I'm Duke"
                + "\n What can I do for you?"
                + "\n____________________________________________________________\n";
        System.out.println(logo + greet);
    }

    public static void commands() {
        String commands = "   ____________________________________________________________"
                + "\n    Here are all your commands:"
                + "\n     list - show all tasks"
                + "\n     todo <your task> - add task"
                + "\n     deadline <your task> /by <your deadline> - add task with deadline"
                + "\n     event <your event> /at <event's timing> - add event"
                + "\n     done <index of task> - mark task as done"
                + "\n   ____________________________________________________________\n";
        System.out.println(commands);
    }

    public static void bye() {
        String bye = "   ____________________________________________________________"
                + "\n    Bye! Hope to see you again soon."
                + "\n   ____________________________________________________________\n";
        System.out.println(bye);
    }

    public static void invalid_index() {
        String str = "   ____________________________________________________________"
                + "\n    This index does not exist. Please try again!"
                + "\n   ____________________________________________________________";
        System.out.println(str);

    }

    public static void unknown() {
        String str = "   ____________________________________________________________"
                + "\n    Sorry? I didn't get what you want me to do."
                + "\n    Type help to see all available commands."
                + "\n   ____________________________________________________________\n";
        System.out.println(str);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        greeting();
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("help")) {
                commands();
            } else if (input.equals("list")) {
                Task.getListOfTasks(tasks);
            } else if (input.startsWith("todo")) {
                String task;
                try {
                    task = input.split("todo")[1];
                    ToDos.createTask(tasks, task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ToDos.invalid_input();
                }
            } else if (input.startsWith("deadline")) {
                String task;
                String due;
                try {
                    task = input.split("deadline")[1].split("/by")[0];
                    due = input.split("deadline")[1].split("/by")[1];
                    Deadlines.createTask(tasks, task, due);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Deadlines.invalid_input();
                }
            } else if (input.startsWith("event")) {
                try {
                    String event = input.split("event")[1].split("/at")[0];
                    String scheduled = input.split("event")[1].split("/at")[1];
                    Events.createTask(tasks, event, scheduled);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Events.invalid_input();
                }
            } else if (input.startsWith("done")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    Task.done(tasks, index);
                } catch (IndexOutOfBoundsException e) {
                    invalid_index();
                }
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("delete")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    Task.delete(tasks, index);
                } catch (IndexOutOfBoundsException e) {
                    invalid_index();
                }
                input = sc.nextLine();
                continue;
            } else {
                unknown();
                input = sc.nextLine();
                continue;
            }
            input = sc.nextLine();
        }
    }
}
