import java.util.*;
/*Level 1*/
/*Need Fixing */
public class Duke {
    private static final List<String> ListofMessages  = new ArrayList<>();

    public static void Openingmessage() { /*Displays message when chatbot starts up*/
        System.out.println("Hello! I'm Duke What can I do for you?");
    }

    public static void DisplayListOfMessages() {
        int LengthOfArrayList;
        LengthOfArrayList = ListofMessages.size();
        for (int i = 0; i < LengthOfArrayList; i++){
            int NumberToDisplay = i + 1;
            String finalmessage = NumberToDisplay + "." + ListofMessages.get(i);
            System.out.println(finalmessage);
        }
    }

    public static void main(String[] args){
        Openingmessage(); /*Opening Message*/
        Scanner input = new Scanner(System.in);


        while (true) {
            String message = input.nextLine();

            String ToShowMessagesInArrayList = "list";
            if (message.equals(ToShowMessagesInArrayList)) {
                DisplayListOfMessages();
            }

            String ToExit = "bye";
            if (message.equals(ToExit)){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (!(message.equals("list"))) {
                ListofMessages.add(message);/*Stores message in ArrayList if it is not a command*/
                String ItemAdded = "added: " + message;
                System.out.println(ItemAdded);
            }
        }
    }
}