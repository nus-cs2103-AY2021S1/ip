import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ultron {
    //Task list to store the tasks
    private TaskList taskList;

    //Logo for the mascot
    private String logo="  _    _ _ _                   \n" +
                        " | |  | | | |                  \n" +
                        " | |  | | | |_ _ __ ___  _ __  \n" +
                        " | |  | | | __| '__/ _ \\| '_ \\ \n" +
                        " | |__| | | |_| | | (_) | | | |\n" +
                        "  \\____/|_|\\__|_|  \\___/|_| |_|";

    //Get the pattern for the regex for parsing the command
    private Pattern pattern = Pattern.compile("(^\\s?\\w+\\b) ?(.*)?$");

    // Create the scanner object
    private Scanner rd = new Scanner(System.in);

    public Ultron(){

        //Create a new task list
        this.taskList = new TaskList();

    }
    private String getInput() {

        //Declare type
        String input;

        // Take in input
        input = this.rd.nextLine();

        //Return the input
        return input;
    }

    public void printIntro(){
        // Print the intro messages
        System.out.println("Hello from\n" + this.getLogo());
        System.out.println("____________________________________________________________\n"
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

    public String getLogo(){

        //Get the logo of Ultron
        return this.logo;
    }

    private void helpMessage(){
        System.out.println("Heh I guess I could help an insect like you:\n" +
                        "- help                      : Get help for the commands\n" +
                        "- todo (name)               : Adds a todo to the list\n" +
                        "- event (name) /at (date)   : Adds an event at date\n" +
                        "- deadline (name) /by (date): Adds a deadline which expires by date\n"

                );
    }

    private int parseInteger(String args) throws UltronException{
        //Catch any errors in the number
        try{

            //Get the index of the items
            return Integer.valueOf(args) - 1;

        }catch(NumberFormatException e){

            //Throw a new Ultron exception
            throw new UltronException(String.format("'%s' is not a valid number", args));
        }
    }

    private boolean checkInput(String input) throws UltronException{
        //Checks if the user wants to quit

        //Use regex to get the grp
        Matcher inputs = this.pattern.matcher(input);

        //Find the items in the group
        if(!inputs.find()){

            errorMessage(new UltronException(String.format("Invalid command", input)));
            return false;
        }

        //Get the command
        String command = inputs.group(1);

        //Get the other arguments
        String args = inputs.group(2);

        //Switch case to process the commands
        switch (command) {

            //If the user keys in bye
            case "bye": {
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
                int index = this.parseInteger(args);

                //Get the task
                Task tsk = this.taskList.get(index);

                //Remove the task
                if (!this.taskList.remove(index)){

                    //Throw an error if the method return false
                    throw new UltronException(String.format("Invalid value of done '%s'", index));
                }

                //Print the delete message
                System.out.println(String.format("What are you doing removing this?!?!\n  %s", tsk));
                break;
            }

            //Check if the user is done with any task
            case "done":{

                //Initialise index
                int index = this.parseInteger(args);

                //Mark the task as done
                if (!this.taskList.markDone(index)){

                    //Throw an error if the method return false
                    throw new UltronException(String.format("Invalid value of done '%s'", index));
                }

                //Print the done message
                System.out.println(String.format("Finally! Making yourself useful\n  %s", this.taskList.get(index)));
                break;
            }
            //Otherwise it will be a task to be added
            default: {

                //Init the enum states
                States val;
                Task tsk;

                try{
                    //Get the state
                    val = States.valueOf(command.toUpperCase());

                }catch (IllegalArgumentException e){

                    //Throw a Duke exception
                    throw new UltronException(String.format("'%s' is not a valid command", command));
                }

                //Trim the args
                if (args.trim().length() == 0){

                    //Throw an exception when there is nothing supplied
                    throw new UltronException(String.format("There are no arguments supplied to %s command", command));
                }

                try{
                    //Create a new task
                    tsk = val.createTask(args);

                }catch(IllegalStateException e){

                    //Throw a Duke exception
                    throw new UltronException(String.format("Invalid %s command syntax\nUse 'help' for more information",command));
                }

                //Add the task to the task list
                this.taskList.add(tsk);

                //Print out the message
                System.out.println(String.format("Can't you keep track of '%s' yourself?\nNow you have %d burdens", tsk, this.taskList.size()));

            }
        }

        //Do not quit
        return false;
    }

    public void errorMessage(UltronException e){

        //Prints the error message
        System.out.println(String.format("Heh you cant even key in a correct command.\n%s", e.getMessage()));
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
                this.errorMessage(e);
            }

            //Print the separator
            System.out.println("____________________________________________________________");

        }while(true);

        //Print the ending before the bot exits
        this.printEnd();
    }

    public static void main(String[] args) {

        //Create a new duke
        Ultron duke = new Ultron();

        //Run the main loop
        duke.mainLoop();

    }
}
