import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    enum TaskType{
        TODO,
        DEADLINE,
        EVENT
    }

    // print all the content in the list
    public static void printList(ArrayList<Task> list){
        for(int i = 0; i < list.size(); i++){
            System.out.println((i + 1) + list.get(i).toString());
        }
    }

    // return the description
    public static String getDescription(String s, TaskType type){
        if(type == TaskType.TODO){
            int start = 0;
            while(!s.substring(start, start + 4).equals("todo")) start++;
            return s.substring(start + 4);
        }
        String firstWord = type == TaskType.EVENT ? "event" : "deadline", secondWord = type == TaskType.EVENT ? "/at" : "/by";
        int start = 0, firstWordLen = type == TaskType.EVENT ? 5 : 8, len = s.length();
        while(!s.substring(start, start + firstWordLen).equals(firstWord)) start++;
        start += (firstWordLen + 1);
        if(start >= len) return s.substring(len);
        int end = start + 1;
        while(end + 3 < len && !s.substring(end, end + 3).equals(secondWord)) end++;
        if(end + 3 >= len) end = len + 1;
        return s.substring(start, end - 1);
    }

    /*// return the description of Event
    public static String getDeadlineDescription(String[] command, int ptr){
        String res = "";
        Boolean start = false;
        for(int i = ptr; i < command.length; i++){
            if(command[i].equals("/by")) break;
            if(start){
                res += (" " + command[i]);
            }
            else{
                start = true;
                res += command[i];
            }
        }
        return res;
    }*/

    // return the string after the substring "/by" or "/at"
    public static String getTime(String s, TaskType type){
        String word = type == TaskType.EVENT ? "/at" : "/by";
        int i = 0, len = s.length();
        while(i + 3 < len && !s.substring(i, i + 3).equals(word)) i++;
        return i + 3 == len ? "" : s.substring(i + 4);
    }

    // return the string after the substring "/by" or "/at"
    public static String getDeadlineTime(String[] command){
        int len = command.length;
        if(len <= 0) return null;
        int i = 0;
        String res = "";
        while(i < len && !command[i].equals("/by")){
            i++;
        }
        i++;
        while(i < len){
            res += command[i++];
        }
        return res;
    }

    public static void writeArrayList2file(ArrayList<Task> list, FileWriter fileWriter){
        try{
            for(int i = 0; i < list.size(); i++){
                if(list.get(i) instanceof Todo){
                    Todo todo = (Todo)list.get(i);
                    fileWriter.write("T|" + (todo.isDone ? "1" : "0") + "|" + todo.getDescription() + "\n");
                }
                else if(list.get(i) instanceof Deadline){
                    Deadline ddl = (Deadline)list.get(i);
                    fileWriter.write("D|" + (ddl.isDone ? "1" : "0") + "|" + ddl.getDescription() +
                            "|" + ddl.getBy() + "\n");
                }
                else{
                    Event event = (Event)list.get(i);
                    fileWriter.write("E|" + (event.isDone ? "1" : "0") + "|" + event.getDescription() +
                            "|" + event.getAt() + "\n");
                }
            }
            fileWriter.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> readTextFile2List(BufferedReader reader) throws IOException {
        ArrayList<Task> list = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null){
            String[] part = line.split("\\|");
            if(part[0].equals("T")){
                list.add(new Todo(part[2], part[1].equals("1")));
            }
            else if(part[0].equals("D")){
                list.add(new Deadline(part[2], part[3], part[1].equals("1")));
            }
            else{
                list.add(new Event(part[2], part[3], part[1].equals("1")));
            }
        }
        return list;
    }

    // returns string to be parsed to LocalTime
    public static String getLocalTime(String[] command, TaskType type){
        String word = type == TaskType.DEADLINE ? "/by" : "/at";
        for(int i = 0; i < command.length; i++) {
            if(command[i].equals(word)){
                if(i + 2 < command.length){
                    return command[i + 2];
                }
                else{
                    return null;
                }
            }
        }
        return null;
    }

    public static String changeDateFormat(String[] command, TaskType type){
        String word = type == TaskType.DEADLINE ? "/by" : "/at";
        for(int i = 0; i < command.length; i++){
            if(command[i].equals(word)){
                if(i + 1 < command.length){
                    command[i + 1] = command[i + 1].replace('/', '-');
                    return command[i + 1];
                }
            }
        }
        return null;
    }

    public static void checkAndPrint(ArrayList<Task> list){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) instanceof Deadline){
                Deadline ddl = (Deadline)list.get(i);
                if(ddl.getDate() != null && ddl.getDate().equals(LocalDate.now()) && !ddl.getIsDone()){
                    System.out.println("Deadline: (" + ddl.getDescription() + ") is due today");
                }
            }
            else if(list.get(i) instanceof Event){
                Event event = (Event)list.get(i);
                if(event.getDate() != null && event.getDate().equals(LocalDate.now()) && !event.getIsDone()){
                    System.out.println("Event: (" + event.getDescription() + ") is today");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // print greetings of chatbot
        System.out.println("____________________________________________________________\n" +
                           "Hello! I'm Duke\n" +
                           "What can I do for you?" +
                           "\n____________________________________________________________");

        ArrayList<Task> list;
        File outputFile = new File("./data/duke.txt");
        boolean dukeExist = false;
        if(outputFile.exists()){
            list = readTextFile2List(new BufferedReader(new FileReader("./data/duke.txt")));
            dukeExist = true;
        }
        else{
            list = new ArrayList<>();
        }

        // add command entered by the user to the list
        String inputCommand;
        String[] command;
        int ptr;
        Scanner sc = new Scanner(System.in);

        while(true){
            inputCommand = sc.nextLine();
            command = inputCommand.split(" ");
            ptr = 0;

            // if the user input is empty, continue the loop
            if(command.length <= 0 || inputCommand.equals("")){
                System.out.println("____________________________________________________________");
                System.out.println("\uD83D\uDE43 OOPS!!! input cannot be empty.");
                System.out.println("____________________________________________________________");
                continue;
            }

            while(/*ptr < command.length && */command[ptr].equals("")) ptr++;

            if(command[ptr].equals("bye")){
                break;
            }
            else if(command[ptr].equals("list")){
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                printList(list);
                System.out.println("____________________________________________________________");
            }
            else if(command[ptr].equals("done")){
                try{
                    int taskNumber = Integer.parseInt(command[ptr + 1]);
                    System.out.println("____________________________________________________________");
                    if(taskNumber > list.size()){
                        System.out.println("no such task: task " + taskNumber + " as you only have " + list.size() + " in total");
                    }
                    else{
                        Task task = list.get(taskNumber - 1);
                        task.markAsDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
                    }
                    System.out.println("____________________________________________________________");
                }
                catch (Exception e){
                    System.out.println("\uD83D\uDE43 wrong input after the word \"done\"");
                }
            }
            else if(command[ptr].equals("delete")){
                try{
                    int taskNumber = Integer.parseInt(command[ptr + 1]);
                    System.out.println("____________________________________________________________");
                    if(taskNumber > list.size()){
                        System.out.println("no such task: task " + taskNumber + " as you only have " + list.size() + " in total");
                    }
                    else{
                        Task task = list.remove(taskNumber - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task.toString());
                        System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
                    }
                    System.out.println("____________________________________________________________");
                }
                catch (Exception e){
                    System.out.println("\uD83D\uDE43 wrong input after the word \"delete\"");
                }
            }
            else if(command[ptr].equals("todo")){
                /*String description = getDescription(inputCommand, TaskType.TODO);
                if(description.equals("") || ptr == command.length - 1){
                    System.out.println("____________________________________________________________");
                    System.out.println("\uD83D\uDE43 OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("____________________________________________________________");
                    continue;
                }*/
                Todo newTodo = Todo.of(inputCommand);
                if(newTodo == null){
                    System.out.println("____________________________________________________________");
                    System.out.println("\uD83D\uDE43 OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                list.add(newTodo);
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        newTodo.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
            }
            else if(command[ptr].equals("deadline")){
                /*String by = getTime(inputCommand, TaskType.DEADLINE), description = getDescription(inputCommand, TaskType.DEADLINE);
                if(description.equals("") || by.equals("") || command[command.length - 1].equals("/by") || ptr == command.length - 1){
                    System.out.println("____________________________________________________________");
                    System.out.println("\uD83D\uDE43 OOPS!!! The description and time of a deadline cannot be empty." +
                            " Or maybe you used \"at\" instead of \"by\"?");
                    System.out.println("____________________________________________________________");
                    continue;
                }*/
                Deadline deadline = Deadline.of(inputCommand);
                if(deadline == null){
                    System.out.println("____________________________________________________________");
                    System.out.println("\uD83D\uDE43 OOPS!!! The description and time of a deadline cannot be empty." +
                            " Or maybe you used \"at\" instead of \"by\"?");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                /*System.out.println("____________________________________________________________");
                try{
                    LocalDate date = LocalDate.parse(changeDateFormat(command, TaskType.DEADLINE));
                    deadline.setDate(date);
                }
                catch(Exception e){
                    System.out.println("Wrong date input format. Correct format should be \"YYYY-MM-DD\"");
                }
                try{
                    LocalTime time = LocalTime.parse(getLocalTime(command, TaskType.DEADLINE));
                    deadline.setTime(time);
                }
                catch (Exception e){
                    System.out.println("Wrong time format. Correct format should be \"HH:MM\"");
                }*/
                list.add(deadline);
                System.out.println("____________________________________________________________");
                if(deadline.getDate() == null){
                    System.out.println("Wrong date input format. Correct format should be \"YYYY-MM-DD\"");
                }
                if(deadline.getTime() == null){
                    System.out.println("Wrong time format. Correct format should be \"HH:MM\"");
                }
                System.out.println("Got it. I've added this task:\n" +
                        deadline.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
            }
            else if(command[ptr].equals("event")){
                /*String at = getTime(inputCommand, TaskType.EVENT), description = getDescription(inputCommand, TaskType.EVENT);
                if(description.equals("") || at.equals("") || command[command.length - 1].equals("/at") || ptr == command.length - 1){
                    System.out.println("____________________________________________________________");
                    System.out.println("\uD83D\uDE43 OOPS!!! The description and time of a event cannot be empty." +
                            " Or maybe you used \"by\" instead of \"at\"?");
                    System.out.println("____________________________________________________________");
                    continue;
                }*/
                Event event = Event.of(inputCommand);
                if(event == null){
                    System.out.println("____________________________________________________________");
                    System.out.println("\uD83D\uDE43 OOPS!!! The description and time of a event cannot be empty." +
                            " Or maybe you used \"by\" instead of \"at\"?");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                System.out.println("____________________________________________________________");
                /*try{
                    LocalDate date = LocalDate.parse(changeDateFormat(command, TaskType.EVENT));
                    event.setDate(date);
                }
                catch(Exception e){
                    System.out.println("Wrong date input format. Correct format should be \"YYYY-MM-DD\"");
                }
                try{
                    String s = getLocalTime(command, TaskType.EVENT);
                    System.out.println(s);

                    LocalTime time = LocalTime.parse(getLocalTime(command, TaskType.EVENT));
                    event.setTime(time);
                }
                catch (Exception e){
                    System.out.println("Wrong time format. Correct format should be \"HH:MM\"");
                }*/
                list.add(event);
                if(event.getDate() == null){
                    System.out.println("Wrong date input format. Correct format should be \"YYYY-MM-DD\"");
                }
                if(event.getTime() == null){
                    System.out.println("Wrong time format. Correct format should be \"HH:MM\"");
                }
                System.out.println("Got it. I've added this task:\n" +
                        event.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");
            }
            else{
                /*Task task = new Task(inputCommand);
                list.add(task);
                System.out.println("____________________________________________________________\n" +
                        "Got it. I've added this task:\n" +
                        task.toString() + "\n" +
                        String.format("Now you have %d tasks in the list.", list.size()) +
                        "\n____________________________________________________________");*/
                System.out.println("\uD83D\uDE43 Sorry~ please specify whether this is a todo or a deadline or a event\n" +
                        "put the word \"todo\" or \"deadline\" or \"event\" in front of your description");
            }
        }

        if(!dukeExist){
            new File("./data").mkdir();
        }
        FileWriter fileWriter = new FileWriter("./data/duke.txt");
        writeArrayList2file(list, fileWriter);

        checkAndPrint(list);

        System.out.println("____________________________________________________________\n" +
                           "Bye. Hope to see you again soon!" +
                           "\n____________________________________________________________");
    }
}
