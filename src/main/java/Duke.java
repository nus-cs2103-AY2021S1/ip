import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    // this field is used when Duke requires a horizontal line
    public static final String LINE = "    ____________________________________________________________";

    // this function greets the user when Duke is started
    public static void greeting(){
        System.out.println(LINE);
        System.out.println("     Hello! I'm Duke and I was designed by Xuan Ming!\n");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
    }

    // this function says bye to the user when Duke receives the input "bye"
    public static void goodbye() {
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    // calling this function will cause Duke to echo what the user inputs to Duke
    // this function is only used in level 1 of the iP
    public static void echo(String s) {
        System.out.println(LINE);
        System.out.println("     " + s);
        System.out.println(LINE);
    }

    // this function takes in the input from the user and adds it to the list of tasks Duke is tracking
    // helper function
    public static void addTask(Task t, ArrayList<Task> tasks) {
        tasks.add(t);
        System.out.println(LINE);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("     " + t.toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    // this function lists the list of tasks Duke is tracking
    public static void list(ArrayList<Task> tasks) {
        int counter = 1;
        System.out.println(LINE);
        System.out.println("     Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println("     " + counter + "." + task.toString());
            counter++;
        }
        System.out.println(LINE);
    }

    // this function prints the task that is completed
    public static void printDone(ArrayList<Task> tasks, int taskNumber) {
        Task t = tasks.get(taskNumber);
        t.markAsDone();
        System.out.println(LINE);
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + t.toString());
        System.out.println(LINE);
    }

    // this function deletes the task
    public static void deleteTask(ArrayList<Task> tasks, int taskNumber) {
        Task t = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println(LINE);
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + t.toString());
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    // this function prompts the user for an input
    public static void userPrompt(ArrayList<Task> tasks) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            // if the user inputs a "bye" command, we simply break out of the Duke program
            if (sc.hasNext("bye")) {
                goodbye();
                break;
            }

            // if the user inputs a "list" command, Duke will iterate through the added text and print them in the order
            // they were given to Duke.
            if (sc.hasNext("list")) {
                list(tasks);
                sc.nextLine();
                continue;
            }

            // if the user inputs a "done" command followed by a valid number, Duke will change the valid numbered
            // task to done
            if (sc.hasNext("done")) {
                try {
                    sc.skip("done");
                    String input = sc.nextLine().trim();
                    if (input.length() == 0) {
                        throw new MissingNumberFromCommandException();
                    }
                    int taskNumber = Integer.parseInt(input);
                    if (taskNumber <= 0 || taskNumber > tasks.size()) {
                        throw new InvalidNumberFromDoneCommandException();
                    }
                    printDone(tasks, taskNumber - 1);
                    continue;
                }

                catch (MissingNumberFromCommandException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! Please type in the \"done\" command followed by a valid task number.");
                    System.out.println(LINE);
                    continue;
                }
                catch (NumberFormatException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! The \"done\" command must be followed by a valid task number.");
                    System.out.println(LINE);
                    continue;
                }

                catch (InvalidNumberFromDoneCommandException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! The \"done\" command must be followed by a valid task number.");
                    System.out.println(LINE);
                    continue;
                }
            }

            if (sc.hasNext("event")) {
                try {
                    sc.skip("event");
                    String input = sc.nextLine().trim();
                    if (input.length() == 0) {
                        throw new MissingDescriptionException();
                    } else if (!input.contains("/at")) {
                        throw new MissingInfoException();
                    } else {
                        String[] split = input.split("/at");
                        if (split.length != 2) {
                            throw new MissingInfoException();
                        }
                        Event e = new Event(split[0], split[1]);
                        addTask(e, tasks);
                        sc.reset();
                        continue;
                    }

                }

                catch (MissingDescriptionException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! The description of a event cannot be empty.");
                    System.out.println(LINE);
                    continue;
                }

                catch (MissingInfoException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! The event you keyed in needs to have a timing!");
                    System.out.println("     You can key in a timing by typing \"/at\", followed by the event timing!");
                    System.out.println(LINE);
                    continue;
                }

            }

            if (sc.hasNext("deadline")) {
                try {
                    sc.skip("deadline");
                    String input = sc.nextLine().trim();
                    if (input.length() == 0) {
                        throw new MissingDescriptionException();
                    } else if (!input.contains("/by")) {
                        throw new MissingInfoException();
                    } else {
                        String[] split = input.split("/by");
                        if (split.length != 2) {
                            throw new MissingInfoException();
                        }
                        Deadline e = new Deadline(split[0], split[1]);
                        addTask(e, tasks);
                        sc.reset();
                        continue;
                    }
                }

                catch (MissingDescriptionException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(LINE);
                    continue;
                }

                catch (MissingInfoException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! The deadline you keyed in needs to have a deadline!");
                    System.out.println("     You can key in a timing by typing \"/by\", followed by the deadline's " +
                            "deadline!");
                    System.out.println(LINE);
                    continue;
                }

            }

            if (sc.hasNext("todo")) {
                try {
                    sc.skip("todo");
                    String task = sc.nextLine().trim();
                    if (task.length() == 0) {
                        throw new MissingDescriptionException();
                    }
                    ToDo t = new ToDo(task);
                    addTask(t, tasks);
                    continue;
                }

                catch (MissingDescriptionException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(LINE);
                    continue;
                }
            }

            if (sc.hasNext("delete")) {
                try {
                    sc.skip("delete");
                    String input = sc.nextLine().trim();
                    if (input.length() == 0) {
                        throw new MissingNumberFromCommandException();
                    }
                    int taskNumber = Integer.parseInt(input);
                    if (taskNumber <= 0 || taskNumber > tasks.size()) {
                        throw new InvalidNumberFromDoneCommandException();
                    }
                    deleteTask(tasks, taskNumber - 1);
                    continue;
                }

                catch (MissingNumberFromCommandException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! Please type in the delete command followed by a task number.");
                    System.out.println(LINE);
                }
                catch (InvalidNumberFromDoneCommandException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! The delete command must be followed by a valid task number.");
                    System.out.println(LINE);
                }
                catch (NumberFormatException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! The delete command must be followed by a valid task number.");
                    System.out.println(LINE);
                }
            }

            else {
                try {
                    String command = sc.nextLine();
                    throw new UnknownCommandException(command);
                }

                catch (UnknownCommandException e) {
                    System.out.println(LINE);
                    System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what \"" + e.command + "\" means :-(");
                    System.out.println(LINE);
                }
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
        greeting();

        // this field keeps track of the tasks given to Duke
        ArrayList<Task> tasks = new ArrayList<>();

        userPrompt(tasks);

    }
}
