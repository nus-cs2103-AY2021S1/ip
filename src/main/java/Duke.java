
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> books = new ArrayList<>();
        while (sc.hasNext()) {

            String echo = sc.nextLine();
            String[] s = echo.split("\\s");
            String first = s[0];

            if (first.equals("list")) {
                for (int i = 0; i < books.size(); i++) {
                    System.out.println(i+1 + ". " + books.get(i));
                }
            } else if (first.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                books.add(echo);
                System.out.println("added: " + echo);
            }

        }
    }
}
