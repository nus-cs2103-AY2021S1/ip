import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + " What can I do for you?");

        List<Task> ls = new ArrayList<>();
        String line = "";

        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String firstArg = line.split(" ")[0];
            switch (line) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int x = 0; x < ls.size(); x++) {
                        System.out.println(x + 1 + ":" + ls.get(x).toString());
                    }
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    switch (firstArg) {
                        case "done":
                            String x = line.split(" ")[1];
                            try {
                                int y = Integer.parseInt(x);
                                ls.get(y - 1).markAsDone();
                            } catch (Exception exception) {
                                System.out.println("Please mark a valid item as done");
                            } finally {
                                break;
                            }
                        case "todo":
                            String todoString = line.substring(5);
                            ToDo todo = new ToDo(todoString);
                            ls.add(todo);
                            System.out.println("____________________________________________________________");
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(todo.toString());
                            System.out.format("Now you have %d tasks in the list \n", ls.size());
                            System.out.println("____________________________________________________________");
                            break;

                        case "deadline":
                            String by = line.split("/")[1];
                            String by2 = by.substring(by.indexOf("by") + 3);
                            String deadlineString = line.split("/")[0].substring(9);


                            Deadline deadline = new Deadline(deadlineString,by2);
                            ls.add(deadline);
                            System.out.println("____________________________________________________________");
                            System.out.println(" Got it. I've added this task: ");
                            System.out.println(deadline.toString());
                            System.out.format("Now you have %d tasks in the list \n", ls.size());
                            System.out.println("____________________________________________________________");
                            break;

                        case "event":
                            String at = line.split("/")[1];
                            String at2 = at.substring(at.indexOf("at") + 3);

                            String eventString = line.split("/")[0].substring(6);
                            Event event = new Event(eventString,at2);
                            ls.add(event);
                            System.out.println("____________________________________________________________");
                            System.out.println(" Got it. I've added this task: ");
                            System.out.println(event.toString());
                            System.out.format("Now you have %d tasks in the list \n", ls.size());
                            System.out.println("____________________________________________________________");
                            break;




                        default:
                            ls.add(new Task(line));
                            System.out.println("____________________________________________________________");
                            System.out.println("added: " + line);
                            System.out.println("____________________________________________________________");

                    }


            }

        }
    }
}



