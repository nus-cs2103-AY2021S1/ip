import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);

        ArrayList<String> lst = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            } else if (str.equals("list")) {
                for (int i = 0; i < lst.size(); i ++) {
                    String num = Integer.toString(i + 1);
                    System.out.println(num + ". " + lst.get(i));
                }
            } else {
                lst.add(str);
                System.out.println("added: " + str);
            }
        }
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(goodbyeMessage);
    }
}
