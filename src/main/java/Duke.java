import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String border = "____________________________________________________________\n";
        System.out.println(border + "Hello! I'm Duke\n" + "What can I do for you?\n" + border);

        Scanner scan = new Scanner(System.in);

        List<String> storage = new ArrayList<>();

        while(scan.hasNext()) {
            String input = scan.nextLine();
            if(input.equals("bye")) {
                System.out.println(border + "Bye. Hope to see you again soon!\n" + border);
                return;
            } else {
                storage.add(input);
                System.out.println(border + "added: " + input + "\n" + border);
            }
        }
    }
}
