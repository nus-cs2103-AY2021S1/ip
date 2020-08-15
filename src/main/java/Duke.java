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
        List<Task> list = new ArrayList<>();

        String output;

         do {

             output = sc.nextLine();
             System.out.println(line);

             if (output.equals("list")) {
                 for (int i = 0; i < list.size(); i++) {
                     System.out.println((i + 1) + ". " + list.get(i));
                 }
                 System.out.println(line);
                 continue;
             }

             if (output.indexOf("done") == 0) {
                 String[] arr = output.split(" ");

                 try {

                     int index = Integer.parseInt(arr[1]) - 1;

                     if (index < list.size()) {
                         list.get(index).completeTask();
                         System.out.println("Nice! I've marked this task as done:");
                         System.out.println(list.get(index));
                         System.out.println(line);
                     }

                     continue;

                 } catch (NumberFormatException nfe) {

                 }
             }

             list.add(new Task(output));
             System.out.println("added: " + output);
             System.out.println(line);

        } while (!output.equals("bye"));

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
