import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);



        Scanner sc = new Scanner (System.in);
        ArrayList<String> Todo = new ArrayList<>();

        //Greeting the user
        System.out.println ("Hello! I'm Duke");
        System.out.println ("What can I do for you?");

        String next = sc.nextLine();
        while (!next.equals("bye")){
            if (next.equals("list")){
                for (int i = 0; i < Todo.size(); i++){
                    System.out.printf("%d. %s \n", i + 1, Todo.get(i));
                }
            } else {
                Todo.add(next);
                System.out.printf("added: %s \n", next);
            }
            next = sc.nextLine();
        }

        //Exit
        System.out.println ("Bye. Hope to see your again soon!");
    }
}
