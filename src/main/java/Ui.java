public class Ui {

    //print welcome message
    public void printWelcomeMessage() {
        String emoji = Emoji.CHICKEN.toString();
        String welcomeMessage = "    ____________________________________________________________\n"
                + "    Hello! I'm ByteMe " + emoji + emoji + emoji + "\n"
                + "    What can I do for you? (Don't bite me!)\n"
                + "    ____________________________________________________________\n";

        System.out.println(welcomeMessage);
    }

    //send bye message
    public void sendBye() {
        String msgForBye = "    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon! \n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForBye);
    }
    
    public void sendCount(int i) {
        System.out.println("    Now you have " + i + " tasks in the list.");
    }
    
    public void printAddedToDo(TaskList tl, Todo todo) {
        String msgForToDo = "    ____________________________________________________________\n"
                + "    Got it. I 've added this task: \n"
                + "      " + todo.toString() + "\n"
                + tl.countNum() + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForToDo);
    }

    public void printAddedDdl(TaskList tl, Deadline ddl) {
        String msgForToDo = "    ____________________________________________________________\n"
                + "    Got it. I 've added this task: \n"
                + "      " + ddl.toString() + "\n"
                + tl.countNum() + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForToDo);
    }

    public void printAddedEvent(TaskList tl, Event event) {
        String msgForEvent = "    ____________________________________________________________\n"
                + "    Got it. I 've added this task: \n"
                + "      " + event.toString() + "\n"
                + tl.countNum() + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(msgForEvent);
    }

    public void showLoadingError() {
        System.out.println("Loading error!");
    }
}
