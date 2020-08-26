package duke;

import java.util.Scanner;

public class Ui {
    
    Scanner sc;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    public void sayHi() {
        String logo = ",--.                           ,--. \n"
                + "|  |-.  ,---. ,--.--. ,---.  ,-|  | \n"
                + "| .-. '| .-. ||  .--'| .-. :' .-. | \n"
                + "| `-' |' '-' '|  |   \\   --.\\ `-' | \n"
                + " `---'  `---' `--'    `----' `---'  \n\n";
        StringBuilder message = new StringBuilder("Hi I'm\n").append(logo).append("Please give me something to do.");
        botOutput(message);
    }
    
    public void sayBye() {
        botOutput("Come back soon!! I'm always bored...");
    }
    
    public void showError(DukeException e) {
        botOutput(e.getMessage());
    } 

    public void botOutput(String message) {
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println(message);
        System.out.println(divider);
    }

    public void botOutput(StringBuilder message) {
        botOutput(message.toString());
    }
    
    public String readNextCommand() {
       return sc.nextLine();
    }
}
