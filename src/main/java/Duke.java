import java.util.Scanner;

public class Duke {

    public static boolean isInteger(String s) {
        if(s == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Task[] orderList = new Task[100];
        Scanner sc =  new Scanner(System.in);
        String seperateLine = "    _______________________________________";
        String spaceBeforeOder = "      ";
        System.out.println(seperateLine);
        System.out.println(spaceBeforeOder + "Hello! I'm Duke yy\n      What can I do for you?");
        System.out.println(seperateLine);
        String order = sc.nextLine();
        int numOfOders = 0;
        while (!order.equals("bye")) {
            if (order.equals("list")) {
                System.out.println(seperateLine);
                System.out.println(spaceBeforeOder + "Here are the tasks in your list:");
                for (int i = 0; i < numOfOders; i++){
                    String checkbox = "[" + orderList[i].getStatusIcon() +"] ";
                    System.out.println(spaceBeforeOder + (i + 1) + ". " +
                            checkbox + orderList[i].name);
                }
                System.out.println(seperateLine);
            } else {
                if(order.split(" ")[0].equals("done") && isInteger(order.split(" ")[1])
                    && Integer.parseInt(order.split(" ")[1]) <= numOfOders) {
                    int toMark = Integer.parseInt(order.split(" ")[1]) - 1;
                    orderList[toMark] = orderList[toMark].markDone();
                    System.out.println(seperateLine);
                    System.out.println(spaceBeforeOder + "Nice! I've marked this task as done:");
                    System.out.println(spaceBeforeOder + "  [" + orderList[toMark].getStatusIcon() + "] " + orderList[toMark].name);
                    System.out.println(seperateLine);
                } else {
                    System.out.println(seperateLine);
                    System.out.println(spaceBeforeOder + "added: " + order);
                    System.out.println(seperateLine);
                    orderList[numOfOders] = new Task(order, false);
                    numOfOders++;
                }
            }

            order = sc.nextLine();
        }
        System.out.println(seperateLine);
        System.out.println(spaceBeforeOder + "Bye. Very nice to meet you!");
        System.out.println(spaceBeforeOder + "Hope to see you again soon! ");
        System.out.println(seperateLine);
        sc.close();
    }

}
