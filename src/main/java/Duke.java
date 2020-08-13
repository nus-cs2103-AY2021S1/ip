import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void greeting() {
        String logo = "\n           ███▄ ▄███▓  ██▓   ▄████▄   ██░  ██ "
                    + "\n           ▓██▒▀█▀ ██▒ ▓██▒ ▒██▀ ▀██ ▓██░  ██▒"
                    + "\n           ▓██    ▓██░ ▒██▒ ▒▓█    ▄ ▒██▀▀▀██░"
                    + "\n           ▒██    ▒██  ░██░▒ ▓▓▄ ▄██▒░▓█  ░██ "
                    + "\n           ▒██▒   ░██▒ ░██░▒  ▓███▀ ░░▓█▒ ░██▓"
                    + "\n           ░ ▒░   ░  ░░ ▒  ░  ░▒ ▒  ░ ▒ ░ ░▒░▒"
                    + "\n           ░  ░      ░  ▒ ░   ░  ▒    ▒ ░ ▒░ ░"
                    + "\n           ░      ░     ▒ ░░          ░   ░░ ░"
                    + "\n                  ░     ░  ░ ░        ░   ░  ░";
        String greet = "\n____________________________________________________________"
                + "\n Hi Michaelia, I am your productivity tracker bot."
                + "\n Type help to see all the commands that you can use!"
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

//    public static void getListOfTasks(ArrayList<Task> tasks) {
//        if (tasks.isEmpty()) {
//            String nothing = "   ____________________________________________________________"
//                           + "\n    Can't seem to find any task hmm..."
//                           + "\n   ____________________________________________________________\n";
//            System.out.println(nothing);
//        } else {
//            String lst = "   ____________________________________________________________"
//                       + "\n    Here are the tasks in your list:";
//            for (int i = 0; i < tasks.size(); i++) {
//                int index = i + 1;
//                lst += "\n    " + index + ". " + tasks.get(i);
//            }
//            lst += "\n   ____________________________________________________________\n";
//            System.out.println(lst);
//        }
//    }

//    public static void createToDo(ArrayList<Task> tasks, String task) {
//        ToDos todo = new ToDos(task);
//        tasks.add(todo);
//        String str = "   ____________________________________________________________"
//                   + "\n    Got it. I've added this task:"
//                   + "\n      " + tasks.get(tasks.size() - 1)
//                   + "\n    Now you have " + tasks.size() + " tasks in the list."
//                   + "\n   ____________________________________________________________\n";
//        System.out.println(str);
//    }

//    public static void createDeadline(ArrayList<Task> tasks, String task, String due) {
//        Deadlines deadlines = new Deadlines(task, due);
//        tasks.add(deadlines);
//        String str = "   ____________________________________________________________"
//                   + "\n    Got it. I've added this task:"
//                   + "\n      " + tasks.get(tasks.size() - 1)
//                   + "\n    Now you have " + tasks.size() + " tasks in the list."
//                   + "\n   ____________________________________________________________\n";
//        System.out.println(str);
//    }

//    public static void createEvent(ArrayList<Task> tasks, String event, String scheduled) {
//        Events events = new Events(event, scheduled);
//        tasks.add(events);
//        String str = "   ____________________________________________________________"
//                   + "\n    Got it. I've added this task:"
//                   + "\n      " + tasks.get(tasks.size() - 1)
//                   + "\n    Now you have " + tasks.size() + " tasks in the list."
//                   + "\n   ____________________________________________________________\n";
//        System.out.println(str);
//    }

    public static void done(ArrayList<Task> tasks, Integer index) {
        tasks.get(index - 1).markAsDone();
        String str = "   ____________________________________________________________"
                    + "\n    Nice! I've marked this task as done:\n      "
                    + tasks.get(index - 1)
                    + "\n   ____________________________________________________________\n";
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
            } else if (input.startsWith("todo")){
                String task;
                try {
                    task = input.split("todo")[1];
                    ToDos.createToDo(tasks, task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ToDos.invalid_input();
                }
            } else if (input.startsWith("deadline")){
                String task;
                String due;
                try {
                    task = input.split("deadline")[1].split("/by")[0];
                    due = input.split("deadline")[1].split("/by")[1];
                    Deadlines.createDeadline(tasks, task, due);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Deadlines.invalid_input();
                }
            } else if (input.startsWith("event")) {
                try {
                    String event = input.split("event")[1].split("/at")[0];
                    String scheduled = input.split("event")[1].split("/at")[1];
                    Events.createEvent(tasks, event, scheduled);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Events.invalid_input();
                }
            } else if (input.startsWith("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                done(tasks, index);
                continue;
            } else {
                unknown();
            }
            input = sc.nextLine();
        }
    }
}