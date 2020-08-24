import exceptions.*;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public static void delete(String request, TaskList list){
        try {
            int index = Integer.parseInt(request);
            isNumeric(index, list);
            System.out.println("The event has been removed as per your request: \n " + list.get(index));
            list.delete(index);
            System.out.println("Now you have " + list.getSize() + " tasks in the list+ \n_____________________________");
        } catch (NumberFormatException e) {
            System.out.println("You have not provided a valid number\n_____________________________");
        } catch (InvalidNumberException e) {
            System.out.println("The number provided was greater or lesser than the number of items in the list\n_____________________________");
        }
    }
    public static void update(String request, TaskList list){
        try {
            int index = Integer.parseInt(request);
            isNumeric(index, list);
            list.updateStatus(index);
            System.out.println("Nice! I've marked this task as done: \n " + list.get(index));
            System.out.println("Now you have " + list.getSize() + " tasks in the list \n_____________________________");
        } catch (NumberFormatException e) {
            System.out.println("You have not provided a valid number\n_____________________________");
        } catch (InvalidNumberException e) {
            System.out.println("The number provided was greater or lesser than the number of items in the list\n_____________________________");
        }
    }

    public static void addTodo(String request, TaskList list){
        try {
            ToDos todo = getTodo(request);
            System.out.println("Got it. I've added this task:" + todo);
            list.update(todo);
            System.out.println("Now you have " + list.getSize() + " tasks in the list.\n_____________________________");
        } catch (EmptyTodoException ex) {
            System.out.println("Oops!!! I'm sorry, but the description of a todo cannot be empty\n_____________________________");
        }
    }

    public static void isNumeric(int n, TaskList list) throws InvalidNumberException{
        if (list.getSize() < n || n <= 0){
            throw new InvalidNumberException("The number provided is bigger tha the list size");
        }
    }

    public static void getDeadline(String word, TaskList list) throws InvalidDeadlineException {
        if (word.contains("/by") && !word.substring(word.indexOf("/by") + 3).equals("")){
            word = word.substring(8);
            int index = word.indexOf("/by");
            String str = word.substring(index + 3).trim();
            String datestr = str.replaceAll("-", "/");
            String[] datearr = datestr.split("/");
            if (datearr.length < 2){
                throw new InvalidDeadlineException();
            }
            if (datearr[0].length() < 2){
                datestr = "0" + datestr;
                datearr[0] = "0" + datearr[0];
            }
            if (datearr[1].length() < 2){
                datestr = datearr[0] + "/0" + datearr[1] + "/" + datearr[2];
            }
            if (!datestr.contains(":")){
                String[] arr = datestr.split(" ");
                if (arr.length > 2){
                    throw new InvalidDeadlineException();
                }
                arr[1] = arr[1].substring(0, 2) + ":" + arr[1].substring(2);
                datestr = arr[0] + " " + arr[1];
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime date = LocalDateTime.parse(datestr, formatter);
                Deadline deadline = new Deadline(word.substring(0, index), date);
                list.update(deadline);
                System.out.println("Got it. I've added this task: " + deadline);
                System.out.println("Now you have " + list.getSize() + " tasks in the list.");
            } catch (DateTimeParseException e){
                System.out.println("Invalid date provided");
            }
            System.out.println("_____________________________");
        } else {
            throw new InvalidDeadlineException();
        }
    }

    public static String getEventTest(String word) throws InvalidEventException {
        if (word.contains("/at") && !word.substring(word.indexOf("/at") + 3).equals("")){
            word = word.substring(5);
            int index = word.indexOf("/at");
            String str = word.substring(index + 3).trim();
            String datestr = str.replaceAll("-", "/");
            String[] datearr = datestr.split("/");
            if (datearr.length < 2){
                throw new InvalidEventException();
            }
            if (datearr[0].length() < 2){
                datestr = "0" + datestr;
                datearr[0] = "0" + datearr[0];
            }
            if (datearr[1].length() < 2){
                datestr = datearr[0] + "/0" + datearr[1] + "/" + datearr[2];
            }
            if (!datestr.contains(":")){
                String[] arr = datestr.split(" ");
                if (arr.length > 2){
                    throw new InvalidEventException();
                }
                arr[1] = arr[1].substring(0, 2) + ":" + arr[1].substring(2);
                datestr = arr[0] + " " + arr[1];
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime date = LocalDateTime.parse(datestr, formatter);
                Event event =  new Event(word.substring(0, index), date);
                return event.toString();
            } catch (DateTimeParseException e){
                System.out.println("Incorrect Date format used");
                return null;
            }
        } else {
            throw new InvalidEventException();
        }
    }

    public static void getEvent(String word, TaskList list) throws InvalidEventException {
        if (word.contains("/at") && !word.substring(word.indexOf("/at") + 3).equals("")){
            word = word.substring(5);
            int index = word.indexOf("/at");
            String str = word.substring(index + 3).trim();
            String datestr = str.replaceAll("-", "/");
            String[] datearr = datestr.split("/");
            if (datearr.length < 2){
                throw new InvalidEventException();
            }
            if (datearr[0].length() < 2){
                datestr = "0" + datestr;
                datearr[0] = "0" + datearr[0];
            }
            if (datearr[1].length() < 2){
                datestr = datearr[0] + "/0" + datearr[1] + "/" + datearr[2];
            }
            if (!datestr.contains(":")){
                String[] arr = datestr.split(" ");
                if (arr.length > 2){
                    throw new InvalidEventException();
                }
                arr[1] = arr[1].substring(0, 2) + ":" + arr[1].substring(2);
                datestr = arr[0] + " " + arr[1];
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime date = LocalDateTime.parse(datestr, formatter);
                Event event =  new Event(word.substring(0, index), date);
                System.out.println("Got it. I've added this task:\n" + event);
                list.update(event);
                System.out.println("Now you have " + list.getSize() + " tasks in the list.");
            } catch (DateTimeParseException e){
                System.out.println("Incorrect Date format used");;
            }
            System.out.println("_____________________________");
        } else {
            throw new InvalidEventException();
        }
    }

    public static void validity(String line) throws UnknownCommandException {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("delete", "done", "todo", "event", "deadline"));
        String[] words = line.split(" ");
        if (list.contains(words[0]) && words.length > 1){

        } else {
            throw new UnknownCommandException();
        }
    }

    public static ToDos getTodo(String work) throws EmptyTodoException{
        if (work.length() > 4){
            return new ToDos(work.substring(4));
        } else {
            throw new EmptyTodoException();
        }
    }
}