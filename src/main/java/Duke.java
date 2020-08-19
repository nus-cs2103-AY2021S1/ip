import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // print all the content in the list
    public static void printList(ArrayList<Task> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + list.get(i).toString());
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // print greetings of chatbot
        System.out.println("____________________________________________________________\n" +
                           "Hello! I'm Duke\n" +
                           "What can I do for you?" +
                           "\n____________________________________________________________");

        // add command entered by the user to the list
        ArrayList<Task> list = new ArrayList<>();
        String inputCommand;
        String[] command;
        int ptr;
        Scanner sc = new Scanner(System.in);
        while(true){
            inputCommand = sc.nextLine();
            command = inputCommand.split(" ");
            ptr = 0;

            // if the user input is empty, continue the loop
            if(command.length <= 0 || inputCommand.equals("")){
                continue;
            }

            while(/*ptr < command.length && */command[ptr].equals("")) ptr++;

            if(command[ptr].equals("bye")){
                break;
            }
            else if(command[ptr].equals("list")){
                System.out.println("____________________________________________________________");
                printList(list);
                System.out.println("____________________________________________________________");
            }
            else if(command[ptr].equals("done")){
                int taskNumber = Integer.parseInt(command[ptr + 1]);
                if(taskNumber > list.size()){
                    System.out.println("no such task: task" + taskNumber + " as you only have " + list.size() + " in total");
                }
                else{
                    Task task = list.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
                }
            }
            else{
                list.add(new Task(inputCommand));
                System.out.println("____________________________________________________________\n" +
                               "added: " + inputCommand +
                               "\n____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________\n" +
                           "Bye. Hope to see you again soon!" +
                           "\n____________________________________________________________");
    }
}
