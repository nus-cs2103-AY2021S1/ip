import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputManager {
    private static String fileDir;

    public static void parse(String input) throws ErrorExceptions{
        Scanner sc = new Scanner(input);
        String current = sc.next();
        TaskManager.fileDir(fileDir);
        if(current.equals("bye")){
            UserInterface.stop();
        }
        else if(current.equals("delete")){
            try {
                task t;
                int index = sc.nextInt();
                try {
                    t = TaskManager.getTask(index);
                    TaskManager.delete(index);
                    UserInterface.done();
                    System.out.println("    " + TaskManager.read(t));
                    System.out.println("The tracked task has been deleted!");
                } catch(NoSuchElementException e){
                    throw new ErrorExceptions("There is no suck task!");
                }
            } catch(NoSuchElementException e){
                throw new ErrorExceptions("There is no suck task!");
            }
        }
        else if(current.equals("done")){
            try {
                task t;
                int index = sc.nextInt();
                try {
                    t = TaskManager.getTask(index);
                    TaskManager.completed(t);
                    UserInterface.done();
                    System.out.println("    " + TaskManager.read(t));
                    System.out.println("The tracked task has been marked as completed! Congrats~~!");
                } catch(IndexOutOfBoundsException e){
                    throw new ErrorExceptions("There is no such task!");
                }
            } catch(NoSuchElementException e){
                throw new ErrorExceptions("There is no suck task!");
            }
        }
        else if(current.equals("list")){
            TaskManager.listing();
        }
        else{ // add tasks
            if(current.equals("todo")) {
                String name = getName(input, 1);
                TaskManager.newTask(name,"Todo",null, fileDir);
            } else if(current.equals("deadline")){
                String name = getName(input, 2);
                String date = getDate(input,1);
                TaskManager.newTask(name,"Deadline",date, fileDir);
            } else if(current.equals("event")){
                String name = getName(input, 2);
                String date = getDate(input,2);
                TaskManager.newTask(name,"Event",date, fileDir);
            } else{
                UserInterface.wrongCommand();
            }
        }
    }

    private static String getName(String input, int type) throws ErrorExceptions{
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
    private static String getDate(String input, int type) throws ErrorExceptions{
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
                throw new ErrorExceptions("Wrong deadline or event command format, missing /action: task");
            }
        }
        return date;
    }
    public static void fileDir(String d){
        fileDir = d;
    }
}
