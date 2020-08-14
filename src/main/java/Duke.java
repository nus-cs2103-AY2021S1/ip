import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Duke.echo("Hello! I'm Duke\nWhat can I do for you?");

        while (sc.hasNext()) {
            String nxt = sc.next();
            if (!nxt.equals("bye")) {
                Duke.echo(nxt);
            } else {
                Duke.echo("Bye. Hope to see you again soon!");
                break;
            }
        }
    }

    private static void echo(String s) {
        String line = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", line, s, line);
    }
}
