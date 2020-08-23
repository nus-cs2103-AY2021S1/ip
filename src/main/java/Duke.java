import java.awt.event.AdjustmentEvent;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This Duke class is the main class that prints out the relevant outputs by including all the subclasses of Task and
 * taking in the input.
 */
public class Duke {
    /**
     * todos includes all the string being input into the input.txt file.
     */
    private static List<String> todos = new ArrayList<>();

    /**
     * This is a static function because it adds all the string in a line into the todos list is static, which contains information
     * of the action you want to do.
     */
    private static void scan(){
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()) {
            do {
                todos.add(sc.nextLine());
            } while (sc.hasNextLine());
        }
    }
    public static LocalDate localDate(String string){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
            LocalDate parsedDate = LocalDate.parse(string, formatter);
            return parsedDate;
        }catch (DateTimeException d) {
            /*try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd, HH:mm");
                LocalDateTime parsedDate = LocalDateTime.parse(string, formatter);
                return parsedDate;
            } catch (DateTimeException g) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime parsedDate = LocalTime.parse(string, formatter);
                } catch (DateTimeException f) {
                    System.out.println(f.toString());
                }
            } */
            throw d;
        }
    }
    public static LocalDateTime localDateTime(String string){
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd, HH:mm");
                LocalDateTime parsedDate = LocalDateTime.parse(string, formatter);
                return parsedDate;
            } catch (DateTimeException g) {
                throw g;
            }
    }
    public static LocalTime localTime(String string){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime parsedDate = LocalTime.parse(string, formatter);
            return parsedDate;
        } catch (DateTimeException f) {
            throw f;
        }
    }
    /**
     * The output function is also static and it reads the input strings that is stored in the static list of todos. This
     * then gives differently outputs and behaves differently depending on the input string.
     * If bye is the first word, it breaks out the loop and gives by message
     * If the first word is list, it lists out all the Tasks that are added
     * If the first word is delete, it deletes the task with the number given following this, if task is present, previously not deleted and the number is present, else it prints out error message depending on situation
     * If the first word is done, it marks the task with the number given following this as done, if task is present, previously not deleted and the number is present, else it prints out error message depending on situation
     * If the first word is todo, it adds the todo task if the description is present and prints that the todo is added, if not error message saying that the task is absent is printed
     * If the first word is deadline, it adds the description task if the description and day is present and prints that the deadline is added, else error message is printed depending on situation
     * If the first word is event, it adds the event task if the description and daytime is present and prints that the event is added, else error message is printed depending on situation
     * If the first word is neither of those listed above, then an error message is printed saying that it is incomprehensible
     */
    private static void output(){
        System.out.println("  ____________________________________________________________\n" + "  Hello! I'm Duke\n" + "  What can I do for you?\n" +
                "  ____________________________________________________________");
        for (String string : todos) {
            if (string.length() >= 3 && string.equals("bye")) {
                System.out.println("\n" + string + "\n  ____________________________________________________________");
                System.out.println("  Bye. Hope to see you again soon!\n" + "  ____________________________________________________________");
                break;
            }
            else if (string.length() >= 4 && string.equals("list")) {
                System.out.println("\n" + string + "\n  ____________________________________________________________");
                Task.listing();
            }
            else if(string.length() >= 6 && string.substring(0, 6).equals("delete")){
                System.out.println("\n" + string + "\n  ____________________________________________________________");
                if(string.length() == 6 || string.length() == 7){
                    System.out.println(new DeleteException(true, false).toString());
                    continue;
                }
                int ID = Integer.parseInt(string.substring(7));
                Task.deleteDone(ID);
            }
            else if (string.length() >= 4 && string.substring(0, 4).equals("done")) {
                System.out.println("\n" + string + "\n  ____________________________________________________________");
                if(string.length() == 4 || string.length() == 5){
                    System.out.println(new DoneException(true, false).toString());
                    continue;
                }
                int ID = Integer.parseInt(string.substring(5));
                Task.setDone(ID);
            }
            else if (string.length() >= 4 && string.substring(0, 4).equals("todo")) {
                System.out.println("\n" + string + "\n  ____________________________________________________________");
                if(string.length() == 4 || string.length() == 5){
                    System.out.println(new TodoException().toString());
                }else {
                    todo t = new todo(string.substring(5));
                    t.output();
                }
            }
            else if (string.length() >= 5 && string.substring(0, 5).equals("event")) {
                System.out.println("\n" + string + "\n  ____________________________________________________________");
                if(string.length() == 5 || string.length() == 6){
                    System.out.println(new EventException(true).toString());
                    continue;
                }
                String s = "";
                int index = -1;
                int end = -1;
                boolean duration = false;
                boolean time = false;
                String start = "";
                for (int i = 5; i < string.length(); i++) {
                    if (string.charAt(i) == '/') {
                        index = i;
                        time = true;
                        break;
                    }
                    s = s + string.charAt(i);
                }
                for(int i = index + 1; i < string.length(); i++){
                    if (string.charAt(i) == '-' && i != string.length() - 1) {
                        end = i;
                        duration = true;
                        break;
                    }
                    start = start + string.charAt(i);
                }
                if(!time){
                    System.out.println(new EventException(false).toString());
                    continue;
                }
                if(!duration){
                    continue;
                }
                System.out.println(string.substring(end + 1));
                event d = event.provide(s.substring(1, s.length() - 1), string.substring(index + 4, end), string.substring(end + 1));
                d.output();
            }else if (string.length() >= 8 && string.substring(0, 8).equals("deadline")) {
                System.out.println("\n" + string + "\n  ____________________________________________________________");
                if(string.length() == 8 || string.length() == 9){
                    System.out.println(new DeadlineException(true).toString());
                    continue;
                }
                String s = "";
                int index = -1;
                boolean time = false;
                for (int i = 8; i < string.length(); i++) {
                    if (string.charAt(i) == '/') {
                        index = i;
                        time = true;
                        break;
                    }
                    s = s + string.charAt(i);
                }
                if(!time){
                    System.out.println(new DeadlineException(false).toString());
                    continue;
                }
                deadline d = deadline.provide(s.substring(1, s.length() - 1), string.substring(index + 4));
                d.output();
            } else {
                System.out.println("\n" + string + "\n  ____________________________________________________________");
                System.out.println(new WrongInputException().toString());
            }
            //System.out.println(string);
        }

    }

    /**
     *
     * @param args of type String[]
     * reads input using scan() and adds it to todos.
     *  Then, prints out relevant information using the output() func.
     */
    public static void main(String[] args)  {
        scan();
        output();
    }
}