import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Duke.echo("Hello! I'm Duke\nWhat can I do for you?");

        Duke.checkCommands(sc);
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

    public static void checkCommands(Scanner sc) {
        List<Task> lst = new ArrayList<>();
        loop: while (sc.hasNextLine()) {
            String sentence = sc.nextLine();
            String[] arr = sentence.split("\\s+");
            String command = arr[0];
            String comText = "";
            for (int i = 1; i < arr.length; i++) {
                comText += arr[i] + " ";
            }
            comText = comText.trim();
            switch (command) {
                case "todo":
                    if (arr.length == 1) {
                        Duke.echo("☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        Task todo = new Todo(comText);
                        lst.add(todo);
                        Duke.echo(String.format("Got it. I've added this task:" +
                                "\n%s\nNow you have %d tasks in the list.", todo, lst.size()));
                    }
                    break;
                case "deadline":
                    int dIdx = comText.lastIndexOf("/by");
                    if (arr.length == 1) {
                        Duke.echo("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else if (dIdx == -1 ){
                        Duke.echo("☹ OOPS!!! Please include a /by");
                    } else {
                        String desc = comText.substring(0, dIdx-1);
                        String by = comText.substring(dIdx+4, comText.length()).trim();
                        Task deadline = new Deadline(desc, by);
                        lst.add(deadline);
                        Duke.echo(String.format("Got it. I've added this task:" +
                                "\n%s\nNow you have %d tasks in the list.", deadline, lst.size()));
                    }
                    break;
                case "event":
                    int eIdx = comText.lastIndexOf("/at");
                    if (arr.length == 1) {
                        Duke.echo("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if (eIdx == -1 ){
                        Duke.echo("☹ OOPS!!! Please include a /at");
                    } else {
                        String desc = comText.substring(0, eIdx-1);
                        String at = comText.substring(eIdx+4, comText.length()).trim();
                        Task event = new Event(desc, at);
                        lst.add(event);
                        Duke.echo(String.format("Got it. I've added this task:" +
                                "\n%s\nNow you have %d tasks in the list.", event, lst.size()));
                    }
                    break;
                case "list":
                    Duke.printList(lst);
                    break;
                case "done":
                    if (arr.length != 2) {
                        Duke.echo("☹ OOPS!!! Please use `done item_number`, e.g. done 3");
                        break;
                    }
                    int lstNum = Integer.parseInt(arr[1]);
                    if (lstNum > lst.size() || lstNum < 0) {
                        Duke.echo("☹ OOPS!!! The item does not exist within the list");
                    } else {
                        Task item = lst.get(lstNum-1);

                        item.setStatus(true);
                        Duke.echo(String.format("Nice! I've marked this task as done:\n%s", item));
                    }
                    break;
                case "bye":
                    Duke.echo("Bye. Hope to see you again soon!");
                    break loop;
                default:
                    Duke.echo("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        }
    }
}
