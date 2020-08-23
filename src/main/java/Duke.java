import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static final String DATAFILEDIRECTORY = System.getProperty("user.dir") + (System.getProperty("user.dir").endsWith("text-ui-test") ? "/../src/main/java/data/" : "/src/main/java/data/");

    public static List<Task> loadData(File inputFile) throws IOException {
        BufferedReader rb = null;
        try {
            rb = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String newLine = null;
        List<Task> history = new ArrayList<>();
        try {
            newLine = rb.readLine();
            while (newLine != null) {
                String[] taskInput = newLine.split("\\|");
                if (taskInput[0].charAt(0) == 'T') {
                    ToDo pastToDo = new ToDo(taskInput[2].trim());
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastToDo.markDone();
                    }
                    history.add(pastToDo);
                    newLine = rb.readLine();
                } else if (newLine.charAt(0) == 'E') {
                    Event pastEvent = new Event(taskInput[2].trim(), taskInput[3].trim());
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastEvent.markDone();
                    }
                    history.add(pastEvent);
                    newLine = rb.readLine();
                } else if (newLine.charAt(0) == 'D') {
                    Deadline pastDeadline = new Deadline(taskInput[2].trim(), taskInput[3].trim());
                    if (Integer.parseInt(taskInput[1].trim()) == 1) {
                        pastDeadline.markDone();
                    }
                    history.add(pastDeadline);
                    newLine = rb.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        rb.close();
        return history;
    }

    public static void main(String[] args) {
        String logo = " .d8888b.  888               888    888                  888888b.            888    \n" +
                "d88P  Y88b 888               888    888                  888  \"88b           888    \n" +
                "888    888 888               888    888                  888  .88P           888    \n" +
                "888        88888b.   8888b.  888888 888888 888  888      8888888K.   .d88b.  888888 \n" +
                "888        888 \"88b     \"88b 888    888    888  888      888  \"Y88b d88\"\"88b 888    \n" +
                "888    888 888  888 .d888888 888    888    888  888      888    888 888  888 888    \n" +
                "Y88b  d88P 888  888 888  888 Y88b.  Y88b.  Y88b 888      888   d88P Y88..88P Y88b.  \n" +
                " \"Y8888P\"  888  888 \"Y888888  \"Y888  \"Y888  \"Y88888      8888888P\"   \"Y88P\"   \"Y888 \n" +
                "                                                888                                 \n" +
                "                                           Y8b d88P                                 \n" +
                "                                            \"Y88P\"                                  ";
        System.out.println("Hello from\n" + logo);
        String lines = "    ____________________________________________________________";
        String defaultGreeting = lines + "\n" + "     Hello! I'm Chatty Bot \n" + "     What can I do for you?\n" + lines + "\n";
        System.out.println(defaultGreeting);

        try {
            FileReader readFile = new FileReader(DATAFILEDIRECTORY);
        } catch (FileNotFoundException e) {
            File newData = new File(DATAFILEDIRECTORY);
            if(!newData.exists()) {
                newData.mkdirs();
                System.out.println(lines);
                System.out.println("     I see that you do not have a directory to store data. " +
                        "Created one for you before we proceed.");
                System.out.println(lines);
            }
        }

        try{
            File dataFile = new File(DATAFILEDIRECTORY + "dataFile.txt");
            if(dataFile.createNewFile()) {
                System.out.println(lines);
                System.out.println("     I see that this is your first time using Chatty Bot, " +
                        "I have created a file to log your history from now on!");
                System.out.println(lines);
            }
        } catch (IOException e) {
            System.out.println("Unable to create file");
        }

        File dataList = new File(DATAFILEDIRECTORY + "dataFile.txt");

        List<Task> currentList = new ArrayList<>();
        try {
            currentList = loadData(dataList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(lines);
        System.out.println("     Previously saved list (if any) loaded. You may enter your commands now:");
        System.out.println(lines);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")){
            try{
                if (input.contains("done")) {
                    String[] doneInputs = input.split(" ");
                    if (doneInputs.length == 1) {
                        throw new invalidCommand("OOPS!!! Please enter at least 1 task number");
                    } else if (doneInputs.length > 2) {
                        throw new invalidCommand("OOPS!!! Please enter 1 task number only");
                    } else if (doneInputs.length == 2) {
                        try{
                            Integer.parseInt(doneInputs[1]);
                            int taskToDo = Integer.parseInt(doneInputs[1]) - 1;
                            Task editedTask = currentList.get(taskToDo);

                            File toBeDeleted = new File (DATAFILEDIRECTORY + "dataList1.txt");
                            BufferedReader readerBuffer = new BufferedReader(new FileReader(dataList));
                            BufferedWriter writerBuffer = new BufferedWriter(new FileWriter(toBeDeleted));
                            String readingLine = readerBuffer.readLine();
                            String lineToEdit = "";
                            String lineToChangeTo = "";

                            if (editedTask instanceof Deadline) {
                                lineToEdit = ((Deadline) editedTask).dataStorage();
                                currentList.get(taskToDo).markDone();
                                lineToChangeTo = ((Deadline) editedTask).dataStorage();
                            } else if (editedTask instanceof Event) {
                                lineToEdit = ((Event) editedTask).dataStorage();
                                currentList.get(taskToDo).markDone();
                                lineToChangeTo = ((Event) editedTask).dataStorage();
                            } else {
                                lineToEdit = ((ToDo) editedTask).dataStorage();
                                currentList.get(taskToDo).markDone();
                                lineToChangeTo = ((ToDo) editedTask).dataStorage();
                            }

                            while (readingLine != null) {
                                if (readingLine.equals(lineToEdit)) {
                                    writerBuffer.write(lineToChangeTo + "\n");
                                    readingLine = readerBuffer.readLine();
                                    continue;
                                }
                                writerBuffer.write(readingLine + "\n");
                                readingLine = readerBuffer.readLine();
                            }

                            writerBuffer.close();
                            readerBuffer.close();

                            if (dataList.delete()) {
                                // Rename the output file to the input file
                                if (!toBeDeleted.renameTo(dataList)) {
                                    throw new IOException("Could not rename to update data file");
                                }
                            } else {
                                throw new IOException("Could not delete old data file");
                            }

                            System.out.println(lines);
                            System.out.println("     Nice! I've marked this task as done:");
                            System.out.println("       " + currentList.get(taskToDo));
                            System.out.println(lines);
                        } catch(NumberFormatException ex) {
                            throw new invalidCommand("Please enter the task number instead of task name.");
                        } catch (IndexOutOfBoundsException ex) {
                            throw new invalidCommand("Please enter a valid task number.");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (input.contains("todo")) {
                    //process todo task
                    try {
                        String taskToDo = input.substring(5);
                        if (taskToDo.length() == 0) {
                            throw new invalidCommand("OOPS!!! Please specify your task.");
                        }
                        ToDo newToDo = new ToDo(taskToDo);
                        currentList.add(newToDo);

                        FileWriter fw = new FileWriter(dataList,true);
                        fw.write("T | 0 | " + taskToDo + "\n");
                        fw.close();

                        System.out.println(lines + "\n" + "     Got it. I've added this task:");
                        System.out.println("       " + newToDo);
                        System.out.println("     Now you have " + String.valueOf(currentList.size()) + " task(s) in the list.");
                        System.out.println(lines);
                    } catch(StringIndexOutOfBoundsException ex) {
                        throw new invalidCommand("OOPS!!! Please specify your task.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (input.contains("deadline")) {
                    try {
                        String deadlineInput = input.substring(9);
                        int indexSeparator = deadlineInput.indexOf("/by");
                        if (indexSeparator == -1) {
                            throw new invalidCommand("Please include your deadline using /by !");
                        }
                        String deadlineTaskName = deadlineInput.substring(0, indexSeparator - 1);
                        String deadlineTime = deadlineInput.substring(indexSeparator + 4);
                        Deadline newDeadline = new Deadline(deadlineTaskName, deadlineTime);
                        currentList.add(newDeadline);

                        FileWriter fw = new FileWriter(dataList,true);
                        fw.write("D | 0 | " + deadlineTaskName + " | " + deadlineTime + "\n");
                        fw.close();

                        System.out.println(lines + "\n" + "     Got it. I've added this task:");
                        System.out.println("       " + newDeadline);
                        System.out.println("     Now you have " + String.valueOf(currentList.size()) + " task(s) in the list.");
                        System.out.println(lines);
                    } catch (StringIndexOutOfBoundsException ex) {
                        throw new invalidCommand("OOPS!!! Please specify your task.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (input.contains("event")) {
                    try {
                        String eventInput = input.substring(6);
                        int indexSeparator = eventInput.indexOf("/at");
                        if (indexSeparator == -1) {
                            throw new invalidCommand("Please include your event date using /at !");
                        }
                        String eventTaskName = eventInput.substring(0, indexSeparator - 1);
                        String eventTime = eventInput.substring(indexSeparator + 4);
                        Event newEvent = new Event(eventTaskName, eventTime);
                        currentList.add(newEvent);

                        FileWriter fw = new FileWriter(dataList,true);
                        fw.write("E | 0 | " + eventTaskName + " | " + eventTime + "\n");
                        fw.close();

                        System.out.println(lines + "\n" + "     Got it. I've added this task:");
                        System.out.println("       " + newEvent);
                        System.out.println("     Now you have " + String.valueOf(currentList.size()) + " task(s) in the list.");
                        System.out.println(lines);
                    } catch (StringIndexOutOfBoundsException ex) {
                        throw new invalidCommand("OOPS!!! Please specify your task.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (input.contains("list")){
                    if (input.split(" ").length > 1) {
                        throw new invalidCommand("You have to view your entire to-do list!");
                    }
                    //list out items in to-do list
                    System.out.println(lines);
                    System.out.println("     Here are the tasks in your list:");
                    for(int i = 0; i < currentList.size(); i++) {
                        String currentLine = "      "+ (i + 1) + ". " + currentList.get(i);
                        System.out.println(currentLine);
                    }
                    System.out.println(lines);
                } else if (input.contains("delete")) {
                    try {
                        String[] deleteInput = input.split(" ");
                        if (deleteInput.length > 2) {
                            throw new invalidCommand("Please only input 1 task number!");
                        }
                        int deleteIndex = Integer.parseInt(deleteInput[1]);
                        Task removedTask = currentList.remove(deleteIndex - 1);

                        File removed = new File (DATAFILEDIRECTORY + "dataList1.txt");
                        BufferedReader reader = new BufferedReader(new FileReader(dataList));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(removed));
                        String currentLine = reader.readLine();
                        String lineToRemove = "";

                        if (removedTask instanceof Deadline) {
                            lineToRemove = ((Deadline) removedTask).dataStorage();
                        } else if (removedTask instanceof Event) {
                            lineToRemove = ((Event) removedTask).dataStorage();
                        } else {
                            lineToRemove = ((ToDo) removedTask).dataStorage();
                        }

                        while (currentLine != null) {
                            if (currentLine.equals(lineToRemove)) {
                                currentLine = reader.readLine();
                                continue;
                            }
                            writer.write(currentLine + "\n");
                            currentLine = reader.readLine();
                        }

                        writer.close();
                        reader.close();

                        if (dataList.delete()) {
                            // Rename the output file to the input file
                            if (!removed.renameTo(dataList)) {
                                throw new IOException("Could not rename to update data file");
                            }
                        } else {
                            throw new IOException("Could not delete old data file");
                        }

                        System.out.println(lines);
                        System.out.println("     Alright, the following task has been removed");
                        System.out.println("     " + removedTask);
                        System.out.println("     Now you have " + String.valueOf(currentList.size()) + " task(s) in the list.");
                        System.out.println(lines);
                    } catch(IndexOutOfBoundsException ex) {
                        throw new invalidCommand("Please enter a valid task number.");
                    } catch (NumberFormatException ex) {
                        throw new invalidCommand("Please enter a valid task number");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new invalidCommand("OOPS!!! I don't know what that means :-(");
                }
            } catch(invalidCommand ex) {
                System.out.println(lines);
                System.out.println("     "  + ex);
                System.out.println(lines);
            }
            input = sc.nextLine();
        }
        //greet user goodbye
        String end_Greeting = lines + "\n" + "     Bye. Hope to see you again soon!\n" + lines;
        System.out.println(end_Greeting);

    }
}
