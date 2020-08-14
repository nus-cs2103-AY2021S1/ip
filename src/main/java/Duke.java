import java.util.Scanner;

public class Duke {
    public static void echo(String output) {
        System.out.println("____________________________________________________________");
        System.out.println(output);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo =
                          " ____             _     \n"
                        + "|  _ \\           | |    \n"
                        + "| |_) |_ __ _   _| |__\n"
                        + "|  _ <| '__| | | | '_ \\ \n"
                        + "| |_) | |  | |_| | | | |\n"
                        + "|____/|_|   \\__,_|_| |_|\n";

        echo(logo + "What's up?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String output = scanner.next();
            if (output.equals("bye")) {
                echo("Ciao!");
                break;
            }
            echo(output);
        }

    }
}
