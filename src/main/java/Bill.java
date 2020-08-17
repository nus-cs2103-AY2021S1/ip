import java.util.*;

public class Bill {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        System.out.println("------------------------------------------");
        System.out.println("Hello! I'm Bill ");
        System.out.println("What Can I do for you? ");
        System.out.println("------------------------------------------");

        String input = sc.next();

       while (!input.equals("bye")) {
           System.out.println("------------------------------------------");
           System.out.println(input);
           System.out.println("------------------------------------------");
           input = sc.next();
       }

        System.out.println("------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");









    }

}
