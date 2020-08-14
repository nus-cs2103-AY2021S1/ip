import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    String[] tasks;

    String indent = "    ";
    String textIndent = "     ";
    String divide = indent + "____________________________________________________________\n";
    String welcomeMessage = divide + textIndent + "Hello! I'm Duke\n" + textIndent + "What can I do for you?\n" + divide;
    String exitMessage = textIndent + "Bye. Hope to see you again soon!\n";

    static String[] commands = {"bye", "list"};

    Duke() {
        this.tasks = new String[100];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        System.out.println(duke.welcomeMessage);

        int index = 0;
        while(sc.hasNext()) {
            String text = sc.nextLine();
            if (text.equals(commands[0])) {
                System.out.println(duke.divide + duke.exitMessage + duke.divide);
                break;
            } else if (text.equals(commands[1])) {
                for (int i = 0; i < index; i++) {
                    String ls = (i + 1) +". " + duke.tasks[i];
                    if (i == 0) {
                        System.out.println(duke.divide + duke.textIndent + ls);
                    } else {
                        System.out.println(duke.textIndent + ls);
                    }
                }
                System.out.println(duke.divide);
            } else {
                String out = duke.divide + duke.textIndent + "added: " + text + "\n" + duke.divide;
                duke.tasks[index] = text;
                System.out.println(out);
                index++;
            }
        }
    }
}
