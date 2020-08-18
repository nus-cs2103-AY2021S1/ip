import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lists = new ArrayList<String>();
        String logo ="      ___                __     \n"
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
            if (received.equals("bye")) {
                String[] byeMessage = {"Bye. Hope to see you again soon!"};
                Response bye = new Response(byeMessage);
                bye.printResponse();
                break;
            } else if (received.equals("list")) {
                Response list = new Response(lists.toArray(new String[0]));
                list.printResponse();
            } else {
                String[] message = {"added: " + received};
                lists.add(message[0]);
                Response msg = new Response(message);
                msg.printResponse();
            }
        }
    }
}
