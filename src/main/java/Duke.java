import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n"+"What can I do for you?");
        ArrayList<String> listOfItems = new ArrayList<String>();
        String echo= sc.nextLine();
        while(!echo.equals("bye")) {
            if(!echo.equals("list")) {
                System.out.println("added: " + echo);
                listOfItems.add(echo);
            }else {
                int iterator = 1;
                for(String s : listOfItems){
                    System.out.println(iterator + ". " + s);
                    iterator++;
                }
            }
            echo = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
