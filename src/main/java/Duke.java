import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.initialise();
    }

    private void initialise() {
        Scanner sc = new Scanner(System.in);
        greet();
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                echo(command);
            }
        }
        exit();
    }

    private void greet() {
        System.out.println("    ------------------------------------------------------\n" +
                "     Hello! I'm Duke \n     What can I do for you?\n" +
                "    ------------------------------------------------------\n");
    }

    private void echo(String command) {
        System.out.println("    ------------------------------------------------------\n" +
                "     " + command + "\n" +
                "    ------------------------------------------------------\n");    }

    private void exit() {
        System.out.println("    ------------------------------------------------------\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ------------------------------------------------------\n");    }
}
