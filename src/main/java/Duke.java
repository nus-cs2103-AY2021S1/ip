import java.util.Scanner;

public class Duke {

    public static void displayMessage(String msg) {
        System.out.println("\n––––––––––––––––––––– *** –––––––––––––––––––––");
        System.out.println(msg);
        System.out.println("––––––––––––––––––––– *** –––––––––––––––––––––");
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcome = "Duke at your service!\n"
                + logo + "\n" +
                "How can I help you?\n" +
                "Type in your orders below.";

        displayMessage(welcome);
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String order = sc.next();
            if (order.equalsIgnoreCase("bye")) {
                String goodbye = "Alright, see you soon!";
                displayMessage(goodbye);
                sc.close();
                break;
            } else {
                displayMessage("You typed: " + order);
            }
        }
    }
}

