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
                if (tempArray[0].equals("todo")) {
                    Todo newTodo = new Todo(echo.substring(5));
                    listOfItems.add(newTodo);
                    System.out.println("Got it. I've added this task:\n" + newTodo.toString() +
                            "\nNow you have " + listOfItems.size() + " tasks in total");
                }
                if (tempArray[0].equals("deadline")) {
                    String[] tempString = echo.substring(9).split(" /by");
                    Deadline newDeadline = new Deadline(tempString[0], tempString[1]);
                    listOfItems.add(newDeadline);
                    System.out.println("Got it. I've added this task:\n" + newDeadline.toString()
                            + "\nNow you have " + listOfItems.size() + " tasks in total");
                }
                if (tempArray[0].equals("event")) {
                    String[] tempString = echo.substring(7).split(" /at");
                    Event newEvent = new Event(tempString[0], tempString[1]);
                    listOfItems.add(newEvent);
                    System.out.println("Got it. I've added this task:\n" + newEvent.toString()
                            + "\nNow you have " + listOfItems.size() + " tasks in total");
                }

            }
            echo = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
