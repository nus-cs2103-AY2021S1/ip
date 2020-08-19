import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String open = "_______________________________________ \n"
                + "Hello! I'm Duke \n"
                + "What can I do for you? \n"
                + "_______________________________________ \n";
        String close = "_______________________________________ \n"
                + "Bye. Hope to see you again soon! \n"
                + "_______________________________________ \n";
        String line = "_______________________________________";
        System.out.println(open);

        String user_input;
        ArrayList<Task> user_list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            user_input = scanner.nextLine();
            if (user_input.equals("bye")) {
                break;
            } else if (user_input.equals("list")){
                String output = "";
                for (int i = 1; i <= user_list.size(); i++) {
                    output = output + i + ". " + user_list.get(i-1) + "\n";
                }
                System.out.println(line + "\n Here are the tasks in your list: \n" + output + line);
            } else {
                user_list.add(new Task(user_input));
                System.out.println(line + "\n added: " +user_input + "\n" + line);
            }
        }

        scanner.close();
        System.out.println(close);
    }
}
