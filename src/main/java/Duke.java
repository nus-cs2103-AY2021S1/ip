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
            String command = arr[0];
            String comText = "";
            for (int i = 1; i < arr.length; i++) {
                comText += arr[i] + " ";
            }
            comText.trim();
            switch (command) {
                case "todo":
                    Task todo = new Todo(comText);
                    lst.add(todo);
                    Duke.echo(String.format("Got it. I've added this task: " +
                                    "\n%s\nNow you have %d tasks in the list.", todo, lst.size()));
                    break;
                case "deadline":
                    int dIdx = comText.lastIndexOf("/by");
                    Task deadline = new Deadline(
                            comText.substring(0, dIdx-1),
                            comText.substring(dIdx+4, comText.length())
                    );
                    lst.add(deadline);
                    Duke.echo(String.format("Got it. I've added this task: " +
                            "\n%s\nNow you have %d tasks in the list.", deadline, lst.size()));
                    break;
                case "event":
                    int eIdx = comText.lastIndexOf("/at");
                    Task event = new Event(
                            comText.substring(0, eIdx-1),
                            comText.substring(eIdx+4, comText.length())
                    );
                    lst.add(event);
                    Duke.echo(String.format("Got it. I've added this task: " +
                            "\n%s\nNow you have %d tasks in the list.", event, lst.size()));
                    break;
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
