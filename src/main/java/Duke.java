import java.util.Scanner; // Import the scanner class
import java.util.regex.Pattern;

public class Duke {
    //Task list to store the tasks
    private TaskList taskList;

    //Logo for the mascot
    private String logo="  _    _ _ _                   \n" +
                        " | |  | | | |                  \n" +
                        " | |  | | | |_ _ __ ___  _ __  \n" +
                        " | |  | | | __| '__/ _ \\| '_ \\ \n" +
                        " | |__| | | |_| | | (_) | | | |\n" +
                        "  \\____/|_|\\__|_|  \\___/|_| |_|";

    public Duke(){

        //Create a new task list
        this.taskList = new TaskList();

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

    private String getInput(){

        // Create the scanner object
        Scanner sc = new Scanner(System.in);

        // Take in input
        String input = sc.nextLine();

        //Return the input
        return input;
    }

    private int checkInput(String input) {

        //Switch case to process the commands
        switch (input) {

            //If the user keys in bye
            case "bye": {
                return 0;
            }

            //If the user keys in list
            case "list": {

                //Print out the list so far
                System.out.println("Heh, you cant even remember what you had");

                //Display the content
                this.taskList.displayContent();

                //Break out of the switch
                return 2;
            }


            //Otherwise it will be a task to be added
            default: {

                //Check if the user is marking a task as done
                if (input.indexOf("done") == 0){

                    //Get the rest of string after done
                    String substring = input.substring(5);

                    //Check mark the task as complete
                    if (Pattern.matches("[0-9]+", substring)){

                        //Get the index of the entry
                        int number = Integer.valueOf(substring) - 1;

                        //Perform the task
                        boolean completed = this.taskList.markDone(number);

                        //Check if it is completed successfully
                        if (completed){

                            //Print the completed message
                            System.out.println(String.format("Finally you have done something:\n  [%s] %s", this.taskList.get(number).getStatusIcon(), this.taskList.get(number).getMessage()));

                        }else{

                            //Otherwise print the error message
                            System.out.println(String.format("You can't even key in a correct number!\nClearly %d is not valid when u have %d tasks", number, this.taskList.size()));
                        }
                    }


                    //Break out of the switch
                    return 4;

                }else{

                    //Add the task to the task list
                    this.taskList.add(new Task(input));

                    //Print the added message
                    System.out.println(String.format("Can't you keep track of '%s' yourself?", input));

                    //Break out of the switch
                    return 3;
                }
            }
        }
    }

    public void mainLoop(){

        //Print the intro
        this.printIntro();

        //Get input
        String input = this.getInput();

        // While the user input is not bye
        while(true){

            //Print the separator
            System.out.println("____________________________________________________________");

            //If it is a terminating condition
            if (this.checkInput(input) <= 0){
                break;
            }

            //Print the separator
            System.out.println("____________________________________________________________");

            // Get the next line as input
            input = this.getInput();
        }

        //Print the ending before the bot exits
        this.printEnd();
    }

    public static void main(String[] args) {

        //Create a new duke
        Duke duke = new Duke();

        //Run the main loop
        duke.mainLoop();

    }
}
