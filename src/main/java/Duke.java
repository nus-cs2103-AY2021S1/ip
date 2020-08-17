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

        Scanner sc = new Scanner(System.in);
        System.out.println(" ____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(" ____________________________________________________________");
        ArrayList<Task> stringStore = new ArrayList<>();
        String input = sc.nextLine();
        while(!input.equals("bye")){
            if(input.equals("list")) {
                System.out.println(" ____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                int sizeStore = stringStore.size();
                for (int i = 1; i < sizeStore + 1; i++) {
                    System.out.println(i + "." + stringStore.get(i - 1));
                }
                System.out.println(" ____________________________________________________________");
            } else if (input.split(" ")[0].equals("done")){
                stringStore.get(Integer.parseInt(input.split(" ")[1]) - 1).markAsDone();
            } else {
                System.out.println(" ____________________________________________________________");
                String actual = "";
                System.out.println("added: " + input);
                Task a = new Task(input);
                System.out.println(" ____________________________________________________________");
                System.out.println("");
                stringStore.add(a);
            }
            input =  sc.nextLine();

        }
        System.out.println(" ____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(" ____________________________________________________________");

    }
}
