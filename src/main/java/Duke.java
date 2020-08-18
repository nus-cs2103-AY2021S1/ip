import java.util.ArrayList;
import java.util.EmptyStackException;
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
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);

        //Initialise scanner to prompt user
        Scanner sc = new Scanner(System.in);

        //Duke runs until user inputs "bye"
        while (this.isChatting) {
            //Obtain user input
            String user_input = sc.nextLine();

            //Obtain command keyword base on user_input
            try {
                Command user_command = getCommand(user_input.trim()); //trim to get rid of unnecessary whitespaces

                //Conditionals based on command keyword
                //list command
                if (user_command.equals(Command.LIST)) {
                    getToDoList();
                }
                //bye command
                else if (user_command.equals(Command.BYE)) {
                    exitDuke();
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
                //To catch inappropriate commands that have not been identified
                else {
                    System.out.println("UNKNOWN ERROR DETECTED");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Converts the tasklist arraylist into a labelled list message
    private void getToDoList() {
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
        String emptyListMsg = "Your tasks list is empty!";
        if (taskList.size() < 1) {
            System.out.println(emptyListMsg);
        } else {
            System.out.println(getListMsg);
            System.out.println(output);
        }
    }

    private void getTotalTasksMsg() {
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    //Done command
    //Check if user input done keyword is located in the correct place
    //Then, check if user input contains apt integer
    private void markTaskDone(String user_input) throws DukeException {
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
            throw new DoneNotNumberException();
        } catch (IndexOutOfBoundsException ex) {
            throw new DoneOutOfBoundException();
        }
    }

    //Adds a To Do task to the task list
    private void addToDo(String user_input) {
        ToDo newTask = new ToDo(user_input.substring(5));
        this.taskList.add(newTask);
        addedToListMsg();
        System.out.println("\t" + newTask);
        getTotalTasksMsg();
    }

    //Adds a Deadline task to the task list
    private void addDeadline(String user_input) throws DukeException {
        String dateTime = getDateTime(user_input, Command.DEADLINE);
        Deadline newTask = new Deadline(user_input.substring(9, user_input.indexOf("/")), dateTime);
        this.taskList.add(newTask);
        addedToListMsg();
        System.out.println("\t" + newTask);
        getTotalTasksMsg();
    }

    //Adds an Event task to the task list
    private void addEvent(String user_input) throws  DukeException {
        String dateTime = getDateTime(user_input, Command.EVENT);
        Event newTask = new Event(user_input.substring(6, user_input.indexOf("/")), dateTime);
        this.taskList.add(newTask);
        addedToListMsg();
        System.out.println("\t" + newTask);
        getTotalTasksMsg();
    }

    //To print out add to list message
    private void addedToListMsg() {
        String add_to_listMsg = "Got it. I've added this task:";
        System.out.println(add_to_listMsg);
    }

    //To obtain date and time that follows a /by or /at
    private String getDateTime(String user_input, Command command) throws  DukeException {
        int slashIndex = user_input.indexOf("/");

        if (slashIndex != -1) {
            if (checkFollowUpCommand(user_input, slashIndex, command)) {
                return user_input.substring(slashIndex+ 3);
            } else {
                throw new InaptFollowUpCommandException();
            }
        } else {
            throw new MissingFollowUpCommandException();
        }
    }

    //Use to check whether commands such as event and deadline have follow up '/' command
    private boolean checkFollowUpCommand(String user_input, int slashIndex, Command command) {
        if (user_input.charAt(slashIndex + 1) == 'b' && user_input.charAt(slashIndex + 2) == 'y'
                && command.equals(Command.DEADLINE)) {
            return true;
        } else if (user_input.charAt(slashIndex + 1) == 'a' && user_input.charAt(slashIndex + 2) == 't'
                && command.equals(Command.EVENT)) {
            return true;
        } else {
            return false;
        }
    }

    //To obtain first keyword of user input
    private Command getCommand(String user_input) throws DukeException{
        //For commands that have text following a keyword
        if (user_input.contains(" ")) {
            int indexOfFirstSpace = user_input.indexOf(' ');
            if (indexOfFirstSpace == 4) {
                if (user_input.substring(0, 4).equals("todo")) {
                    return Command.TODO;
                } else if (user_input.substring(0, 4).equals("done")) {
                    return Command.DONE;
                } else {
                    throw new InaptCommandException();
                }
            } else if (indexOfFirstSpace == 5) {
                if (user_input.substring(0, 5).equals("event")) {
                    return Command.EVENT;
                } else {
                    throw new InaptCommandException();
                }
            } else if (indexOfFirstSpace == 8){
                if (user_input.substring(0, 8).equals("deadline")) {
                    return Command.DEADLINE;
                } else {
                    throw new InaptCommandException();
                }
            } else {
                throw new InaptCommandException();
            }
        }
        //For commands that do not have any text after keyword
        else {
            if (user_input.equals("list")) {
                return Command.LIST;
            } else if (user_input.equals("bye")) {
                return Command.BYE;
            } else if (user_input.equals("todo") || user_input.equals("deadline") || user_input.equals("event"))  {
                throw new EmptyTaskException(user_input);
            }
            else {
                throw new InaptCommandException();
            }
        }
    }

    private void exitDuke() {
        String parting = "Bye. Hope to see you again soon!";
        System.out.println(parting);
        this.isChatting = false;
    }

    public static void main(String[] args) {
        //Initialise Duke
        Duke chatBot = new Duke();
        //Start chatting with Bot
        chatBot.startChat();
    }
}
