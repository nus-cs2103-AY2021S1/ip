import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    //Characteristics of Duke
    private boolean isChatting;
    private ArrayList<String> todoList;

    //Editable Strings for system messages
    private static String greeting = "Hello! I'm Duke\n What can I do for you?";
    private static String parting = "Bye. Hope to see you again soon!";
    private static String error_msg = "Please enter an appropriate command!";
    private static String add_to_list = "added: ";

    //Constructor
    Duke() {
        //Characteristic of Duke
        this.isChatting = true;
        this.todoList = new ArrayList<>();
    }

    //Initialise Duke
    private void startChat() {
        //Initialisation Message
        System.out.println(greeting);

        //Initialise scanner to prompt user
        Scanner sc = new Scanner(System.in);

        //Duke runs until user inputs "bye"
        while (this.isChatting) {
            //Obtain user input
            String user_input = sc.nextLine();

            //Conditionals for user inputs
            //Return toDoList
            if (user_input.equals("list")) {
                System.out.println(getToDoList());
            }
            //Random Command
            else if (user_input.equals("blah")) {
                System.out.println(user_input);
            }
            //Exit Duke
            else if (user_input.equals("bye")) {
                System.out.println(parting);
                this.isChatting = false;
            }
            //Add to todoList
            else {
                this.todoList.add(user_input);
                System.out.println(add_to_list + user_input);
            }
        }
    }

    //Converts the to do arraylist into a labelled list
    private String getToDoList() {
        String output ="";
        for (int i = 0 ; i < todoList.size() ; i++) {
            if (i == todoList.size() - 1) {
                output = output + (i + 1) + ". " + todoList.get(i);
            } else {
                output = output + (i + 1) + ". " + todoList.get(i) + "\n";
            }
        }
        return output;
    }

    public static void main(String[] args) {
        //Initialise Duke
        Duke chatBot = new Duke();
        chatBot.startChat();
    }
}
