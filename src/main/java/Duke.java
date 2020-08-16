import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> todos = new ArrayList<>();
    public static void scan(FileInputStream inputStream){
        try {
            int size = inputStream.available();
            StringBuffer text = new StringBuffer();
            for(int i = 0; i < size; i++){
                char c = (char)inputStream.read();
                text.append(c);
            }
            String s = " ";
            for(int i = 0 ; i < size; i++){
                if(text.toString().charAt(i) == 10){
                    todos.add(s.substring(1));
                    s = " ";
                }else {
                    s = s + text.toString().charAt(i);
                }
            }
            todos.add(s.substring(1));
        }catch (Exception e){
            System.out.println();
        }
    }
    public static void output(){
        System.out.println("  ____________________________________________________________\n" + "  Hello! I'm Duke\n" + "  What can I do for you?\n" +
                "  ____________________________________________________________");
        for(String string : todos){
            System.out.println("\n" + string + "\n  ____________________________________________________________");
            if(string.equals("bye")){
                System.out.println("  Bye. Hope to see you again soon!\n" + "  ____________________________________________________________");
                break;
            }
            if(string.equals("list")){
                Task.listing();
            }else if(string.substring(0,4).equals("done")){
                int ID = Integer.parseInt(string.substring(5));
                Task.tasks.get(ID - 1).setDone();
                Task.tasks.get(ID - 1).donePrint();
            }else {
                Task t = new Task(string);
            }
        }
    }
    public static void main(String[] args) {
        /*System.out.println("  ____________________________________________________________\n" + "  Hello! I'm Duke\n" + "  What can I do for you?\n"
                         + "  ____________________________________________________________");
        try {
            String file = args[0];
            FileInputStream inputStream = new FileInputStream(file);
            int size = inputStream.available();
            ArrayList<String> strings = new ArrayList<>();
            ArrayList<String> list = new ArrayList<>();
            StringBuffer text = new StringBuffer();
            for(int i = 0; i < size; i++){
                char c = (char)inputStream.read();
                text.append(c);
            }
            String s = " ";
            for(int i = 0 ; i < size; i++){
                if(text.toString().charAt(i) == 10){
                    strings.add(s.substring(1));
                    s = "";
                }
                s = s + text.toString().charAt(i);
            }
            strings.add(s.substring(1));
            for(String string : strings){
                System.out.println("\n" + string + "\n  ____________________________________________________________");
                if(string.equals("bye")){
                    System.out.println("  Bye. Hope to see you again soon!\n" + "  ____________________________________________________________");
                    break;
                }
                if(string.equals("list")){
                    for(int i = 0; i < list.size(); i++){
                        System.out.println("  " + (i + 1) + ". " + list.get(i));
                    }
                }else {
                    list.add(string);
                    System.out.println("  " + "added: " + string + "\n" +
                            "  ____________________________________________________________");
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }*/
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        try {
            String file = args[0];
            FileInputStream inputStream = new FileInputStream(file);
            scan(inputStream);
            output();
        }catch (Exception e){

        }

    }
}
