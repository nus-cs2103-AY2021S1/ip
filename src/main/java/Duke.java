import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String seperator = "\t____________________________________________________________";
        System.out.println(seperator);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println(seperator);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String newLine = sc.nextLine();
            if(newLine.equals("bye")) {
                System.out.println(seperator);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(seperator);
                break;
            } else {
                System.out.println(seperator);
                System.out.println("\t"+newLine);
                System.out.println(seperator);
            }
        }
    }
}
