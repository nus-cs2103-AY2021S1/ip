import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    Task[] tasks;

    String indent = "    ";
    String textIndent = "     ";
    String divide = indent + "____________________________________________________________\n";
    String welcomeMessage = divide + textIndent + "Hello! I'm Duke\n" + textIndent + "What can I do for you?\n" + divide;
    String exitMessage = textIndent + "Bye. Hope to see you again soon!\n";
    String listMessage = "Here are the tasks in your list:\n";
    String doneMessage = "Nice! I've marked this task as done:\n";

    static String[] commands = {"bye", "list", "done "};

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
            String[] part = str.split("(?<=\\D)(?=\\d)");

            if (part[0].equals(commands[0])) {
                System.out.println(duke.divide + duke.exitMessage + duke.divide);
                break;
            } else if (part[0].equals(commands[1])) {
                for (int i = 0; i < index; i++) {
                    String ls = (i + 1) +"." + duke.tasks[i];
                    if (i == 0) {
                        System.out.println(duke.divide + duke.textIndent + duke.listMessage + duke.textIndent + ls);
                    } else {
                        System.out.println(duke.textIndent + ls);
                    }
                }
                System.out.println(duke.divide);
            } else if (part[0].equals(commands[2])) {
                int num = Integer.parseInt(part[1]);
                Task curr = duke.tasks[num - 1];
                curr.markAsDone();
                System.out.println(duke.divide + duke.textIndent + duke.doneMessage + duke.textIndent + curr);
            } else {
                String out = duke.divide + duke.textIndent + "added: " + part[0] + "\n" + duke.divide;
                duke.tasks[index] = new Task(part[0]);
                System.out.println(out);
                index++;
            }
        }
    }
}
