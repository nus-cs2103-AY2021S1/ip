import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // print all the content in the list
    public static void printList(ArrayList<String> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + ". " + list.get(i));
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
        ArrayList<String> list = new ArrayList<>();
        String command;
        Scanner sc = new Scanner(System.in);
        while(true){
            command = sc.nextLine();
            if(command.equals("bye")){
                break;
            }
            else if(command.equals("list")){
                System.out.println("____________________________________________________________");
                printList(list);
                System.out.println("____________________________________________________________");
            }
            else{
                list.add(command);
                System.out.println("____________________________________________________________\n" +
                               "added: " + command +
                               "\n____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________\n" +
                           "Bye. Hope to see you again soon!" +
                           "\n____________________________________________________________");
    }
}
