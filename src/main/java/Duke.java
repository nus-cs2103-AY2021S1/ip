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

        ArrayList<String> ListOfItems = new ArrayList<String>();
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
            for (int i = 0; i< ListOfItems.size(); i++) {
                int number = i+1;
                System.out.println(Integer.toString(number) + ". " + ListOfItems.get(i));

            }
                System.out.println(line);
                echo = sc.nextLine();
            } else {

                ListOfItems.add(echo);

                System.out.println(line);
                System.out.println("added: " + echo);
                System.out.println(line);

                echo = sc.nextLine();


            }
        }










    }


}
