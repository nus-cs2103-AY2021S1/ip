import java.util.*;
/*Level 1*/
/*Need Fixing */
public class Duke {
    public static void main(String[] args){
        System.out.println("Hello! I'm Duke What can I do for you?");
        Scanner input = new Scanner(System.in);

        while (true) {
            String message = input.nextLine();
            String ToExit = "bye";
            if (message.equals(ToExit)){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(message);
        }
    }
}