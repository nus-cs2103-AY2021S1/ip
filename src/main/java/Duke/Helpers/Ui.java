package Duke.Helpers;

import Duke.Duke;
import Duke.Errors.DukeException;

import java.util.Scanner;

/**
 * deals with interactions with the user
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * this prints out if there is an error when tasks are loaded
     * @param d this is the error that is thrown whose string message gets printed
     */
    public void showLoadingError(DukeException d){
        System.out.println(d.getMessage());
    }

    /**
     * prints welcome message
     */
    public void showWelcome(){
        System.out.println("  ____________________________________________________________\n" +
                "  Hello! I'm Duke\n" + "  What can I do for you?");
    }

    /**
     * this prints the ____ for easier readability
     */
    public void showLine(){
        System.out.println("  ____________________________________________________________\n");
    }

    /**
     * this prints the next line of code to execute if it exists
     * @return the string of command
     */
    public String readCommand(){
        if(sc.hasNext()){
            return sc.nextLine();
        }else{
            return null;
        }
    }

    /**
     * prints out the error
     * @param s s is the error that is printed
     */
    public void showError(String s){
        System.out.println(s);
    }

}
