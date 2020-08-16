import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Dobby {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String underscore_line = "    __________________________________________";
        System.out.println(underscore_line);
        System.out.println("    Hello! I'm Dobby\n    How can I help you?");
        System.out.println(underscore_line);
        ArrayList<String> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String text = scanner.nextLine();
            if (text.equalsIgnoreCase("bye")) {
                scanner.close();
                System.out.println(underscore_line);
                System.out.println("    Goodbye. Hope to see you again soon!");
                System.out.println(underscore_line);
                System.exit(0);
            } else if (text.equalsIgnoreCase("list")) {
                int i = 0;
                System.out.println(underscore_line);
                for (String item: list) {
                    i++;
                    System.out.println("    " + i + ". " + item);
                }
                System.out.println(underscore_line);
            } else {
                list.add(text);
                System.out.println(underscore_line);
                System.out.println("    Added: " + text );
                System.out.println(underscore_line);
            }
        }
    }
}
