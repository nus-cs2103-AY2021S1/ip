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
            System.out.println("____________________________________________________________");
            String[] parsedInput = userInput.split(" ", 2);

            if (parsedInput[0].equals("todo")) {
                taskList.addTodo(parsedInput[1]);

            } else if (parsedInput[0].equals("deadline")) {
                String[] furtherParsed = parsedInput[1].split(" /by ", 2);
                taskList.addDeadline(furtherParsed[0], furtherParsed[1]);
            } else if (parsedInput[0].equals("event")){
                String[] furtherParsed = parsedInput[1].split(" /at ",2);
                taskList.addEvent(furtherParsed[0],furtherParsed[1]);
            } else if(userInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                isEnded = true;
            } else if (userInput.equals("list")){
                taskList.printList();
            } else if (parsedInput[0].equals("done")) {
                int itemRank = Integer.parseInt(parsedInput[1]);
                taskList.updateTaskStatus(itemRank);
            } else {
                System.out.println("Please give an appropriate response.");
            }
            System.out.println("____________________________________________________________");
        }


    }
}
