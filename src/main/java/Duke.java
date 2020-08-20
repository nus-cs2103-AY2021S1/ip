import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            } else {
                System.out.println(str);
            }
        }
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }
}
