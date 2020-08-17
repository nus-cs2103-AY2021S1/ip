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
        echo("    Hello! I'm Duke\n    How can I help you?");
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")){
                echo("Bye. Hope to see you again, bro!");
                break;
            }
            echo(input);
        }
    }
}