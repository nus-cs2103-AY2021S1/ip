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

    //Regex for parsing the date
    private Pattern date_match = Pattern.compile("^(.*) (/by|/at) (.*)$");

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
                "- todo (name)               : Adds a todo to the list\n" +
                "- event (name) /at (date)   : Adds an event at date\n" +
                "- deadline (name) /by (date): Adds a deadline which expires by date"
                );
    }

    private boolean checkInput(String input){
        //Checks if the user wants to quit

        //Use regex to get the grp
        Matcher inputs = this.pattern.matcher(input);

        //Find the items in the group
        if(!inputs.find()){
            errorMessage(input);
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

            //Check if the user is done with any task
            case "done":{

                //Get the index of the items
                int index = Integer.valueOf(args) - 1;

                //Mark the task as done
                this.taskList.markDone(index);

                //Print the done message
                System.out.println(String.format("Finally! Making yourself useful\n  %s", this.taskList.get(index)));
                break;
            }
            //Otherwise it will be a task to be added
            default: {
                try{
                    //Get the state
                    States val = States.valueOf(command.toUpperCase());

                    //Initialise the task
                    Task tsk = null;

                    //Switch case for the enums
                    switch (val) {

                        //If it is the deadline enum
                        case DEADLINE:

                        //If it is the Event enum
                        case EVENT:

                            //Create the matcher
                            Matcher m = this.date_match.matcher(args);

                            //Check for matches
                            m.find();

                            //Get the date and the name
                            String name = m.group(1);
                            String date = m.group(3);

                            //Pass the 2 arguments into the function
                            tsk = val.createTask(name, date);
                            break;

                        //Otherwise
                        case TODO:

                            //Pass the first item
                            tsk = val.createTask(args);
                            break;
                    }

                    //Add the task to the task list
                    this.taskList.add(tsk);

                    //Print out the message
                    System.out.println(String.format("Can't you keep track of '%s' yourself?\nNow you have %d burdens", tsk, this.taskList.size()));

                //If there is an error
                }catch(Exception e) {

                    //Print the error message
                    this.errorMessage(input);
                }
            }
        }

        //Do not quit
        return false;
    }

    public void errorMessage(String args){

        //Prints the error message
        System.out.println(String.format("Heh you cant even key in a correct command.\nClearly '%s' is not valid", args));
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
            if (this.checkInput(input.trim())){
                break;
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
