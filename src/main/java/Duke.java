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
        ArrayList<String> stringStore = new ArrayList<>();
        String input = sc.nextLine();
        while(!input.equals("bye")){
            if(input.equals("list")){
                System.out.println(" ____________________________________________________________");
                int sizeStore = stringStore.size();
                for (int i = 1; i < sizeStore + 1 ; i++) {
                    System.out.println(i +". " + stringStore.get(i-1));
                }
                System.out.println(" ____________________________________________________________");
            } else {
                System.out.println(" ____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println(" ____________________________________________________________");
                System.out.println("");
                stringStore.add(input);
            }
            input =  sc.nextLine();

        }
        System.out.println(" ____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(" ____________________________________________________________");

    }
}
