package main.java;

import java.util.Scanner;

public class Ui {
    Scanner scanner;
    public Ui(){
        this.scanner = new Scanner(System.in);
    }

    public String readCommand(){
        return scanner.nextLine();
    }

    public void greetingMessage(){
        System.out.println("Hello! I'm Duke. What can I do for you?");
    }

    public void prompt(){
        System.out.println("Input:");
    }

    public void badInput(){
        System.out.println(
                "Please input:\n"+
                "1)list - to access the list\n" +
                "2)todo - to create a todo task\n" +
                "3)deadline - to create a deadline\n" +
                "4)event - to schedule an event\n" +
                "5)done - to mark tasks as done\n" +
                "6)delete - to delete tasks from the list"
        );
    }

    public void showException(String str){
        System.out.println(str);
    }

}
