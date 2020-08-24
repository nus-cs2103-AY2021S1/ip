import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        ArrayList<task> store = new ArrayList<>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        if(echo.equals("")){
            System.out.println("Oops, there is nothing here! Please type a command!");
        }
        while(echo.equals("bye") == false){
            try{
                int index = checker.inputChecker(echo);
                int type = checker.typeChecker(echo);
                if(type == 1){
                    try{
                        String name = checker.name(echo);
                        Todo t = new Todo(name);
                        store.add(t);
                        t.print();
                    }
                    catch(NoSuchElementException e){
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                }
                else if(type == 2){
                    try{
                        String name = checker.name(echo);
                        try{
                            String date = checker.dateFinder(echo,2);
                            Deadline d = new Deadline(name);
                            d.addDate(date);
                            store.add(d);
                            d.print();
                        }
                        catch(ErrorExceptions e){
                            System.out.println(e);
                        }
                    }
                    catch(NoSuchElementException e){
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                }
                else if(type == 3){
                    try{
                        String name = checker.name(echo);
                        try{
                            String date = checker.dateFinder(echo,3);
                            Event e = new Event(name);
                            e.addDate(date);
                            store.add(e);
                            e.print();
                        }
                        catch(ErrorExceptions m){
                            System.out.println(m);
                        }
                    }
                    catch(NoSuchElementException e){
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                }
                else{
                    if(echo.equals("list")){
                        int count = 1;
                        for(task i : store){
                            System.out.println(count + ". " + i.read());
                            count++;
                        }
                    }
                    else if(index>0){
                        try{
                            task l = store.get(index-1);
                            l.done();
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println("  " + l.read2());
                        }
                        catch(IndexOutOfBoundsException e){
                            System.out.println("There is no such task!");
                        }
                    }
                    else if(index<0){
                        try{
                            int actual = index * -1;
                            task d = store.get(actual-1);
                            d.delete();
                            store.remove(actual-1);
                        }
                        catch(IndexOutOfBoundsException e){
                            System.out.println("There is no such task!");
                        }

                    }
                    else{
                        System.out.println("Invalid Command, I do not understand! ☹");
                    }
                }
            }
            catch(NoSuchElementException e){
                System.out.println("Oops, there is nothing here! Please type a command!");
            }
            echo = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
