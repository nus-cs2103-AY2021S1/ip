import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {

        String line = "____________________________________________________________";
        String introduction = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(line);
        System.out.println(introduction);
        System.out.println(line);

        ArrayList<Task> ListOfItems = new ArrayList<Task>();
        boolean flag = false;
        String echo;
        Scanner sc = new Scanner(System.in);
        echo = sc.nextLine();
        while (flag == false) {


            if (echo.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                flag = true;


            } else if (echo.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
            for (int i = 0; i< ListOfItems.size(); i++) {
                int number = i+1;
                System.out.println(Integer.toString(number) + ". " + ListOfItems.get(i));

            }
                System.out.println(line);
                echo = sc.nextLine();
            } else {

                String split = echo;
                String arr[] = split.split(" ", 2);
                if (arr[0].equals("done")) {
                    int index = Integer.parseInt(arr[1]) - 1;
                    Task taskToChange = ListOfItems.get(index);
                    taskToChange.markAsDone();
                    ListOfItems.set(index, taskToChange);
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done: \n" + ListOfItems.get(index));
                    System.out.println(line);

                    echo = sc.nextLine();
                } else {
                    Task item = new Task(echo);
                    ListOfItems.add(item);

                    System.out.println(line);
                    System.out.println("added: " + item.description);
                    System.out.println(line);

                    echo = sc.nextLine();

                }
            }
        }










    }


}
