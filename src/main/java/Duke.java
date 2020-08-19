import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> lists = new ArrayList<Task>();
        String logo = "      ___                __     \n"
                + "     |  _ \\   ____      |  |    \n"
                + "     | |_| | / __  \\  __|  |__  \n"
                + "     |  _ / | |  |  ||__    __| \n"
                + "     | |    | |_/   |   |  |    \n"
                + "     |_|     \\___/|_|   |__|    \n";
        String[] greetingTexts = {"Hello! I'm Pat", "What can I do for you?"};
        Response greeting = new Response(greetingTexts);
        System.out.println("     Hello from\n" + logo);
        greeting.printResponse();
        while(sc.hasNext()) {
            String received = sc.nextLine();
            if (received.equals("bye")) {
                String[] byeMessage = {"Bye. Hope to see you again soon!"};
                Response bye = new Response(byeMessage);
                bye.printResponse();
                break;
            } else if (received.equals("list")) {
                Response list = new Response(lists.toArray(new Task[0]), Response.Tag.LIST);
                list.printResponse();
            } else {
                String[] test = received.split(" ");
                if (test[0].equals("done")) {
                    int idx = Integer.parseInt(test[1]) - 1;
                    Task target = lists.get(idx);
                    target.markDone();
                    Response msg = new Response(new String[]{"Nice! I've marked this task as done:", "  " + target});
                    msg.printResponse();
                } else {
                    if (test[0].equals("todo")) {
                        String description = received.split("todo ")[1];
                        Todo todo = new Todo(description);
                        lists.add(todo);
                        Response msg = new Response(new Task[]{todo}, Response.Tag.ADD, lists.size());
                        msg.printResponse();
                    } else if (test[0].equals("deadline")) {
                        String[] str = received.split("deadline ")[1].split(" /");
                        Deadline deadline = new Deadline(str[0], str[1]);
                        lists.add(deadline);
                        Response msg = new Response(new Task[]{deadline}, Response.Tag.ADD, lists.size());
                        msg.printResponse();
                    } else if (test[0].equals("event")) {
                        String[] str = received.split("event ")[1].split(" /");
                        Event event = new Event(str[0], str[1]);
                        lists.add(event);
                        Response msg = new Response(new Task[]{event}, Response.Tag.ADD, lists.size());
                        msg.printResponse();
                    }
                }
            }
        }
    }
}
