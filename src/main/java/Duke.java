import java.util.Scanner;

public class Duke {
    public static void line(){
        for (int i = 0; i < 50; i++) {
            System.out.print("\u2500");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm duke. What can I do for you? \n" + logo);
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    while(!str.equals("bye")){
        line();
        System.out.println(str);
        line();
        str = sc.nextLine();
    }
    System.out.println("Bye. Hope to see you again soon!");
    sc.close();
    }
}
