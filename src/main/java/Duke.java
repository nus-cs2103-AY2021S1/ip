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
            String received = sc.next();
            String duplicate = new String(received);
            if (received.equals("bye")) {
                String[] byeMessage = {"Bye. Hope to see you again soon!"};
                Response bye = new Response(byeMessage);
                bye.printResponse();
                break;
            } else if (received.equals("list")) {
                Response list = new Response(lists.toArray(new Task[0]), Response.Tag.LIST);
                list.printResponse();
            } else {
                if (received.equals("done")) {
                    int idx = sc.nextInt() - 1;
                    Task target = lists.get(idx);
                    target.markDone();
                    Response msg = new Response(new String[]{"Nice! I've marked this task as done:", "  " + target});
                    msg.printResponse();
                } else {
                    Task newTask = new Task(received);
                    lists.add(newTask);
                    Response msg = new Response(new String[]{received}, Response.Tag.ADD);
                    msg.printResponse();
                }
            }
        }
    }
}
