import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        ArrayList<String> store = new ArrayList<>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        int count = 1;
        while(echo.equals("bye") == false){
            if(echo.equals("list")){
                for(String i : store){
                    System.out.println(i);
                }
            }
            else{
                System.out.println("added: " +echo);
                echo = count + ". " + echo;
                store.add(echo);
                count++;
            }
            echo = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
