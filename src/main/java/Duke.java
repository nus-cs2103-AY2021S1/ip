import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String input = sc.nextLine();
        while(!input.equals("bye")){
            System.out.println(" ____________________________________________________________");
            System.out.println(input);
            System.out.println(" ____________________________________________________________");
            System.out.println("");
            input =  sc.nextLine();

        }
        System.out.println(" ____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(" ____________________________________________________________");

    }
}
