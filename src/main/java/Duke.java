import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")){
                break;
            }
            System.out.println("\n" + command);
        }
        System.out.println("Goodbye! See you again soon!");
    }
}
