import java.util.ArrayList;
import java.util.Scanner;

//Posh Version of Duke
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
        DELETE
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
        String greeting = "Oh Golly! Who do we have here?\nThe name's Duke, how can I be of assistance?";
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
                //delete command
                else if (user_command.equals(Command.DELETE)) {
                    deleteTask(user_input);
                }
                //To catch inappropriate commands that have not been identified
                else {
                    System.out.println("OH FIDDLESTICKS, WE SEEM TO HAVE HIT A BUMP ON THE ROAD HERE.");
                }
            } catch (DukeIllegalCommandException e) {
                System.out.println(e.getMessage());
            } catch (DukeInvalidUserInputException e) {
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
        String emptyListMsg = "Oh dear, it seems that your tasks list is empty!";
        if (taskList.size() < 1) {
            System.out.println(emptyListMsg);
        } else {
            System.out.println(getListMsg);
            System.out.println(output);
        }
    }

    private void getTotalTasksMsg() {
        System.out.println("Marvellous! Now you have " + this.taskList.size() + " tasks in your list!");
    }

    //Done command
    //Then, check if user input contains apt integer
    private void markTaskDone(String user_input) throws DukeInvalidUserInputException {
        //Get number after done keyword
        String int_substring = user_input.substring(5);
        try {
            int int_substring_converted = Integer.parseInt(int_substring);
            Task currentTask = this.taskList.get(int_substring_converted - 1);
            currentTask.markAsDone();
            String markDoneMsg = "Splendid! I've marked the following task as done:";
            System.out.println(markDoneMsg);
            System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
        } catch (NumberFormatException ex) {
            throw new DukeInvalidUserInputException("My sincere apologies, but please enter a valid number.");
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeInvalidUserInputException("Oh dear, it appears that item does not exist.");
        }
    }

    //Delete command
    //Then, check if user input contains apt integer
    private void deleteTask(String user_input) throws DukeInvalidUserInputException{
        //Get number after done keyword
        String int_substring = user_input.substring(7);
        try {
            int int_substring_converted = Integer.parseInt(int_substring);
            Task currentTask = this.taskList.get(int_substring_converted - 1);
            this.taskList.remove(currentTask);
            String deleteMsg = "No worries, the following task has been deleted from your list:";
            System.out.println(deleteMsg);
            System.out.println("  [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
            getTotalTasksMsg();
        } catch (NumberFormatException ex) {
            throw new DukeInvalidUserInputException("My sincere apologies, but please enter a valid number.");
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeInvalidUserInputException("Oh dear, it appears that item does not exist.");
        }
    }

    //Adds a To Do task to the task list
    private void addToDo(String user_input) throws DukeInvalidUserInputException{
        try {
            String description = user_input.substring(5).trim();
            if (description.isEmpty()) {
                throw new DukeInvalidUserInputException("I'm sorry to inform you that the description of a todo must not be empty.");
            }
            ToDo newTask = new ToDo(description);
            this.taskList.add(newTask);
            addedToListMsg();
            System.out.println("\t" + newTask);
            getTotalTasksMsg();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("I'm sorry to inform you that the description of a todo must not be empty.");
        }
    }

    //Adds a Deadline task to the task list
    private void addDeadline(String user_input) throws DukeIllegalCommandException, DukeInvalidUserInputException{
        try{
            String withoutCommand = user_input.substring(user_input.indexOf(' '));
            String[] withoutCommandArr = withoutCommand.split("/");
            String description = withoutCommandArr[0].trim();
            if (description.isEmpty()) {
                throw new DukeInvalidUserInputException("I'm sorry to inform you that the description of a deadline must not be empty.");
            }
            if (withoutCommandArr.length < 2) {
                throw new DukeInvalidUserInputException("It appears you are missing a follow up '/by' command.");
            }
            String followUpCommand = getFollowUpCommand(withoutCommandArr[1]);
            if (followUpCommand.equals("by")) {
                if (!withoutCommandArr[1].trim().contains(" ")) {
                    throw new DukeInvalidUserInputException("It appears you are missing the date and time for your deadline.");
                }
                String dateTime = withoutCommandArr[1].substring(withoutCommandArr[1].indexOf(" ")).trim();
                Deadline newTask = new Deadline(description, dateTime);
                this.taskList.add(newTask);
                addedToListMsg();
                System.out.println("\t" + newTask);
                getTotalTasksMsg();
            } else {
                throw new DukeIllegalCommandException(followUpCommand);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("I'm sorry to inform you that the description of a deadline must not be empty.");

        }
    }

    //Adds an Event task to the task list
    private void addEvent(String user_input) throws DukeIllegalCommandException, DukeInvalidUserInputException{
        try {
            String withoutCommand = user_input.substring(user_input.indexOf(' '));
            String[] withoutCommandArr = withoutCommand.split("/");
            String description = withoutCommandArr[0].trim();
            if (description.isEmpty()) {
                throw new DukeInvalidUserInputException("I'm sorry to inform you that the description of an event must not be empty.");
            }
            if (withoutCommandArr.length < 2) {
                throw new DukeInvalidUserInputException("It appears you are missing a follow up '/at' command.");
            }
            String followUpCommand = getFollowUpCommand(withoutCommandArr[1]);
            if (followUpCommand.equals("at")) {
                if (!withoutCommandArr[1].trim().contains(" ")) {
                    throw new DukeInvalidUserInputException("It appears you are missing the date and time for your event.");
                }
                String dateTime = withoutCommandArr[1].substring(withoutCommandArr[1].indexOf(" ")).trim();
                Event newTask = new Event(description, dateTime);
                this.taskList.add(newTask);
                addedToListMsg();
                System.out.println("\t" + newTask);
                getTotalTasksMsg();
            } else {
                throw new DukeIllegalCommandException(followUpCommand);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("I'm sorry to inform you that the description of an event must not be empty.");
        }
    }

    //To print out add to list message
    private void addedToListMsg() {
        String add_to_listMsg = "No worries, the following task has been added to your list:";
        System.out.println(add_to_listMsg);
    }

    //To obtain follow up commands that follow a '/' such as /by at /at
    private String getFollowUpCommand(String user_input) {
        String[] user_inputArr = user_input.split(" ");
        return user_inputArr[0];
    }

    //To obtain first keyword of user input
    private Command getCommand(String user_input) throws DukeIllegalCommandException{
        String[] user_inputArr = user_input.split(" ");
        String keyword = user_inputArr[0];
        switch (keyword) {
            case "list":
                return Command.LIST;
            case "done":
                return Command.DONE;
            case "bye":
                return Command.BYE;
            case "todo":
                return Command.TODO;
            case "event":
                return Command.EVENT;
            case "deadline":
                return Command.DEADLINE;
            case "delete":
                return Command.DELETE;
            default:
                throw new DukeIllegalCommandException(keyword);
        }
    }

    private void exitDuke() {
        String parting = "Well, I'm utterly knackered! Cheerios!";
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
