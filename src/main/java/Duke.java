import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    Task[] tasks;

    String indent = "    ";
    String textIndent = "     ";
    String divide = indent + "____________________________________________________________\n";
    String welcomeMessage = divide + textIndent + "Hello! I'm Duke\n" + textIndent + "What can I do for you?\n" + divide;
    String exitMessage = textIndent + "Bye. Hope to see you again soon!\n";
    String listMessage = textIndent + "Here are the tasks in your list:\n";
    String doneMessage = textIndent + "Nice! I've marked this task as done:\n";
    String todoMessage = textIndent + "Got it. I've added this task:\n";

    static String[] commands = {"bye", "list", "done", "todo", "deadline", "event"};

    Duke() {
        this.tasks = new Task[100];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        System.out.println(duke.welcomeMessage);

        int index = 0;
        while(sc.hasNext()) {
            String str = sc.nextLine();
            String[] part = str.split(" ", 2);

            if (part[0].equals(commands[0])) { // handle bye
                System.out.println(duke.divide + duke.exitMessage + duke.divide);
                break;
            } else if (part[0].equals(commands[1])) { // handle list
                for (int i = 0; i < index; i++) {
                    String ls = (i + 1) +"." + duke.tasks[i];
                    if (i == 0) {
                        System.out.println(duke.divide + duke.listMessage + duke.textIndent + ls);
                    } else {
                        System.out.println(duke.textIndent + ls);
                    }
                }
                System.out.println(duke.divide);
            } else if (part[0].equals(commands[2])) { // handle done
                int num = Integer.parseInt(part[1]);
                Task curr = duke.tasks[num - 1];
                curr.markAsDone();
                System.out.println(duke.divide + duke.doneMessage + duke.textIndent + curr + "\n" + duke.divide);
            } else if (part[0].equals(commands[3])) { // handle todo
                String numTasks = "Now you have " + (index + 1) + " tasks in the list.\n";
                duke.tasks[index] = new Todo(part[1]);
                String out = duke.divide + duke.todoMessage + duke.textIndent + duke.tasks[index] +
                        "\n" + duke.textIndent + numTasks + duke.divide;
                System.out.println(out);
                index++;
            } else if (part[0].equals(commands[4])) { // handle deadline
                String numTasks = "Now you have " + (index + 1) + " tasks in the list.\n";
                String[] split = part[1].split(" /by", 2);
                duke.tasks[index] = new Deadline(split[0], split[1]);
                String out = duke.divide + duke.todoMessage + duke.textIndent + duke.tasks[index] +
                        "\n" + duke.textIndent + numTasks + duke.divide;
                System.out.println(out);
                index++;
            } else if (part[0].equals(commands[5])) { // handle event
                String numTasks = "Now you have " + (index + 1) + " tasks in the list.\n";
                String[] split = part[1].split(" /at", 2);
                duke.tasks[index] = new Event(split[0], split[1]);
                String out = duke.divide + duke.todoMessage + duke.textIndent + duke.tasks[index] +
                        "\n" + duke.textIndent + numTasks + duke.divide;
                System.out.println(out);
                index++;
            } else {
                System.out.println("Oops!");
            }
        }
    }
}
