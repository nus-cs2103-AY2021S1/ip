import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String lines = "    ____________________________________________________________";
        String defaultGreeting = lines + "\n" + "     Hello! I'm Duke \n" + "     What can I do for you?\n" + lines + "\n";
        System.out.println(defaultGreeting);
        Scanner sc = new Scanner(System.in);
        List<String> currentList = new ArrayList<>();
        String input = sc.nextLine();
        while (!input.equals("bye")){
            if (!input.equals("list")) {
                String response = lines + "\n      added: " + input + "\n" + lines + "\n";
                System.out.println(response);
                currentList.add(input);
            } else if (input.equals("list")){
                System.out.println(lines);
                for(int i = 0; i < currentList.size(); i++) {
                    String currentLine = "      "+ (i + 1) + ". " + currentList.get(i);
                    System.out.println(currentLine);
                }
                System.out.println(lines);
            }
            input = sc.nextLine();
        }
        String end_Greeting = lines + "\n" + "      Bye. Hope to see you again soon!\n" + lines;
        System.out.println(end_Greeting);

    }
}
