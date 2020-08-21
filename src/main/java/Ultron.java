import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ultron {

    //Task list to store the tasks
    private final TaskList taskList;

    //Get the pattern for the regex for parsing the command
    private final Pattern pattern = Pattern.compile("(^\\s?\\w+\\b) ?(.*)?$");

    //Get the storage
    private final Storage storage;

    // Create the scanner object
    private final Scanner scanner;

    public Ultron(String path){

        //Create the Storage object
        storage = new Storage(path);

        //Fetch the data
        ArrayList<Task> taskArrayList = new ArrayList<>();
        try {
            taskArrayList = storage.fetchAll();
        }catch (UltronException e){}

        //Create a task list
        taskList = new TaskList(taskArrayList);

        //Create a new scanner
        this.scanner = new Scanner(System.in);

    }
    private String getInput() {

        //Declare type
        String input;

        // Take in input
        input = this.scanner.nextLine();

        //Return the input
        return input;
    }

    private String wrapper(String message){
        //Wrapper when printing the error message
        return "Heh, you can't even type in a correct command\n"
                + message
                + "\nI'll give u a pity tip\n"
                + "Use 'help' for more information";
    }

    public void printIntro(){
        // Print the intro messages

        //Logo for the mascot
        String LOGO = "  _    _ _ _                   \n" +
                " | |  | | | |                  \n" +
                " | |  | | | |_ _ __ ___  _ __  \n" +
                " | |  | | | __| '__/ _ \\| '_ \\ \n" +
                " | |__| | | |_| | | (_) | | | |\n" +
                "  \\____/|_|\\__|_|  \\___/|_| |_|";

        //Print the intro
        System.out.println(LOGO
                + "\n____________________________________________________________\n"
                + "Hello lesser being, I am Ultron\n"
                + "What do you want?\n"
                + "____________________________________________________________\n");
    }

    public void printEnd(){
        // Print the end message
        System.out.println("____________________________________________________________\n"
                + "Clearly you were not worth my time.\n"
                + "____________________________________________________________");
    }

    private void helpMessage(){
        System.out.println("Heh I guess I could help an insect like you:\n" +
                        "- help                      : Get help for the commands\n" +
                        "- todo (name)               : Adds a todo to the list\n" +
                        "- event (name) /at (date)   : Adds an event at date\n" +
                        "- deadline (name) /by (date): Adds a deadline which expires by date\n" +
                        "- delete (number)           : Removes a todo from the list\n"
                );
    }

    private int parseInteger(String args) throws UltronException{
        //Catch any errors in the number
        try{

            //Get the index of the items
            return Integer.parseInt(args) - 1;

        }catch(NumberFormatException e){

            //Throw a new Ultron exception
            throw new UltronException(args, ExceptionType.INVALID_NUMBER);
        }
    }

    private boolean checkInput(String input) throws UltronException{
        //Checks if the user wants to quit

        //Use regex to get the grp
        Matcher inputs = this.pattern.matcher(input);

        //Find the items in the group
        if(!inputs.find()){

            //Throw an invalid command error if it is unable to find any matches
            throw new UltronException(input, ExceptionType.INVALID_COMMAND);
        }

        //Get the command
        String inputCommand = inputs.group(1);

        //Get the other arguments
        String arguments = inputs.group(2);

        //Switch case to process the commands
        switch (inputCommand) {

            //If the user keys in bye
            case "bye": {
                storage.writeAll(taskList.getList());
                return true;
            }

            //If the user keys in list
            case "list": {

                //Print out the list so far
                System.out.println("Heh, you cant even remember what you had");

                //Display the content
                this.taskList.displayContent();
                break;
            }

            //Check if the user is asking for help
            case "help":{

                //Print the help message
                helpMessage();
                break;
            }

            case "delete":{
                //Initialise index
                int index = this.parseInteger(arguments);

                //Check if the index is out of range
                if (index >= this.taskList.size()){

                    //Throw an Ultron exception if it is out of range
                    throw new UltronException(inputCommand, Integer.toString(index + 1), ExceptionType.INVALID_ARGUMENT);
                }

                //Get the task
                Task tsk = this.taskList.get(index);

                //Remove the task
                this.taskList.remove(index);

                //Print the delete message
                System.out.printf("What are you doing removing this?!?!\n  %s\nNow you have %d burdens%n", tsk, this.taskList.size());
                break;
            }

            //Check if the user is done with any task
            case "done":{

                //Initialise index
                int index = this.parseInteger(arguments);

                //Mark the task as done
                if (!this.taskList.markDone(index)){

                    //Throw an error if the method return false
                    throw new UltronException(inputCommand, Integer.toString(index), ExceptionType.INVALID_ARGUMENT);
                }

                //Print the done message
                System.out.printf("Finally! Making yourself useful\n  %s%n", this.taskList.get(index));
                break;
            }
            //Otherwise it will be a task to be added
            default: {

                //Init the enum states
                Command command;
                Task task;

                try{
                    //Get the state
                    command = Command.valueOf(inputCommand.toUpperCase());

                }catch (IllegalArgumentException e){

                    //Throw a Duke exception
                    throw new UltronException(inputCommand, ExceptionType.INVALID_COMMAND);
                }

                //Trim the args
                if (arguments.trim().length() == 0){

                    //Throw an exception when there is nothing supplied
                    throw new UltronException(inputCommand, ExceptionType.NO_ARGUMENTS_SUPPLIED);
                }

                try{
                    //Create a new task
                    task = command.createTask(arguments);

                }catch(IllegalStateException e){

                    //Throw a Duke exception
                    throw new UltronException(inputCommand, ExceptionType.INVALID_COMMAND);
                }

                //Add the task to the task list
                this.taskList.add(task);

                //Print out the message
                System.out.printf("Can't you keep track of '%s' yourself?\nNow you have %d burdens%n", task, this.taskList.size());

            }
        }

        //Do not quit
        return false;
    }

    public void mainLoop(){

        //Print the intro
        this.printIntro();

        // While the user input is not bye
        do{
            // Get the next line as input
            String input = this.getInput();

            //Print the separator
            System.out.println("____________________________________________________________");

            //If it is a terminating condition
            try{

                //If the input returns True to quitting
                if (this.checkInput(input.trim())){

                    //Break out of the loop
                    break;
                }

            //Catch the Ultron exception
            }catch(UltronException e){

                //Print the error message
                System.out.println(this.wrapper(e.getMessage()));
            }

            //Print the separator
            System.out.println("____________________________________________________________");

        }while(true);

        //Print the ending before the bot exits
        this.printEnd();
    }

    public static void main(String[] args) {

        //Create a new duke
        Ultron duke = new Ultron("data/data.txt");

        //Run the main loop
        duke.mainLoop();

    }
}
