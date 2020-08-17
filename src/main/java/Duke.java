
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> books = new ArrayList<>();
        while (sc.hasNext()) {

            String echo = sc.nextLine();
            String[] s = echo.split("\\s");
            String first = s[0];

            if (first.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < books.size(); i++) {
                    System.out.println(i+1 + ". " + books.get(i));
                }
            } else if (first.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (first.equals("done")) {
                int index = Integer.parseInt(s[1]);
                books.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + books.get(index - 1).getStatusIcon() + " return book");
            } else {
                Task t = new Task(echo);
                books.add(t);
                System.out.println("added: " + echo);
            }
        }
    }
}
