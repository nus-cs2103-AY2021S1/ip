import java.util.*;
/*Level 1*/
/*Need Fixing */
public class Duke {
    /*testing branch*/
    private static final List<Task> ListofMessages  = new ArrayList<>();

    public static void Openingmessage() { /*Displays message when chatbot starts up*/
        System.out.println("Hello! I'm Duke What can I do for you?");
    }

    public static void DisplayListOfMessages() {
        int LengthOfArrayList;
        LengthOfArrayList = ListofMessages.size();
        for (int i = 0; i < LengthOfArrayList; i++){
            int NumberToDisplay = i + 1;
            String finalmessage = NumberToDisplay + ".[" + ((ListofMessages.get(i)).getStatusIcon())+"]" + " "+((ListofMessages.get(i)).getTask());
            System.out.println(finalmessage);
        }
    }

    public static void main(String[] args){
        Openingmessage(); /*Opening Message*/
        Scanner input = new Scanner(System.in);
        int tasktobedone;


        while (true) {

            String message = input.next();

            //Check if the message is done
            String CheckIfTaskIsDone = "done";
            if(message.equals(CheckIfTaskIsDone)){
                tasktobedone = (input.nextInt()) - 1;
                String TheTask = (ListofMessages.get(tasktobedone)).getTask();
                Task ToReplace = new Task(TheTask,tasktobedone);
                ListofMessages.set(tasktobedone,ToReplace);
                System.out.println("Nice! I've marked this task as done");
                String ToBeprinted = ToReplace.getStatusIcon();
                ToBeprinted = "[" + ToBeprinted + "]" + ToReplace.getTask();
                System.out.println(ToBeprinted);


            }

            //The message is not done then we will concetenate the strings
            String remainingmessage = input.nextLine();
            message = message + remainingmessage;

            //Command is list
            String ToShowMessagesInArrayList = "list";
            if (message.equals(ToShowMessagesInArrayList)) {
                DisplayListOfMessages();
            }

            //Command is Exit
            String ToExit = "bye";
            if (message.equals(ToExit)){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            //Make sure the message is not the same as any content
            if (!(message.equals("list")) && (!(message.equals(CheckIfTaskIsDone)))) {
                Task t = new Task(message);
                ListofMessages.add(t);/*Stores message in ArrayList if it is not a command*/
                String ItemAdded = "added: " + message;
                System.out.println(ItemAdded);
            }





        }
    }
}