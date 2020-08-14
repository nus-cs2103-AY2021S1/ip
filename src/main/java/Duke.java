import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String tab = "     ";
        String line = "____________________________________________________________\n";
        String intro = tab + line +
                tab + "Hello! I'm Duke\n" +
                tab + "What can I do for you?\n" +
                tab + line;
        System.out.println(intro);
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println(tab + line +
                        tab + "Bye. Hope to see you again soon!\n" +
                        tab + line);
                break;
            }
            System.out.println(tab + line +
                    tab + input +  "\n" +
                    tab + line);
        }
    }
}
