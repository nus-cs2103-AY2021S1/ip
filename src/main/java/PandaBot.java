import java.util.Scanner;

public class PandaBot {
    private static Task[] tasks = new Task[100];
    private static int counter = 0;


    public static void main(String[] args) {
        String logo =
                 " ____                    _\n"
               + "|  _ \\                  | |\n"
               + "| |_| |___  _ _  __  ___| | ___  _\n"
               + "| ___/  _ \\| | |/  |/ _   |/ _ \\| |\n"
               + "| |  | |_|   |  _  | |_|  | |_|   |\n"
               + "|_|  \\____,__|_| |_|\\___,_|\\___,__|" + " bot\n\n";

        System.out.println(logo + "Hello! I'm PandaBot.\n" + "What can I do for you?\n");

        // echoes command by user
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                // exit the app when the user types bye
                System.out.println("Bye! Remember to finish the rest of your work! See you soon~");
                break;
            } else if (command.equals("list")) {
                // list command
                System.out.println("These are the tasks you have: ");
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    } else {
                        break;
                    }
                }
            } else if (command.startsWith("done")) {
                // done command
                // search for the task done
                String[] cmd = command.split("(?<=done) ", 2);
                int taskNum = -1;
                try {
                    taskNum = Integer.parseInt(cmd[1]) - 1;
                    // mark the task as done
                    tasks[taskNum].markTaskDone();
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number.");
                } finally {
                    System.out.println("Great! I've marked this task as done:");
                    if (taskNum >= 0) {
                        System.out.println(tasks[taskNum]);
                    } else {
                        System.out.println("Error with task number");
                    }
                }

            } else {
                // adding tasks
                if (command.startsWith("todo")) {
                    String[] cmd = command.split("(?<=todo) ",2);
                    tasks[counter] = new ToDo(cmd[1]);
                } else if (command.startsWith("deadline")) {
                    String[] cmd = command.split("(?<=deadline) ",2 );
                    String[] taskDes = cmd[1].split("/by ");
                    tasks[counter] = new Deadline(taskDes[0], taskDes[1]); // <- magic num?
                } else if (command.startsWith("event")) {
                    String[] cmd = command.split("(?<=event) ", 2);
                    String[] taskDes = cmd[1].split("/at ");
                    tasks[counter] = new Event(taskDes[0], taskDes[1]);
                } else {
                    System.out.println("Invalid command");
                    continue;
                }
                
                System.out.println("Noted! I've added this task: ");
                System.out.println(tasks[counter].toString());
                counter++;
                System.out.println("Now you have " + counter + " tasks in this list.");
            }
        }
        sc.close();
    }
}
