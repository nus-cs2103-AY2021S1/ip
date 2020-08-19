import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void displayMessage(String msg) {
        System.out.println("––––––––––––––––––––– *** –––––––––––––––––––––");
        System.out.println(msg);
        System.out.println("––––––––––––––––––––– *** –––––––––––––––––––––\n");
    }

    public static String getOrders(List<String> orders) {
        int index = 1;
        String orderMessage = "TO-DO LIST:";
        for (String order : orders) {
            orderMessage += String.format("\n   " + index + ". " + order);
            index++;
        }
        return orderMessage;
    }

    public static void main(String[] args) {

        List<String> orders = new ArrayList<>(100);

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

        while (sc.hasNextLine()) {

            String order = sc.nextLine();
            if (order.equalsIgnoreCase("bye")) {

                String goodbye = "Alright, see you soon!";
                displayMessage(goodbye);
                sc.close();
                break;

            } else if (order.equalsIgnoreCase("list")) {

                displayMessage(getOrders(orders));

            } else {

                String orderMessage = "You typed: " + order + "\n"
                        + "'" + order + "' added to list!";

                orders.add(order);
                displayMessage(orderMessage);

            }
        }
    }
}

