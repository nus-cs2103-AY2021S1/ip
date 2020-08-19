import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(
                "_______________________________"+
                "Hello! I'm Duke\n"+
                "What can I do for you?\n"+
                "_______________________________"
        );
        String input = scan.nextLine();
        while(!input.equals("bye")) {
            System.out.println(
                    "_______________________________\n" +
                            input + "\n" +
                            "_______________________________");
            input = scan.nextLine();

        }
        System.out.println("_______________________________\n"+
                "Bye. Hope to see you again soon!");

    }
}
