import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String str;
        str = sc.nextLine();
        while (!str.equals("bye")){
            System.out.println("-----------------");
            System.out.println(str);
            System.out.println("-----------------");
            str = sc.nextLine();
        }
        System.out.println("-----------------");
        System.out.println("Bye. Hope to see you again!");
        System.out.println("-----------------");
    }
}
