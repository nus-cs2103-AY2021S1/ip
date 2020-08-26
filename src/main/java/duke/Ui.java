package duke;

public class Ui {

    public static int TODO = 0, DEADLINE = 1, EVENT = 2;

    /**
     * Construct a Ui object
     */
    public Ui(){}

    /**
     * Prints the welcome message
     */
    public void showWelcomeMessage(){
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
    }

    /**
     * Prints the load file error
     */
    public void showLoadingError(){
        System.out.println("no ./data/duke.txt found");
    }

    /**
     * Prints the input empty error
     */
    public void showInputEmtyError(){
        System.out.println("____________________________________________________________");
        System.out.println("\uD83D\uDE43 OOPS!!! input cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the bye message
     */
    public void showByeMessage(){
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!" +
                "\n____________________________________________________________");
    }

    /**
     * Prints Task mark as done
     * @param task
     */
    public void showTaskMarkAsDone(Task task){
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
    }

    /**
     * Prints error when task number exceed TaskList size
     * @param taskNumber the specified task number
     * @param listSize the size of the TaskList
     */
    public void showTaskNumberExceed(int taskNumber, int listSize){
        System.out.println("no such task: task " + taskNumber + " as you only have " + listSize + " in total");
    }

    /**
     * Prints wrong format after the word "done" in done command
     */
    public void wrongFormatAfterDone(){
        System.out.println("\uD83D\uDE43 wrong input after the word \"done\"");
    }

    /**
     * Prints wrong format after the word "delete" in delete command
     */
    public void wrongFormatAfterDelete(){
        System.out.println("\uD83D\uDE43 wrong input after the word \"delete\"");
    }

    /**
     * Prints delete message
     * @param task the Task to be deleted
     * @param list the TaskList object
     */
    public void showDeleteMessage(Task task, TaskList list){
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", list.getSize()));
    }

    /**
     * Prints description empty message
     */
    public void descriptionEmpty(){
        System.out.println("____________________________________________________________");
        System.out.println("\uD83D\uDE43 OOPS!!! The description of a todo cannot be empty.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints message after add task
     * @param task the Task to be added
     * @param type the type of Task
     * @param listSize the size of TaskList
     */
    public void MessageAfterAdd(Task task, int type, int listSize){
        if(type == TODO){
            Todo newTodo = (Todo)task;
            System.out.println("____________________________________________________________\n" +
                    "Got it. I've added this task:\n" +
                    newTodo.toString() + "\n" +
                    String.format("Now you have %d tasks in the list.", listSize) +
                    "\n____________________________________________________________");
        } else if(type == DEADLINE){
            Deadline ddl = (Deadline)task;
            System.out.println("Got it. I've added this task:\n" +
                    ddl.toString() + "\n" +
                    String.format("Now you have %d tasks in the list.", listSize) +
                    "\n____________________________________________________________");
        } else{
            Event event = (Event)task;
            System.out.println("Got it. I've added this task:\n" +
                    event.toString() + "\n" +
                    String.format("Now you have %d tasks in the list.", listSize) +
                    "\n____________________________________________________________");
        }
    }

    /**
     * Prints wrong deadline format
     */
    public void wrongDeadlineFormat(){
        System.out.println("____________________________________________________________");
        System.out.println("\uD83D\uDE43 OOPS!!! The description and time of a deadline cannot be empty." +
                " Or maybe you used \"at\" instead of \"by\"?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints wrong event format
     */
    public void wrongEventFormat(){
        System.out.println("____________________________________________________________");
        System.out.println("\uD83D\uDE43 OOPS!!! The description and time of a event cannot be empty." +
                " Or maybe you used \"by\" instead of \"at\"?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints wrong date format
     */
    public void wrongDateFormat(){
        System.out.println("Wrong date input format. Correct format should be \"YYYY-MM-DD\"");
    }

    /**
     * Prints wrong time format
     */
    public void wrongTimeFormat(){
        System.out.println("Wrong time format. Correct format should be \"HH:MM\"");
    }

    /**
     * Prints no such command message
     */
    public void noSuchCommand(){
        System.out.println("\uD83D\uDE43 Sorry~ please specify whether this is a todo or a deadline or a event\n" +
                "put the word \"todo\" or \"deadline\" or \"event\" in front of your description");
    }
}
