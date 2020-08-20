import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scan1 = new Scanner(System.in);
        ArrayList<Task> storage = new ArrayList<>();
        int count = 1;


        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");


        /* level 2 */
        while (scan1.hasNext()) {
            String input = scan1.nextLine();
            if (!input.equals("bye") && !input.equals("list") && !input.startsWith("done")) {
                Task task = new Task(input);
                storage.add(task);
                String added = "added: " + input;
                System.out.println(added);
            } else if (input.equals("list")) {
                for (Task item: storage) {
                    int position = storage.indexOf(item) + 1;
                    System.out.println(position + ". " + item.getDescription());

                }
            }
            else {
                String bye = "Bye. Hope to see you again soon!";
                System.out.println(bye);
                scan1.close();
                break;
            }
        }


    }
}
