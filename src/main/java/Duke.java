import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    //Characteristics of Duke
    private boolean isChatting;
    private ArrayList<Task> taskList;

    //Constants Command Enum
    enum Command {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DONE,
        BYE,
        INAPPROPRIATE
    }

    //Constructor
    public Duke() {
        //Characteristic of Duke
        this.isChatting = true;
        this.taskList = new ArrayList<>();
    }

    //Initialise Duke
    private void startChat() {
        //Initialisation Message
        String greeting = "Hello! I'm Duke\n What can I do for you?";
        System.out.println(greeting);

        //Initialise scanner to prompt user
        Scanner sc = new Scanner(System.in);

        //Duke runs until user inputs "bye"
        while (this.isChatting) {
            //Obtain user input
            String user_input = sc.nextLine();

            //Obtain command keyword base on user_input
            Command user_command = getCommand(user_input.trim()); //trim to get rid of unnecessary whitespaces

            //Conditionals based on command keyword
            //list command
            if (user_command.equals(Command.LIST)) {
                System.out.println(getToDoList());
            }
            //bye command
            else if (user_command.equals(Command.BYE)) {
                String parting = "Bye. Hope to see you again soon!";
                System.out.println(parting);
                this.isChatting = false;
            }
            //to do command
            else if (user_command.equals(Command.TODO)) {
                addToDo(user_input);
            }
            //deadline command
            else if (user_command.equals(Command.DEADLINE)) {
                addDeadline(user_input);
            }
            //event command
            else if (user_command.equals(Command.EVENT)) {
                addEvent(user_input);
            }
            //done command
            else if (user_command.equals(Command.DONE)) {
                markTaskDone(user_input);
            }
            //Unknown command
            else if (user_command.equals(Command.INAPPROPRIATE)) {
                String inappropriateCommandMsg = "Please enter an appropriate command!";
                System.out.println(inappropriateCommandMsg);
            }
            //To catch inappropriate commands that have not been identified
            else {
                System.out.println("Error Detected");
            }
        }
    }

    //Converts the tasklist arraylist into a labelled list message
    private String getToDoList() {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (i == taskList.size() - 1) {
                output = output + (i + 1) + "." + currentTask;
            } else {
                output = output + (i + 1) + "." + currentTask + "\n";
            }
        }
        String getListMsg = "Here are the tasks in your list:";
        System.out.println(getListMsg);
        return output;
    }

    private String getTotalTasksMsg() {
        return "Now you have " + this.taskList.size() + " tasks in the list.";
    }

    //Done command
    //Check if user input done keyword is located in the correct place
    //Then, check if user input contains apt integer
    private void markTaskDone(String user_input) {
        //Error Messages to guide user how to correct incorrect input(To change into exceptions maybe)
        String outOfBoundsMsg = "That item does not exist in your list! The input number has exceeded your list size!";
        String numberFormatMsg = "Please key in an appropriate number!";

        //Get number after done keyword
        String int_substring = user_input.substring(5);
        try {
            int int_substring_converted = Integer.parseInt(int_substring);
            Task currentTask = this.taskList.get(int_substring_converted - 1);
            currentTask.markAsDone();
            String markDoneMsg = "Nice! I've marked this task as done:";
            System.out.println(markDoneMsg);
            System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
        } catch (NumberFormatException ex) {
            System.out.println(numberFormatMsg);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(outOfBoundsMsg);
        }
    }

    //Adds a To Do task to the task list
    private void addToDo(String user_input) {
        ToDo newTask = new ToDo(user_input.substring(5));
        this.taskList.add(newTask);
        addedToListMsg();
        System.out.println("\t" + newTask);
        System.out.println(getTotalTasksMsg());
    }

    //Adds a Deadline task to the task list
    private void addDeadline(String user_input) {
        String dateTime = getDateTime(user_input);
        if (dateTime != null) {
            Deadline newTask = new Deadline(user_input.substring(9, user_input.indexOf("/")), dateTime);
            this.taskList.add(newTask);
            addedToListMsg();
            System.out.println("\t" + newTask);
            System.out.println(getTotalTasksMsg());
        } else {
            String inappropriateDeadlineMsg = "Please enter an appropriate deadline.";
            System.out.println(inappropriateDeadlineMsg);
            return;
        }
    }

    //Adds an Event task to the task list
    private void addEvent(String user_input) {
        String dateTime = getDateTime(user_input);
        if (dateTime != null) {
            Event newTask = new Event(user_input.substring(6, user_input.indexOf("/")), dateTime);
            this.taskList.add(newTask);
            addedToListMsg();
            System.out.println("\t" + newTask);
            System.out.println(getTotalTasksMsg());
        } else {
            String inappropriateEventMsg = "Please enter an appropriate event.";
            System.out.println(inappropriateEventMsg);
            return;
        }
    }

    //To print out add to list message
    private void addedToListMsg() {
        String add_to_listMsg = "Got it. I've added this task:";
        System.out.println(add_to_listMsg);
    }

    //To obtain date and time that follows a /by or /at
    private String getDateTime(String user_input) {
        int slashIndex = user_input.indexOf("/");

        //Since by and at have the same number of characters, we can just +3 here
        //To be change if different commands other than by or at are added
        if (slashIndex != -1) {
            return user_input.substring(slashIndex+ 3);
        } else {
            return null;
        }
    }

    //To obtain first keyword of user input
    private Command getCommand(String user_input) {
        //For commands that have text following a keyword
        if (user_input.contains(" ")) {
            int indexOfFirstSpace = user_input.indexOf(' ');
            if (indexOfFirstSpace == 4) {
                if (user_input.substring(0, 4).equals("todo")) {
                    return Command.TODO;
                } else if (user_input.substring(0, 4).equals("done")) {
                    return Command.DONE;
                } else {
                    return Command.INAPPROPRIATE;
                }
            } else if (indexOfFirstSpace == 5) {
                if (user_input.substring(0, 5).equals("event")) {
                    return Command.EVENT;
                } else {
                    return Command.INAPPROPRIATE;
                }
            } else if (indexOfFirstSpace == 8){
                if (user_input.substring(0, 8).equals("deadline")) {
                    return Command.DEADLINE;
                } else {
                    return Command.INAPPROPRIATE;
                }
            } else {
                return Command.INAPPROPRIATE;
            }
        }
        //For commands that do not have any text after keyword
        else {
            if (user_input.equals("list")) {
                return Command.LIST;
            } else if (user_input.equals("bye")) {
                return Command.BYE;
            } else {
                return Command.INAPPROPRIATE;
            }
        }
    }

    public static void main(String[] args) {
        //Initialise Duke
        Duke chatBot = new Duke();
        //Start chatting with Bot
        chatBot.startChat();
    }
}
