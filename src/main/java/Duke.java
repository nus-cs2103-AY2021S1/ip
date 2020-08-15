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
            try {
                switch (command) {
                    case "todo":
                        if (arr.length == 1) {
                            throw new EmptyDescException();
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
                            throw new EmptyDescException();
                        } else if (dIdx == -1 || comText.length() == (dIdx+3)){
                            throw new DeadlineException();
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
                            throw new EmptyDescException();
                        } else if (eIdx == -1 || comText.length() == (eIdx+3)){
                            throw new EventTaskException();
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
                            throw new InvalidCommandException();
                        }
                        int doneNum = Integer.parseInt(arr[1]);
                        if (doneNum > lst.size() || doneNum < 0) {
                            throw new InvalidIndexException();
                        } else {
                            Task item = lst.get(doneNum-1);

                            item.setStatus(true);
                            Duke.echo(String.format("Nice! I've marked this task as done:\n%s", item));
                        }
                        break;
                    case "delete":
                        if (arr.length != 2) {
                            throw new InvalidCommandException();
                        }
                        int deleteNum = Integer.parseInt(arr[1]);
                        if (deleteNum > lst.size() || deleteNum < 0) {
                            throw new InvalidIndexException();
                        } else {
                            Task item = lst.get(deleteNum-1);
                            lst.remove(deleteNum-1);
                            Duke.echo(String.format("Noted. I've removed this task:\n%s" +
                                    "\nNow you have %d tasks in the list.", item, lst.size()));
                        }
                        break;
                    case "bye":
                        Duke.echo("Bye. Hope to see you again soon!");
                        break loop;
                    default:
                        throw new InvalidCommandException();
                }
            } catch (Exception e) {
                Duke.echo(e.getMessage());
            }
        }
    }
}
