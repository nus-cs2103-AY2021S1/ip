import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String res = "";
        List<String> data = new ArrayList<String>();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        while (true) {
            res = sc.nextLine();
            if (res.equals("bye")) {
                break;
            } else if (res.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < data.size(); i++) {
                    System.out.printf("     %d. %s\n", i + 1, data.get(i));
                }
                System.out.println("    ____________________________________________________________");
            } else {
                data.add(res);
                System.out.println("    ____________________________________________________________");
                System.out.printf("     added: %s\n", res);
                System.out.println("    ____________________________________________________________");
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }


}
