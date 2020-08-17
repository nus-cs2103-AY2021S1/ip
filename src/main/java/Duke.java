import main.java.TaskManager;

import java.util.Scanner;

public class Duke {
    /**
     * Print on screen the message wrapped with dotted lines.
     * @param message Message to be printed out
     */
    public static void echo(String message) {
        String line = "____________________________________________________________\n";
        System.out.println(line + message + "\n" + line);

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager duke = new TaskManager();
        echo("    Hello! I'm Duke\n    How can I help you?");
        while (sc.hasNext()) {
            String input = sc.nextLine();
            switch (input) {
                case "bye" :
                    echo("Bye. Hope to see you again, bro!");
                    break;
                case "list":
                    echo(duke.toString());
                    break;
                default:
                    duke.addTask(input);
                    echo("added: " + input);
                    break;
            }
        }
    }
}