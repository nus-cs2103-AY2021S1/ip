import java.util.ArrayList;
import java.util.List;
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
                    + "      \\##         |\n"
                    + "       \\###.     .)\n"
                    + "         `======/";

        System.out.println("A massive alien head has appeared\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    SHOW ME WHAT YOU'VE GOT");
        System.out.println("    ____________________________________________________________");
        System.out.println("");

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();


        while (!sc.hasNext("bye")) {
            try {
                String command = sc.next();

                System.out.println("    ____________________________________________________________");
                if (command.equals("list")) {
                    System.out.println("     Here are the tasks in your list:");
                    int taskCount = 1;
                    for (Task task : tasks) {
                        System.out.println("     " + (taskCount) + ". " + task);
                        taskCount++;
                    }
                    if (tasks.size() == 0) {
                        System.out.println("     This is a very empty list... UwU");
                    }
                } else if (command.equals("done")) {
                    //Need to handle out of bounds number.
                    int itemNumber = sc.nextInt();
                    Task selectedTask = tasks.get(itemNumber - 1);
                    selectedTask.setDone();
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + selectedTask);

                } else if (command.equals("delete")) {
                    int itemNumber = sc.nextInt();
                    Task selectedTask = tasks.remove(itemNumber - 1);
                    System.out.println("     Noted. I've removed this task:");
                    System.out.println("       " + selectedTask);
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    Task newTask = null;
                    if (command.equals("todo")) {
                        String nextLine = sc.nextLine().stripLeading();
                        if (nextLine.length() > 0) {
                            newTask = new ToDo(nextLine);
                        } else {
                            throw new InvalidTaskFormat(TaskType.TODO);
                        }
                    } else if (command.equals("deadline")) {
                        String[] splitParts = sc.nextLine().split((" /by "));
                        if (splitParts.length != 2 || splitParts[0].strip().length() == 0 ||
                                    splitParts[1].strip().length() == 0) {
                            throw new InvalidTaskFormat(TaskType.DEADLINE);
                        } else {
                            newTask = new Deadline(splitParts[0].stripLeading(), splitParts[1]);
                        }
                    } else if (command.equals("event")) {
                        String[] splitParts = sc.nextLine().split((" /at "));
                        if (splitParts.length != 2 || splitParts[0].strip().length() == 0 ||
                                    splitParts[1].strip().length() == 0) {
                            throw new InvalidTaskFormat(TaskType.EVENT);
                        } else {
                            newTask = new Event(splitParts[0].strip(), splitParts[1].strip());
                        }
                    } else {
                        sc.nextLine();
                        throw new InvalidDukeCommand();
                    }

                    if (newTask != null) {
                        System.out.println("     Got it. I've added this task:");
                        tasks.add(newTask);
                        System.out.println("       " + newTask);
                        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
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
