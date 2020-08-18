import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "I can be your friend who manages your task!");
        boolean exited = false;
        ArrayList<String> arr = new ArrayList<>();

        while (!exited) {
            int counter = 1;
            Scanner sc = new Scanner(System.in);

            String command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye. Please come back soon :(");
                exited = true;
            } else if (command.equals("list")) {
                for (String s: arr) {
                    System.out.println(counter + ". " + s);
                    counter++;
                }
            } else {
                arr.add(command);
                System.out.print("added: "+ command + "\n");
            }
        }

    }
}
