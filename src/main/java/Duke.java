import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> lst = new ArrayList<>();

        Duke.echo("Hello! I'm Duke\nWhat can I do for you?");

        loop: while (sc.hasNextLine()) {
            String nxt = sc.nextLine();
            switch (nxt) {
                case "list":
                    Duke.printList(lst);
                    break;
                case "bye":
                    Duke.echo("Bye. Hope to see you again soon!");
                    break loop;
                default:
                    Duke.echo("added: " + nxt);
                    lst.add(nxt);
                    break;
            }
        }
    }

    private static void echo(String s) {
        String line = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", line, s, line);
    }

    private static void printList(List lst) {
        String s = "";
        for (int i = 1; i <= lst.size(); i++) {
            s += String.format("%d. %s", i, lst.get(i-1));
            s += i == lst.size() ? "" : "\n";
        }
        Duke.echo(s);
    }
}
