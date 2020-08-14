import java.util.Scanner;

public class Duke {
    public final static String LINE = "*********************************************************";

    public static void main(String[] args) {
        Welcome();
        Scanner sc = new Scanner(System.in);
        Repeater(sc);
        sc.close();
    }

    public static void Welcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE);
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you pal? :D");
        System.out.println(LINE);

    }


    public static void Repeater(Scanner sc){
        while(true){
            String response = sc.nextLine();
            System.out.println(LINE);
            if(response.equals("bye")){
                System.out.println("CYA PAL. Hope to see you again!");
                System.out.println(LINE);
                break;
            } else {
                System.out.println(response);
                System.out.println(LINE);
            }
        }
    }
}
