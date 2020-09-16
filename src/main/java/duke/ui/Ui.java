package duke.ui;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;

import java.util.Scanner;

/**
 * User Interface
 */
public class Ui {
    public static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";

     private Scanner input;

    /**
     * Construct an User interface.
     */
    public Ui(){
        input = new Scanner(System.in);
    }

    /**
     * Print out the greetings.
     */
    public static void greet(){

        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");

    }

    /**
     * Print out the goodbye words.
     */
    public static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print out the command received.
     * @param command Take in a command.
     */
    public static void echo(String command){
        System.out.println(command);
    }


    /**
     * Run the programme.
     */
    public void run(){

        greet();

        Scanner input = new Scanner(System.in);

        String command;

        while(input.hasNext()){

            try{
                command = input.nextLine();

                if(command.equals("bye")){

                    exit();

                    input.close();

                    break;

                }else{

                    System.out.println(Parser.processCommand(command));

//                    Storage storage = new Storage(Parser.taskList);

                    Storage.saveDataToFile(Parser.taskList);

                }
            } catch(Exception e){

                System.out.println(e.getMessage());

            }

        }
    }
}
