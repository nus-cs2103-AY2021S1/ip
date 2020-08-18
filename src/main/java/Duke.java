import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        String seperateLine = "    _______________________________________";
        String spaceBeforeOder = "      ";
        System.out.println(seperateLine);
        System.out.println(spaceBeforeOder + "Hello! I'm Duke yy\n      What can I do for you?");
        System.out.println(seperateLine);
        String order = sc.next();
        while (!order.equals("bye")) {
            System.out.println(seperateLine);
            System.out.println(spaceBeforeOder + order);
            System.out.println(seperateLine);
            order = sc.next();
        }
        System.out.println(seperateLine);
        System.out.println(spaceBeforeOder + "Bye. Very nice to meet you!");
        System.out.println(spaceBeforeOder + "Hope to see you again soon! ");
        System.out.println(seperateLine);
        sc.close();
    }
}
