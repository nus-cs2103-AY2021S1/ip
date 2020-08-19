import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n"+"What can I do for you?");
        ArrayList<Task> listOfItems = new ArrayList<Task>();
        String echo= sc.nextLine();
        while(!echo.equals("bye")) {
            String [] tempArray = echo.split(" ");
            if(echo.equals("list")) {
                int iterator = 1;
                System.out.println("Here are the tasks in your list:");
                for(Task s : listOfItems){
                    System.out.println(iterator + "." + s.toString());
                    iterator++;
                }
            }

            else if (tempArray[0].equals("done")) {
                int index = Integer.parseInt(tempArray[1]) - 1;
                listOfItems.get(index).markDone();
                System.out.println("Nice! I've marked this task as done: \n" + listOfItems.get(index).toString());
            }

            else {
                System.out.println("added: " + echo);
                listOfItems.add(new Task(echo));
            }
            echo = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
