import jdk.jfr.Event;

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
        for (int i = 0; i < LengthOfArrayList; i++) {
            int NumberToDisplay = i + 1;

            if (ListofMessages.get(i) instanceof ToDos) {
                String AdditemMessage = "[T]";
                String finalmessage = NumberToDisplay +   "."  +  AdditemMessage +   "[" + ((ListofMessages.get(i)).getStatusIcon()) + "]" + ((ListofMessages.get(i)).getTask());
                System.out.println(finalmessage);

            }else if (ListofMessages.get(i) instanceof Deadlines) {
                String ToManipulate = ListofMessages.get(i).getStatusIcon();
                String ItemType = ((Deadlines) ListofMessages.get(i)).getItem();
                String GetDateLine = ((Deadlines) ListofMessages.get(i)).getDeadLine();//Type casting
                String TaskName = ((Deadlines) ListofMessages.get(i)).getDeadLineTask();
                String finalmessage = NumberToDisplay + "." + ItemType + "[" + ListofMessages.get(i).getStatusIcon() + "] " + TaskName + "(by: " + GetDateLine + ")";
                System.out.println(finalmessage);
            }else if (ListofMessages.get(i) instanceof Events) {
                String ToManipulate = ListofMessages.get(i).getStatusIcon();
                String ItemType = ((Events) ListofMessages.get(i)).getItem();
                String GetDateLine = ((Events) ListofMessages.get(i)).getEvent();
                String TaskName = ((Events) ListofMessages.get(i)).getEventsDescription();
                String finalmessage = NumberToDisplay + "." + ItemType + "[" + ListofMessages.get(i).getStatusIcon() + "] " + TaskName + "(at: " + GetDateLine + ")";
                System.out.println(finalmessage);

            } else {
                String finalmessage = NumberToDisplay + ".[" + ((ListofMessages.get(i)).getStatusIcon()) + "]" + " " + ((ListofMessages.get(i)).getTask());
                System.out.println(finalmessage);
            }
        }
    }

    public static String NumberOfItemsInList() {
        int numberOfItems = ListofMessages.size();
        String NumberOfItems = "Now you have " + numberOfItems + " tasks in the list.";
        return NumberOfItems;
    }

    public static void main(String[] args){
        Openingmessage(); /*Opening Message*/
        Scanner input = new Scanner(System.in);
        int tasktobedone;


        while (true) {

            String message = input.next();

            String ToDoItem = "todo";
            if (message.equals(ToDoItem)) {
                String remainingmessage = input.nextLine();
                ToDos t = new ToDos(remainingmessage);
                ListofMessages.add(t);
                String GotIt = "Got it. I've added this task: ";
                String DisplayItem = t.getItem();
                String getStatus = t.getStatusIcon();
                String DisplayItemWithTask = "  "  +  DisplayItem +"[" + getStatus + "]" + remainingmessage;
                System.out.println(GotIt);
                System.out.println(DisplayItemWithTask);
                System.out.println(NumberOfItemsInList());

            }

            //Deadline item
            String DeadLineItem = "deadline";
            if (message.equals(DeadLineItem)) {
                String remainingmessage = input.nextLine();

                int i = 0;
                int lengthofremainingmessage = remainingmessage.length();
                //Locate the / icon
                while (true) {
                    if(remainingmessage.charAt(i) == '/'){
                        break;
                    }
                    i = i + 1;
                }

                String firsthalf = remainingmessage.substring(1,i);
                String secondhalf = remainingmessage.substring (i + 4,lengthofremainingmessage);

                Deadlines t = new Deadlines(firsthalf,secondhalf);
                ListofMessages.add(t);
                String GotIt = "Got it. I've added this task: ";
                System.out.println(GotIt);
                //Display item with task
                String DisplayItem = "  " + t.getItem();
                String getStatus = t.getStatusIcon();
                getStatus = "[" + getStatus + "] ";
                String DisplayItemWithTask = DisplayItem + getStatus + firsthalf + "(" + "by: " + secondhalf + ")" ;
                System.out.println(DisplayItemWithTask);
                System.out.println(NumberOfItemsInList());
            }

            //For events
            String Eventitem = "event";
            if (message.equals(Eventitem)) {
                String remainingmessage = input.nextLine();

                int i = 0;
                int lengthofremainingmessage = remainingmessage.length();
                //Locate the / icon
                while (true) {
                    if(remainingmessage.charAt(i) == '/'){
                        break;
                    }
                    i = i + 1;
                }

                String firsthalf = remainingmessage.substring(1,i);
                String secondhalf = remainingmessage.substring (i + 4,lengthofremainingmessage);

                Events t = new Events(firsthalf,secondhalf);
                ListofMessages.add(t);
                String GotIt = "Got it. I've added this task: ";
                System.out.println(GotIt);
                //Display item with task
                String DisplayItem = "  " + t.getItem();
                String getStatus = t.getStatusIcon();
                getStatus = "[" + getStatus + "] ";
                String DisplayItemWithTask = DisplayItem + getStatus + firsthalf + "(" + "at: " + secondhalf + ")" ;
                System.out.println(DisplayItemWithTask);
                System.out.println(NumberOfItemsInList());


            }

            //String remainingmessage = input.nextLine();
            //message = message + remainingmessage;
            //This is for the earlier task for now we will remove it

            //Command is list
            String ToShowMessagesInArrayList = "list";
            if (message.equals(ToShowMessagesInArrayList)) {
                DisplayListOfMessages();
            }

            //Check if the message is done
            String CheckIfTaskIsDone = "done";
            if(message.equals(CheckIfTaskIsDone)){
                tasktobedone = (input.nextInt()) - 1;

                Task TheTask = (ListofMessages.get(tasktobedone));
                //Task ToReplace = new Task(TheTask,tasktobedone);
                //ListofMessages.set(tasktobedone,ToReplace);
          
                if(TheTask instanceof ToDos){
                    TheTask.setStatus();
                    ListofMessages.set(tasktobedone,TheTask);
                    System.out.println("Nice! I've marked this task as done");
                    String ToBeprinted = TheTask.getStatusIcon();
                    ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
                    System.out.println(ToBeprinted);
                }else if(TheTask instanceof Deadlines){
                    TheTask.setStatus();
                    ListofMessages.set(tasktobedone,TheTask);
                    System.out.println("Nice! I've marked this task as done");
                    String ToBeprinted = TheTask.getStatusIcon();
                 //   String ToAdd = ((Deadlines) ListofMessages.get(tasktobedone)).getDeadLineTask();
                    ToBeprinted = "[" + ToBeprinted + "]"  + TheTask.getTask();
                    System.out.println(ToBeprinted);
                }else{
                    TheTask.setStatus();
                    ListofMessages.set(tasktobedone,TheTask);
                    System.out.println("Nice! I've marked this task as done");
                    String ToBeprinted = TheTask.getStatusIcon();
                    String ToAdd = ((Events) ListofMessages.get(tasktobedone)).getEventsDescription();
                //    System.out.println("hello world");
                   // System.out.println(ToAdd);
                    ToBeprinted = "[" + ToBeprinted + "]" + TheTask.getTask();
                    System.out.println(ToBeprinted);
                }

//                System.out.println("Nice! I've marked this task as done");
//                String ToBeprinted = ToReplace.getStatusIcon();
//                ToBeprinted = "[" + ToBeprinted + "]" + ToReplace.getTask();
//                System.out.println(ToBeprinted);
            }














            //Command is Exit
            String ToExit = "bye";
            if (message.equals(ToExit)){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            //Not needed for now used in earlier task
            //Make sure the message is not the same as any content
//            if (!(message.equals("list")) && (!(message.equals(CheckIfTaskIsDone))) && (!(message.equals(ToDoItem)))) {
//                Task t = new Task(message);
//                ListofMessages.add(t);/*Stores message in ArrayList if it is not a command*/
//                String ItemAdded = "added: " + message;
//                System.out.println(ItemAdded);
//            }


        }
    }
}