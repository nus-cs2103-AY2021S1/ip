import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String border = "____________________________________________________________\n";
        System.out.println(border + "Hello! I'm Duke\n" + "What can I do for you?\n" + border);

        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()) {
            String input = scan.next();
            if(input.equals("bye")) {
                System.out.println(border + "Bye. Hope to see you again soon!\n" + border);
                return;
            } else {
                System.out.println(border + input + "\n" + border);
            }
        }
    }
}
