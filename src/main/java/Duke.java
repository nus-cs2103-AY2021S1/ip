import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);



        Scanner sc = new Scanner (System.in);

        //Greeting the user
        System.out.println ("Hello! I'm Duke");
        System.out.println ("What can I do for you?");

        String next = sc.nextLine();
        while (!next.equals("bye")){
            System.out.println (next);
            next = sc.nextLine();
        }

        System.out.println ("Bye. Hope to see your again soon!");
    }
}
