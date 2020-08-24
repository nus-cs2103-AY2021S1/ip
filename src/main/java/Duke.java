import Logic.Exceptions.DukeException;
import Logic.Storage.Storage;
import Logic.Tasks.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void listItems(ArrayList<Task> xs){
        int counter = 1;
        for (Task t: xs) {
            System.out.println("    " + counter + ": " + t.toString());
            counter += 1;
        }
    }

    protected static ArrayList<String> convertToFile(ArrayList<Task> list) {
        ArrayList<String> dataFile = new ArrayList<>();
        for (Task task : list){
            dataFile.add(task.toData());
        }
        return dataFile;
    }

    public static void main(String[] args) throws DukeException{

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        ArrayList<Task> listOfTask = new ArrayList<>();
        String breakline = "    ______________________________________________________";
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Welcome to \n" + logo + "\n    Your personal assistant :)");
        System.out.println(breakline);
        System.out.println(breakline);

        Storage storage = new Storage("./src/main/java/Logic/Data/data.txt", "./src/main/java/Logic/Data");
        storage.Load();

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String userCommand = sc.nextLine();
            String[] userWord = userCommand.split(" ", 2);

            if (userCommand.equals("bye")) {
                storage.Save(convertToFile(listOfTask));
                System.out.println(breakline);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(breakline);
                break;
            }
            switch (userWord[0]) {
            case "list":
                System.out.println(breakline);
                listItems(listOfTask);
                System.out.println(breakline);
                break;

            case "todo":
                if (userWord.length == 1 || userWord[1].equals("")) {
                    throw new DukeException("   ☹ OOPS!!! The description of the command todo cannot be empty.");
                }
                ToDo newT = new ToDo(userWord[1]);
                listOfTask.add(newT);
                System.out.println(breakline);
                System.out.println("    Got it. I've added this task: \n     " + newT.toString());
                System.out.println("    Now you have " + listOfTask.size() + " tasks in the list.");
                System.out.println(breakline);
                break;

                case "deadline":
                    if (userWord.length == 1 || userWord[1].equals("")) {
                        throw new DukeException("   ☹ OOPS!!! The description of the command deadline cannot be empty.");
                    }
                    String[] content = userWord[1].split("/by", 2);
                    if (content.length == 1 || content[0].equals("")) {
                        throw new DukeException("   ☹ OOPS!!! We can't seem to find your event description.");
                    }
                    if (content[1].equals("")) {
                        throw new DukeException("   ☹ OOPS!!! We can't seem to find your event time. Please type /by before your preferred timing");
                    }
                    try {
                        String[] dateTime = content[1].split(" ",3);
                        LocalDate localDate = LocalDate.from(dateFormatter.parse(dateTime[1]));
                        LocalTime localTime = LocalTime.from(timeFormatter.parse(dateTime[2]));
                        Deadline newD = new Deadline(content[0], localDate, localTime);
                        listOfTask.add(newD);
                        System.out.println(breakline);
                        System.out.println("    Got it. I've added this task: \n     " + newD.toString());
                        System.out.println("    Now you have " + listOfTask.size() + " tasks in the list.");
                        System.out.println(breakline);
                    } catch (DateTimeParseException error) {
                        throw new DukeException("   ☹ OOPS!!! It seems like you've provided us with the wrong date time format for your event. " +
                                "Please structure it as yyyy-mm-dd hh:mm");
                    }
                    break;

                case "event":
                    if (userWord.length == 1 || userWord[1].equals("")) {
                        throw new DukeException("   ☹ OOPS!!! The description of the command event cannot be empty.");
                    }
                    String[] content2 = userWord[1].split("/at", 2);
                    if (content2.length == 1 || content2[0].equals("")) {
                        throw new DukeException("   ☹ OOPS!!! We can't seem to find your event description.");
                    }
                    if (content2[1].equals("")) {
                        throw new DukeException("   ☹ OOPS!!! We can't seem to find your event time. Please type /at before your preferred timing");
                    }
                    try {
                        String[] dateTime = content2[1].split(" ",3);
                        LocalDate localDate = LocalDate.from(dateFormatter.parse(dateTime[1]));
                        LocalTime localTime = LocalTime.from(timeFormatter.parse(dateTime[2]));
                        Event newE = new Event(content2[0], localDate, localTime);
                        listOfTask.add(newE);
                        System.out.println(breakline);
                        System.out.println("    Got it. I've added this task: \n     " + newE.toString());
                        System.out.println("    Now you have " + listOfTask.size() + " tasks in the list.");
                        System.out.println(breakline);

                    } catch (DateTimeParseException error) {
                        throw new DukeException("   ☹ OOPS!!! It seems like you've provided us with the wrong date time format for your event. " +
                                "Please structure it as yyyy-mm-dd hh:mm");
                    }

                    break;

            case "done":
                if (userWord.length == 1 || userWord[1].equals("")) {
                    throw new DukeException("   ☹ OOPS!!! The description of the command done cannot be empty.");
                }
                int index = Integer.parseInt(userWord[1]) - 1;
                if (index >= 0 && index < listOfTask.size()) {
                    Task temp = listOfTask.get(index);
                    temp.completed();
                    System.out.println(breakline);
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("    " + temp.toString());
                } else {
                    throw new DukeException("    Invalid index entry for done command.");
                }
                System.out.println(breakline);
                break;

            case "delete":
                if (userWord.length == 1 || userWord[1].equals("")) {
                    throw new DukeException("   ☹ OOPS!!! The description of the command done cannot be empty.");
                }
                int index2 = Integer.parseInt(userWord[1]) - 1;
                if (index2 >= 0 && index2 < listOfTask.size()) {
                    Task temp = listOfTask.get(index2);
                    listOfTask.remove(index2);
                    System.out.println(breakline);
                    System.out.println("    Noted. I've removed this task:");
                    System.out.println("    " + temp.toString());
                    System.out.println("    Now you have " + listOfTask.size() + " tasks in the list.");
                } else {
                    throw new DukeException("    Invalid index entry for delete command.");
                }
                System.out.println(breakline);
                break;

            default:
                throw new DukeException("    Sorry! I'm not really sure what to do with this command yet ☹");
            }
        }
        sc.close();
    }
}
