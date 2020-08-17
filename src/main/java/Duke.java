import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    //Characteristics of Duke
    private boolean isChatting;
    private ArrayList<Task> todoList;

    //Editable Strings for system messages
    private static String greeting = "Hello! I'm Duke\n What can I do for you?";
    private static String parting = "Bye. Hope to see you again soon!";
    private static String inappropriateCommandMsg = "Please enter an appropriate command!";
    private static String add_to_list = "added: ";
    private static String getListMsg = "Here are the tasks in your list:";
    private static String markDoneMsg = "Nice! I've marked this task as done: ";

    //Constructor
    public Duke() {
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
            //list command
            if (user_input.equals("list")) {
                System.out.println(getToDoList());
            }
            //Random Command
            else if (user_input.equals("blah")) {
                System.out.println(user_input);
            }
            //bye command
            else if (user_input.equals("bye")) {
                System.out.println(parting);
                this.isChatting = false;
            }
            //add command
            else  if (!user_input.contains("done")){
                Task toAdd = new Task(user_input);
                this.todoList.add(toAdd);
                System.out.println(add_to_list + user_input);
            }
            //done command
            else {
                markTaskDone(user_input);
            }
        }
    }

    //Converts the to do arraylist into a labelled list
    private String getToDoList() {
        String output ="";
        for (int i = 0 ; i < todoList.size() ; i++) {
            Task currentTask = todoList.get(i);
            if (i == todoList.size() - 1) {
                output = output + (i + 1) + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription();
            } else {
                output = output + (i + 1) + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + "\n";
            }
        }
        System.out.println(getListMsg);
        return output;
    }

    //Done command
    //Check if user input done keyword is located in the correct place
    //Then, check if user input contains apt integer
    private void markTaskDone(String user_input) {
        //Error Messages to guide user how to correct incorrect input
        String outOfBoundsMsg = "That item does not exist in your list! The input number has exceeded your list size!";
        String numberFormatMsg = "Please key in an appropriate number!";

        //Checking if done keyword is located in the correct place
        if (user_input.substring(0, 4).contains("done")) {
            String int_substring = user_input.substring(5);
            try {
                int int_substring_converted = Integer.parseInt(int_substring);
                Task currentTask = this.todoList.get(int_substring_converted - 1);
                currentTask.markAsDone();
                System.out.println(markDoneMsg);
                System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
            } catch (NumberFormatException ex){
                System.out.println(numberFormatMsg);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println(outOfBoundsMsg);
            }
        } else {
            System.out.println(inappropriateCommandMsg);
        }
    }

    public static void main(String[] args) {
        //Initialise Duke
        Duke chatBot = new Duke();
        //Start chatting with Bot
        chatBot.startChat();
    }
}
