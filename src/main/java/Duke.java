import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Task> lst = new ArrayList<>();

        Duke.echo("Hello! I'm Duke\nWhat can I do for you?");

        loop: while (sc.hasNextLine()) {
            String sentence = sc.nextLine();
            String[] arr = sentence.split("\\s+");
            String nxt = arr[0];
            switch (nxt) {
                case "list":
                    Duke.printList(lst);
                    break;
                case "done":
                    int lstNum = Integer.parseInt(arr[1]);
                    Task item = lst.get(lstNum-1);

                    item.setStatus(true);
                    Duke.echo(String.format("Nice! I've marked this task as done: \n%s", item));
                    break;
                case "bye":
                    Duke.echo("Bye. Hope to see you again soon!");
                    break loop;
                default:
                    Duke.echo("added: " + sentence);
                    lst.add(new Task(sentence));
                    break;
            }
        }
    }

    private static void echo(String s) {
        String line = "____________________________________________________________";
        System.out.printf("%s\n%s\n%s\n", line, s, line);
    }

    private static void printList(List<Task> lst) {
        String s = "";
        for (int i = 1; i <= lst.size(); i++) {
            Task item = lst.get(i-1);
            s += String.format("%d.%s", i, item);
            s += (i == lst.size()) ? "" : "\n";
        }
        Duke.echo(s);
    }
}
