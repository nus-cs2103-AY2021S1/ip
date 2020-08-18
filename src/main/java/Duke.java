import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________\n";
        System.out.println(line + logo + "\nHello, I'm Duke \nWhat can I do for you?\n" + line);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        List<String> taskList = new ArrayList<>();

        while(!command.equals("bye")) {
            if(command.equals("list")) {
                System.out.print(line);
                for(int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                System.out.println(line);
            } else {
                System.out.println(line + "added: " + command + "\n" + line);
                taskList.add(command);
            }
            command = sc.nextLine();
        }

        System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
    }
}
