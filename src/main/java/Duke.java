import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void greet() {
        System.out.println("Hello! I'm Jia Le :)");
        System.out.println("How may I be of assistance to you?");
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line =scanner.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye! Hope to see you again ;)");
                break;
            } else if (line.equals("list")) {
                int count = 1;
                for (String item: list) {
                    System.out.println(count + ". " + item);
                    count++;
                }
            } else {
                list.add(line);
                System.out.println("added: " + line);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }
}
