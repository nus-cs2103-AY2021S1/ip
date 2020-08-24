import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    public static void main(String[] args) throws DukeException {
        try {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            System.out.println("Hello! I'm Duke \n"
                    + "What can I do for you?");
            Scanner sc = new Scanner(System.in);
            //List<Input> inputs = new ArrayList<>();
            List<Input> inputs = Storage.readFile();
            while (true) {
                String nextLine = sc.nextLine();
                if (nextLine.startsWith("done")) {
                    if (nextLine.equals("done") || nextLine.equals("done ")) {
                        throw new DukeException("OOPS!!! The description of done cannot be empty.");
                    }
                    int numTaskDone = Integer.valueOf(nextLine.substring(5));
                    if (numTaskDone > inputs.size()) {
                        throw new DukeException("OOPS!!! Task does not exist.");
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    Input inputType = inputs.get(numTaskDone - 1);
                    inputType.taskDone();
                    System.out.println("[/] " + inputType.content + " " + inputType.printTime);
                } else if (nextLine.startsWith("remove")) {
                    if (nextLine.equals("remove") || nextLine.equals("remove ")) {
                        throw new DukeException("OOPS!!! The description of remove cannot be empty");
                    }
                    int numTaskDone = Integer.valueOf(nextLine.substring(7));
                    if (numTaskDone > inputs.size()) {
                        throw new DukeException("OOPS!!! Task does not exist.");
                    }
                    System.out.println("Noted. I've removed this task:");
                    Input inputType = inputs.get(numTaskDone -1);
                    if (inputType.done) {
                        System.out.println("  " + inputType.id + "[/] " + inputType.content + inputType.printTime);
                    } else {
                        System.out.println("  " + inputType.id + "[x] " + inputType.content + inputType.printTime);
                    }
                    inputs.remove(numTaskDone -1);
                    System.out.println("Now you have " + inputs.size() + " tasks in the list.");
                    Storage.writeToFile(inputs);
                } else if (nextLine.startsWith("todo")) {
                     if (nextLine.equals("todo") || nextLine.equals("todo ")) {
                         throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                     }
                     Todo todo = new Todo(nextLine.substring(5));
                     inputs.add(todo);
                     int count = inputs.size();
                     System.out.println("Got it. I've added this task: \n" + "  [T][x] " + todo.content +
                             "\n Now you have " + count + " tasks in the list");
                    Storage.writeToFile(inputs);
                } else if (nextLine.startsWith("deadline")) {
                    if (nextLine.equals("deadline") || nextLine.equals("deadline ")) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    int charLoc = nextLine.indexOf("/by");
                    Deadline deadline = new Deadline(nextLine.substring(9, charLoc), nextLine.substring(charLoc + 4));
                    inputs.add(deadline);
                    int count = inputs.size();
                    System.out.println("Got it. I've added this task: \n" + "  [D][x] " + deadline.content +
                            deadline.printTime + "\n Now you have " + count + " tasks in the list");
                    Storage.writeToFile(inputs);
                } else if (nextLine.startsWith("event")) {
                    if (nextLine.equals("event") || nextLine.equals("event ")) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }
                    int charLoc = nextLine.indexOf("/at");
                    Event event = new Event(nextLine.substring(6, charLoc), nextLine.substring(charLoc + 4));
                    inputs.add(event);
                    int count = inputs.size();
                    System.out.println("Got it. I've added this task: \n" + "  [E][x] " + event.content +
                            event.printTime + "\n Now you have " + count + " tasks in the list");
                    Storage.writeToFile(inputs);
                } else if (nextLine.equals("list")) {
                    if (inputs.size() == 0) {
                        System.out.println("No tasks in list");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        int len = inputs.size();
                        for (int i = 1; i <= len; i++) {
                            Input inputType = inputs.get(i - 1);
                            if (inputType.done) {
                                System.out.println(i + ". " + inputType.id + "[/] " + inputType.content +
                                        inputType.printTime);
                            } else {
                                System.out.println(i + ". " + inputType.id + "[x] " + inputType.content + 
                                        inputType.printTime);
                            }
                        }
                    }
                } else if (nextLine.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        
    } catch (DukeException e) {
            System.out.println(e.msg);
        }
    }
        
    public static class Input implements Serializable {
        boolean done;
        String content;
        String id;
        LocalDate time;
        String printTime;

        Input(String content) {
            this.content = content;
            boolean done = false;
        }
        Input(boolean done, String content) {
            this.done = done;
            this.content = content;
        }

        public void taskDone() {
            this.done = true;
        }
    }

    static class Todo extends Input {

        Todo(String content) {
            super(content);
            this.id = "[T]";
            this.time = null;
            this.printTime = "";
        }

        Todo(boolean done, String content) {
            super(done, content);
            this.id = "[T]";
            this.time = null;
            this.printTime = "";
        }
    }

    static class Deadline extends Input {

        Deadline(String content, String deadlineTime) {
            super(content);
            this.time = LocalDate.parse(deadlineTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.id = "[D]";
            this.printTime = "(" + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }

        Deadline(boolean done, String content, String deadlineTime) {
            super(done, content);
            this.time = LocalDate.parse(deadlineTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.id = "[D]";
            this.printTime = "(" + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    static class Event extends Input {

        Event(String content, String eventTime) {
            super(content);
            this.time = LocalDate.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.id = "[E]";
            this.printTime = "(" + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }

        Event(boolean done, String content, String eventTime) {
            super(done, content);
            this.time = LocalDate.parse(eventTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.id = "[E]";
            this.printTime = "(" + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
    
    
    public static class DukeException extends Exception {
        String msg; 
        
        DukeException(String msg) {
            this.msg = msg;
        }
    }
    
    public static class Storage {
        static String filepath = "listStore.ser";
                
        static void writeToFile(List<Input> list) {
            try {
                FileOutputStream writeData = new FileOutputStream(filepath);
                ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
                writeStream.writeObject(list);
                writeStream.flush();
                writeStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        static List<Input> readFile() {
            try {
                FileInputStream readData = new FileInputStream(filepath);
                ObjectInputStream readStream = new ObjectInputStream(readData);
                List<Input> inputList = (List<Input>) readStream.readObject();
                readStream.close();
                return inputList;
            } catch (FileNotFoundException e) {
                return new ArrayList<>();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        
     }
    

}





    
