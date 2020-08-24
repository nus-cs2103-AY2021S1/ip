package main.java;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner input;
    private final static String UNDERSCORE = "____________________________________________________________ \n";


    public Ui() { }

    public void welcomeMessage(TaskList list) {
        System.out.println("____________________________________________________________ \n"
                + "Hello! Welcome to Duke, your personal task manager! \nWhat can I do for you?"
        );
        if (list.getList().size() > 0) {
            System.out.println("You have outstanding tasks. Type 'list' to view your current tasks.");
        }
        System.out.println("____________________________________________________________ \n");
    }

    public void showAdded () {
        System.out.println("Okay! I've added it to the list." +
                " To view your current tasks, type 'list'");
    }

    public void showList(ArrayList<Task> list) {
        System.out.println(UNDERSCORE);
        if(list.size() == 0){
            System.out.println("you do not have any tasks yet");
        } else {
            for (int i = 0; i < list.size(); i++) {
                int number = i + 1;
                System.out.println(" " + number + "." + list.get(i));
            }
        }
        System.out.println(UNDERSCORE);
    }

    public void showInvalidCommand() {
        System.out.println("I'm sorry I don't understand :(");
    }

    public String readCommand(){
        if(input.hasNextLine()){
            return input.nextLine();
        } else {
            return null;
        }
    }

    public void showEnd() {
        System.out.println(UNDERSCORE + " Bye. Hope to see you again soon!" + "\n" + UNDERSCORE);
    }

    public void close(){
        input.close();
    }
    public void start() {
        input = new Scanner(System.in);
    }

    public void showError(Exception err) {
        System.out.println(err.getMessage());
    }
}
