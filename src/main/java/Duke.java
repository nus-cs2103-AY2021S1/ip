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
                    System.out.println(spaceBeforeOder + (i + 1) + ". " +
                            orderList[i]);
                }
                System.out.println(seperateLine);
            } else if (order != null){
                String command = order.split(" ")[0];
                if(command.equals("done") && isInteger(order.split(" ")[1])
                    && Integer.parseInt(order.split(" ")[1]) <= numOfOders) {
                    int toMark = Integer.parseInt(order.split(" ")[1]) - 1;
                    orderList[toMark] = orderList[toMark].markDone();
                    System.out.println(seperateLine);
                    System.out.println(spaceBeforeOder + "Nice! I've marked this task as done:");
                    System.out.println(spaceBeforeOder + "  " + orderList[toMark]);
                    System.out.println(seperateLine);
                } else {
                    if(command.equals("deadline")) {
                        String sub = order.substring(9);
                        String by = sub.split("/by ")[1];
                        String description = sub.split("/by ")[0];
                        orderList[numOfOders] = new Deadline(description, by);
                    } else if(command.equals("event")) {
                        String sub = order.substring(6);
                        String at = sub.split("/at ")[1];
                        String description = sub.split("/at ")[0];
                        orderList[numOfOders] = new Event(description, at);
                    } else if(command.equals("todo")) {
                        String sub = order.substring(5);
                        orderList[numOfOders] = new Todo(sub);
                    }

                    System.out.println(seperateLine);
                    System.out.println(spaceBeforeOder + "Got it. I've added this task: ");
                    System.out.println(spaceBeforeOder + "  " + orderList[numOfOders]);
                    System.out.println(spaceBeforeOder + "Now you have " + (numOfOders + 1) + " tasks in the list.");
                    System.out.println(seperateLine);
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
