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
        int count = 1;
        int index = checker.inputChecker(echo);

        while(echo.equals("bye") == false){
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
                    Deadline d = new Deadline(name);
//                    try{
//
//                    }
//                    catch(NoSuchElementException e){
//
//                    }
                    String date = checker.dateFinder(echo,2);
                    d.addDate(date);
                    store.add(d);
                    d.print();
                }
                catch(NoSuchElementException e){
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            }
            else if(type == 3){
                try{
                    String name = checker.name(echo);
                    Event e = new Event(name);
                    String date = checker.dateFinder(echo,3);
                    e.addDate(date);
                    store.add(e);
                    e.print();
                }
                catch(NoSuchElementException e){
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                }
            }
            else{
                if(echo.equals("list")){
                    for(task i : store){
                        System.out.println(i.read());
                    }
                }
                else if(index!=0){
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
                else{
                    System.out.println("Invalid Command, I do not understand! ☹");
                }
            }
            echo = sc.nextLine();
            index = checker.inputChecker(echo);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
