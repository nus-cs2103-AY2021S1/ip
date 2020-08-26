package Parser;

import Errors.ErrorExceptions;
import Tasks.TaskManager;
import Tasks.task;
import UI.UserInterface;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    private static String fileDir;

    public static void parse(String input) throws ErrorExceptions {
        Scanner sc = new Scanner(input);
        String current = sc.next();
        TaskManager.fileDir(fileDir);
        if(current.equals("bye")){
            ParseExit.execute();
        }
        else if(current.equals("delete")){
            try {
                int index = sc.nextInt();
                ParseDelete.execute(index);
            } catch(NoSuchElementException e){
                throw new ErrorExceptions("There is no such tasks!");
            }
        }
        else if(current.equals("done")){
            try {
                task t;
                int index = sc.nextInt();
                ParseCompleted.execute(index);
            } catch(NoSuchElementException e){
                throw new ErrorExceptions("There is no such tasks!");
            }
        }
        else if(current.equals("list")){
            ParseList.execute();
        }
        else if(current.equals("show")){
            ParseShow.execute();
        }
        else if(current.equals("filter")){
            String date = sc.next();
            ParseFilter.execute(date);
        }
        else{ // add tasks
            ParseAddTask.execute(current,input);
        }
    }

    public static String getName(String input, int type) throws ErrorExceptions {
        Scanner sc = new Scanner(input);
        sc.next(); // skip first commandtype
        String name = "";
        try {
            String current = sc.next();
            if (type == 1) { // todo
                try {
                    while (current.charAt(0) != '/') { // deadline and event
                        name = name + current + " ";
                        current = sc.next();
                    }
                } catch (NoSuchElementException e) {
                }
            } else {
                try {
                    while (current.charAt(0) != '/') { // deadline and event
                        name = name + current + " ";
                        current = sc.next();
                    }
                } catch(NoSuchElementException e){}
            }
            return name;
        } catch(NoSuchElementException e){
            throw new ErrorExceptions("Missing item name!");
        }
    }
    public static String getDate(String input, int type) throws ErrorExceptions {
        Scanner sc = new Scanner(input);
        String next = sc.next();
        String N = "" + next.charAt(0);
        String date = "";
        try{
            while(!N.equals("/")){
                next = sc.next();
                N = "" + next.charAt(0);
            }
            String action = "";
            try{
                String day = sc.next();
                action = action + day;
                try{
                    String time = sc.next();
                    action = action + " " + time;
                }
                catch(NoSuchElementException e){
                    if(type == 2){
                    }
                    else{
                        throw new ErrorExceptions("Wrong event command format, missing timeslot");
                    }
                }
                date = action;
            }
            catch(NoSuchElementException e){
                throw new ErrorExceptions("Wrong event command format, missing date");
            }
        }
        catch(NoSuchElementException e){
            if(type == 1){
                return date;
            }
            else{
                throw new ErrorExceptions("Wrong deadline or event command format, missing /action: Tasks.task");
            }
        }
        return date;
    }
    public static void fileDir(String d){
        fileDir = d;
    }

    public static String getFileDir(){ return fileDir; }
}
