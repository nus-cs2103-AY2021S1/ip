import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String tab = "     ";
        String line = tab + "____________________________________________________________";
        String intro = line + "\n" +
                tab + "Hello! I'm Duke\n" +
                tab + "What can I do for you?\n" +
                line + "\n";
        System.out.println(intro);
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(line);
                System.out.println(tab + "Bye. Hope to see you again soon!");
                System.out.println(line + "\n");
                break;
            } else if (input.equals("list")){
                int id = 1;
                System.out.println(line);
                for(String task : tasks) {
                    System.out.println(tab + id + ". " + task);
                    id++;
                }
                System.out.println(line + "\n");
            } else {
                tasks.add(input);
                System.out.println(line);
                System.out.println(tab + "added: " + input);
                System.out.println(line + "\n");
            }
        }
    }
}
