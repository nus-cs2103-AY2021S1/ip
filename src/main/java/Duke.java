import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        String line = "____________________________________________________________";
        String introduction = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(line);
        System.out.println(introduction);
        System.out.println(line);

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
            } else {


                System.out.println(line);
                System.out.println(echo);
                System.out.println(line);

                echo = sc.nextLine();


            }
        }










    }


}
