package main.java.duke;

public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ______________________________________");
        System.out.println("        Hello! I'm Duke");
        System.out.println("        What can I do for you?");
        System.out.println("    ______________________________________");
    }
    
    public void sayGoodbye() {
        System.out.println("    _______________________________________________________________________________");
        System.out.println("        Bye. Hope to see you again soon!");
        System.out.println("    _______________________________________________________________________________");
    }
    
    public void showInvalidFormatCommandDescription() {
        System.out.println("    _______________________________________________________________________________");
        System.out.println("        ☹ OOPS!!! The description of a done cannot be empty or in wrong format");
        System.out.println("    _______________________________________________________________________________");
    }
    
    public void showMeaninglessCommandDescription() {
        System.out.println("    _______________________________________________________________________________");
        System.out.println("        ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("    _______________________________________________________________________________");
    }
    
    public void printLineBackground() {
        System.out.println("    _______________________________________________________________________________");
    }
}
