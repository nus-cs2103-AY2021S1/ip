import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "\nHello! I'm Duke" + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        boolean boo = true;
        while (boo) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                boo = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else {
                System.out.println(str);
            }
        }
        sc.close();
    }
}
