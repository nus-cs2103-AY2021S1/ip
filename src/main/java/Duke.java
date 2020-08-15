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
        String line = "________________________________";


        System.out.println("Hello from");
        System.out.println(logo);
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        String output = sc.next();

        while (!output.equals("bye")) {

            System.out.println(line);

            if (output.equals("list")) {
                for (int i = 0; i < list.size(); i++){
                    System.out.println((i + 1) + ". " + list.get(i));
                }
            }
            else {
                list.add(output);
                System.out.println("added: " + output);
            }

            System.out.println(line);

            output = sc.next();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
