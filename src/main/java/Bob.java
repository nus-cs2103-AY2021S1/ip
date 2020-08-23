import main.java.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Bob {
    static ArrayList<Task> list = new ArrayList<>();
    static File save = new File("data/save.txt");
    static FileWriter writer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings = "Hello! I'm Bob\nWhat can I do for you?";
        String exit = "Bye! Hope to see you again.";
        System.out.println("Hello from\n" + logo);
        System.out.println(greetings);
        String command = sc.nextLine();

        File data = new File("data");
        if (!data.exists()) {
            data.mkdir();
        }

        try {
            save.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer = new FileWriter(save, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loadSave();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(!command.equals("bye")) {
            try {
                respond(command);
            } catch (BobNoDescriptionException e) {
                System.out.println("Please include a description for this todo!");
            } catch (BobIncompleteDeadlineDescriptionException e) {
                System.out.println("The description for this deadline is incomplete. Please remember to include a brief description alongside a due date.");
                System.out.println("Here's the format: deadline [brief description] /by [due date]");
            } catch (BobIncompleteEventDescriptionException e) {
                System.out.println("The description for this event is incomplete. Please remember to include a brief description alongside the period of this event.");
                System.out.println("Here's the format: event [brief description] /at [period]");
            }  catch(IndexOutOfBoundsException e) {
                if (list.isEmpty()) {
                    System.out.println("There aren't any tasks on the list!");
                } else {
                    System.out.println("There are no tasks on the list with the provided index. Please check the list and try again!");
                }
            } catch (NumberFormatException e) {
              System.out.println("Please provide the index of a task on the list to mark it as done or to delete it.");
              System.out.println("Here's the format: delete/done [index]");
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry, I do not understand your request. Please try again.");
            } catch (IOException e) {
                System.out.println("Sorry, but I had difficulty saving your information. Please try again.");
            } catch (DateTimeParseException e) {
                System.out.println("Please input dates and times in the correct format. The format is: ");
                System.out.println("yyyy-MM-dd HHMM");
                System.out.println("Note: Events require a start date and time and an end date and time with the following format:");
                System.out.println("yyyy-MM-dd HHMM to yyyy-MM-dd HHMM");
            }
            command = sc.nextLine();
        }
        System.out.println(exit);
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void respond(String command) throws IOException {
        if (command.equals("list")) {
            int count = 1;
            for(Task item: list) {
                System.out.println(count +"." + item.toString());
                count++;
            }
            if(list.isEmpty()) {
                System.out.println("There are no tasks in the list at the moment. Feel free to add any.");
            }
        } else if(command.contains("done")) {
            int index = Integer.parseInt(command.substring(command.length()-1));
            Task task = list.get(index-1);
            task.markAsDone();
            System.out.println("Good job! I have marked this task as done:");
            System.out.println("\t" + index + "." + task.toString());
            updateSave();
        } else if (command.contains("todo") || command.contains("deadline") || command.contains("event")){
            Task task = null;
            if (command.contains("todo")) {
                try {
                    task = new Todo(command.substring(5));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BobNoDescriptionException();
                }
            } else if (command.contains("deadline")) {
                try {
                    int index = command.indexOf(47);
                    if (command.substring(9,index).equals("")) {
                        throw new BobIncompleteDeadlineDescriptionException();
                    }
                    task = new Deadline(command.substring(9, index-1), command.substring(index + 4));
                } catch (StringIndexOutOfBoundsException e){
                    throw new BobIncompleteDeadlineDescriptionException();
                }
            } else if(command.contains("event")) {
                try {
                    int index = command.indexOf(47);
                    if (command.substring(6,index).equals("")) {
                        throw new BobIncompleteEventDescriptionException();
                    }
                    task = new Event(command.substring(6, index-1), command.substring(index + 4));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new BobIncompleteEventDescriptionException();
                }
            }
            list.add(task);
            System.out.println("Got it! I have added a new task to the list.");
            System.out.println("added: " + task.toString());
            writer.append(task.saveFormat() + System.lineSeparator());
            writer.flush();

        } else if (command.contains("delete")) {
            int index = Integer.parseInt(command.substring(command.length()-1));
            Task removed = list.get(index-1);
            list.remove(index-1);
            System.out.println("Noted. I have removed the following task: ");
            System.out.println("\t" + removed.toString());
            System.out.println("There are now " + list.size() + " remaining tasks on the list.");
            updateSave();
        } else {
            throw new IllegalArgumentException();
        }
    }

    static void loadSave() throws FileNotFoundException {
        Scanner sc = new Scanner(save);

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            char c = str.charAt(0);
            if (c == 'T') {
                String bool = str.substring(4,5);
                String description = str.substring(8);
                list.add(new Todo(bool.equals("1") ? true : false, description));
            } else if (c == 'D') {
                String bool = str.substring(4,5);
                String description = str.substring(8, str.indexOf("|", 8) - 1);
                int i = str.indexOf("|", 8);
                String deadline = str.substring(i + 2);
                list.add(new Deadline(bool.equals("1") ? true : false, description, deadline));
            } else if (c == 'E') {
                String bool = str.substring(4,5);
                String description = str.substring(8, str.indexOf("|", 8 ) - 1);
                int i = str.indexOf("|", 8);
                String period = str.substring(i + 2);
                list.add(new Event(bool.equals("1") ? true : false, description, period));
            }
        }
    }

    static void updateSave() throws IOException {
        FileWriter deleter = new FileWriter(save);

        for(Task task : list) {
            deleter.append(task.saveFormat() + System.lineSeparator());
        }
        deleter.close();
    }


}
