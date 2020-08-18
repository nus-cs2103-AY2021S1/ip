import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] orderList = new String[100];
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
                for (int i = 0; i < numOfOders; i++){
                    System.out.println(spaceBeforeOder + (i + 1) + ". " + orderList[i]);
                }
                System.out.println(seperateLine);
            } else {
                System.out.println(seperateLine);
                System.out.println(spaceBeforeOder + "added: " + order);
                System.out.println(seperateLine);
                orderList[numOfOders] = order;
                numOfOders++;
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
