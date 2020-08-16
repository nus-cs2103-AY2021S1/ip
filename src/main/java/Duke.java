import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException{
        String logo = "        ___\n"
                    + "    . -^   `--,\n"
                    + "   /# =========`-_\n"
                    + "  /# (--====___====\\\n"
                    + " /#   .- --.  . --.|\n"
                    + "/##   |  * ) (   * ),\n"
                    + "|##   \\    /\\ \\   / |\n"
                    + "|###   ---   \\ ---  |\n"
                    + "|####      ___)    #|\n"
                    + "|######           ##|\n"
                    + " \\##### ---------- /\n"
                    + "  \\####           (\n"
                    + "   `\\###          |\n"
                    + "     \\###         |\n"
                    + "      \\##        |\n"
                    + "       \\###.    .)\n"
                    + "         `======/";

        System.out.println("A massive alien head has appeared\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    SHOW ME WHAT YOU'VE GOT");
        System.out.println("    ____________________________________________________________");
        System.out.println("");

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int itemsIndex = 0;

        while (!sc.hasNext("bye")) {
            try {
                String command = sc.next();

                System.out.println("    ____________________________________________________________");
                if (command.equals("list")) {
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < itemsIndex; i++) {
                        System.out.println("     " + (i + 1) + ". " + tasks[i]);
                    }
                } else if (command.equals("done")) {
                    //Need to handle out of bounds number.
                    int itemNumber = sc.nextInt();
                    Task selectedTask = tasks[itemNumber - 1];
                    selectedTask.setDone();
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + selectedTask);

                } else {
                    Task newTask = null;
                    if (command.equals("todo")) {
                        String nextLine = sc.nextLine().stripLeading();
                        if (nextLine.length() > 0) {
                            newTask = new ToDo(nextLine);
                        } else {
                            throw new InvalidTaskFormat("todo");
                        }
                    } else if (command.equals("deadline")) {
                        String[] splitParts = sc.nextLine().split((" /by "));
                        if (splitParts.length != 2 || splitParts[0].strip().length() == 0 ||
                                    splitParts[1].strip().length() == 0) {
                            throw new InvalidTaskFormat("deadline");
                        } else {
                            newTask = new Deadline(splitParts[0].stripLeading(), splitParts[1]);
                        }
                    } else if (command.equals("event")) {
                        String[] splitParts = sc.nextLine().split((" /at "));
                        if (splitParts.length != 2 || splitParts[0].strip().length() == 0 ||
                                    splitParts[1].strip().length() == 0) {
                            throw new InvalidTaskFormat("event");
                        } else {
                            newTask = new Event(splitParts[0].strip(), splitParts[1].strip());
                        }
                    } else {
                        sc.nextLine();
                        throw new InvalidDukeCommand();
                    }

                    if (newTask != null) {
                        System.out.println("     Got it. I've added this task:");
                        tasks[itemsIndex] = newTask;
                        itemsIndex++;
                        System.out.println("       " + newTask);
                        System.out.println("     Now you have " + itemsIndex +" tasks in the list.");
                    }

                }
            } catch (DukeException de) {
                System.out.println(de);
            } finally {
                System.out.println("    ____________________________________________________________");
                System.out.println("");
            }

        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

}
