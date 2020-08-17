import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String command = sc.nextLine();
        ArrayList<String> list = new ArrayList<>();

        while(!command.equals("bye")) {
            if(command.equals("list")){
                System.out.println("    ____________________________________________________________");
                Integer i = 1;
                for(String s : list) {
                    System.out.println("    " + i.toString() + ". " + s);
                    i++;
                }
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + command);
                list.add(command);
                System.out.println("    ____________________________________________________________");
            }
            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
