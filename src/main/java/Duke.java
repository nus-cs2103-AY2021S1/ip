import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________ \nHello! I'm Duke\nWhat can I do for you? \n____________________________________________________________");

        boolean isEnded = false;
        List taskList = new List();

        Scanner sc= new Scanner(System.in);

        while(!isEnded) {
            String userInput = sc.nextLine();

            if(userInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                isEnded = true;
            } else if (userInput.equals("list")) {
                taskList.printList();
            } else {
                taskList.addTask(userInput);
                System.out.println("added: " + userInput);
            }
            System.out.println("____________________________________________________________");
        }


    }
}
