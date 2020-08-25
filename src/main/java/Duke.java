import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    
    public static void main(String[] args) throws MissingTaskDescriptionException,
            MissingTaskNumberException, CommandNotRecognisedException, IOException {
        
//        // create new File
//        String path = "./data/duke.txt";
//        File f = new File(path);
//        boolean dir = f.getParentFile().mkdirs();
//        if (dir) {
//            System.out.println("directory created");
//        } else {
//            System.out.println("directory exist");
//        }
        
        try {
            String path = "./data/duke.txt";
            File f = new File(path);
            boolean dir = f.getParentFile().mkdirs();
            
            if (dir) {
                FileOutputStream fileOutput = new FileOutputStream("./data/duke.txt");
//                String s="dir success";
//                byte b[]=s.getBytes();//converting string into byte array    
//                fileOutput.write(b);
//                fileOutput.close();
                System.out.println("dir success");
            } else {
                boolean file = f.createNewFile();
                if (file) {
                    FileOutputStream fileOutput = new FileOutputStream("./data/duke.txt");
//                    String s="dir fail file success.";
//                    byte b[]=s.getBytes();//converting string into byte array    
//                    fileOutput.write(b);
//                    fileOutput.close();
//                    System.out.println("dir fail file success");
                } else {
//                    System.out.println("dir fail file fail");
                    byte[] bytes = Files.readAllBytes(Paths.get(path));
                    List<String> allLines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
                }
            }
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
//        boolean file = f.createNewFile();
//        if (file) {
//            System.out.println("file created");
//        } else {
//            System.out.println("file exist");
//        }
//                
        // Contains all tasks
        ArrayList<Task> listOfTasks= new ArrayList<>();
        
        // Introduction of Mocha
        String horizontalLine = "_______________________________________________________";
        String nameIntro = "Hello, I'm Mocha!";
        String greeting = "What's up today!";
        System.out.println(horizontalLine
                + "\r\n"
                + nameIntro
                + "\r\n"
                + greeting
                + "\r\n"
                + horizontalLine);
        
        Scanner userInput = new Scanner(System.in);
        
        while (userInput.hasNextLine()) {
            try {
                String nextLine = userInput.nextLine();
                String[] commandParts = nextLine.split("\\s", 2);

                if (commandParts[0].contains("todo")) {

                    Task newToDoTask= createNewToDo(nextLine);
                    listOfTasks.add(newToDoTask);

                    System.out.println(horizontalLine
                            + "\r\n"
                            + "One new ToDo Task added: "
                            + "\r\n"
                            + newToDoTask.toString()
                            + "\r\n"
                            + "Total number of tasks in list: "
                            + listOfTasks.size()
                            + "\r\n"
                            + horizontalLine);

                } else if (commandParts[0].contains("deadline")) {

                    Task newDeadlineTask = createNewDeadline(nextLine);
                    listOfTasks.add(newDeadlineTask);
                    System.out.println(horizontalLine
                            + "\r\n"
                            + "One new Deadline added: "
                            + "\r\n"
                            + newDeadlineTask.toString()
                            + "\r\n"
                            + "Total number of tasks in list: "
                            + listOfTasks.size()
                            + "\r\n"
                            + horizontalLine);

                } else if (commandParts[0].contains("event")) {

                    Task newEventTask = createNewTask(nextLine);
                    listOfTasks.add(newEventTask);
                    System.out.println(horizontalLine
                            + "\r\n"
                            + "One new Deadline Task added: "
                            + "\r\n"
                            + newEventTask.toString()
                            + "\r\n"
                            + "Total number of tasks in list: "
                            + listOfTasks.size()
                            + "\r\n"
                            + horizontalLine);

                } else if (commandParts[0].contains("done")) {

                    int taskNumber = markDoneTask(nextLine);
                    Task doneTask = listOfTasks.get(taskNumber);
                    doneTask.markAsDone();
                    System.out.println(horizontalLine
                            + "\r\n"
                            + "Nice! One thing done: \r\n"
                            + doneTask.toString()
                            + "\r\n"
                            + horizontalLine);

                } else if (commandParts[0].contains("list")) {

                    System.out.println(horizontalLine
                            + "\r\n"
                            + "Here are all of your tasks:"
                            + "\r\n");

                    for (int i = 0; i < listOfTasks.size(); i++) {
                        System.out.println((i + 1) + "." + listOfTasks.get(i).toString());
                    }

                    System.out.println("\r\n"
                            + "You have a total of "
                            + listOfTasks.size()
                            + " tasks."
                            + "\r\n"
                            + horizontalLine);

                } else if (commandParts[0].contains("bye")) {
                    System.out.println(horizontalLine
                            + "\r\n"
                            + "Bye! See ya soon!"
                            + "\r\n"
                            + horizontalLine);
                    
                } else if (commandParts[0].contains("delete")) {

                    int taskNumber = Integer.parseInt(commandParts[1].trim()) - 1;
                    Task deleteTask = listOfTasks.get(taskNumber);
                    listOfTasks.remove(taskNumber);
                    System.out.println(horizontalLine
                            + "\r\n"
                            + "Noted. Removing the following task:"
                            + "\r\n"
                            + deleteTask.toString()
                            + "\r\n"
                            + "Total number of tasks left in the list: "
                            + listOfTasks.size()
                            + "\r\n"
                            + horizontalLine);

                } else {
                    throw new CommandNotRecognisedException(horizontalLine
                            + "\r\n"
                            + "Oops! I couldn't understand what you mean :("
                            + "\r\n"
                            + horizontalLine);
                }
            } catch (MissingTaskDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (MissingTaskNumberException e) {
                System.out.println(e.getMessage());
            } catch (CommandNotRecognisedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Task createNewToDo(String nextLine) throws MissingTaskDescriptionException {
        try {
            
            String[] commandParts = nextLine.split("\\s", 2);
            Task newToDoTask = new ToDo(commandParts[1]);
            return newToDoTask;
            
        } catch (Exception e) {
            String horizontalLine = "_______________________________________________________";

            throw new MissingTaskDescriptionException(horizontalLine
                    + "\r\n"
                    + "Oops! The description cannot be empty :("
                    + "\r\n"
                    + horizontalLine);
        }
    }

    private static Task createNewDeadline(String nextLine) throws MissingTaskDescriptionException {
        try {
            
            String[] commandParts = nextLine.split("\\s", 2);
            String[] deadlineParts = commandParts[1].split("/by");
            Task newDeadlineTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            return newDeadlineTask;
            
        } catch (Exception e) {
            String horizontalLine = "_______________________________________________________";

            throw new MissingTaskDescriptionException(horizontalLine
                    + "\r\n"
                    + "Oops! The description cannot be empty :("
                    + "\r\n"
                    + horizontalLine);
        }
    }

    private static Task createNewTask(String nextLine) throws MissingTaskDescriptionException {
        try {
            
            String[] commandParts = nextLine.split("\\s", 2);
            String[] eventParts = commandParts[1].split("/at");
            Task newEventTask = new Event(eventParts[0].trim(), eventParts[1].trim());
            return newEventTask;
            
        } catch (Exception e) {
            String horizontalLine = "_______________________________________________________";

            throw new MissingTaskDescriptionException(horizontalLine
                    + "\r\n"
                    + "Oops! The description cannot be empty :("
                    + "\r\n"
                    + horizontalLine);
        }
    }


    private static int markDoneTask(String nextLine) throws MissingTaskNumberException {
      try {
          
          String[] commandParts = nextLine.split("\\s", 2);
          int taskNumber = Integer.parseInt(commandParts[1].trim()) - 1;
          return taskNumber;
          
      } catch (Exception e) {

          String horizontalLine = "_______________________________________________________";
          
          throw new MissingTaskNumberException(horizontalLine 
                  + "\r\n"
                  + "Oops! The task number cannot be missing :("
                  + "\r\n"
                  + horizontalLine);
      }
    } 
}

