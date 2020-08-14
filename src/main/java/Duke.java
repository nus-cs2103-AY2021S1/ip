import java.util.Scanner;

public class Duke {
    static void printDialog(String content) {
        System.out.println("    ----------------------------------------");
        System.out.println("    " + content + "\n");
        System.out.println("    ----------------------------------------");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDialog("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String content = sc.next();
            if(content.equals("bye")) {
                printDialog("Bye. Hope to see you again soon!");
                break;
            }
            printDialog(content);
        }
    }
}
