import java.util.Scanner;

public class Dobby {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String ln = "______________________________________";
        System.out.println(ln);
        System.out.println("Hello! I'm Dobby\nHow can I help you?");
        System.out.println(ln);

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String text = scanner.nextLine();

            if (text.equalsIgnoreCase("bye")) {
                scanner.close();
                System.out.println(ln + "\nGoodbye. Hope to see you again soon!\n" + ln);
                System.exit(0);
            } else {
                System.out.println(ln + "\n" + text + "\n" + ln);
            }
        }
    }
}
