import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String res = "";
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        while(true) {
            res = sc.nextLine();
            if(res.equals("bye")) {
                break;
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.printf("     %s\n", res);
                System.out.println("    ____________________________________________________________");
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
