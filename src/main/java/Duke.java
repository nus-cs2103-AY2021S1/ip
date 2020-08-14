import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<String> storage = new ArrayList<>();

    // border line
    public static void line() {
        System.out.println("____________________________________________________________");
    }

    // output sandwiched by border lines
    public static void echo(String output) {
        line();
        System.out.println(output);
        line();
    }

    // stores user inputs in storage
    public static void store(String item) {
        storage.add(item);
    }

    // lists items in storage with numbers
    public static void listOut() {
        line();
        for (int i = 0; i < storage.size(); i++) {
            // list starts from 1
            System.out.println((i + 1) + ". " + storage.get(i));
        }
        line();
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
            // bye exits while
            if (output.equals("bye")) {
                echo("Ciao!");
                break;
            }
            // list of items in storage
            else if (output.equals("list")) {
                listOut();
            }
            // no special order, adds user input to storage
            else {
                storage.add(output);
                echo("added: " + output);
            }
        }

    }
}
