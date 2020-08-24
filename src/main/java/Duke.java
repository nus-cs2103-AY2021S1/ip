import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static boolean isInteger(String s) {
        if(s == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static ArrayList<Task> loadData() throws IOException {
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ArrayList<Task> orderList = new ArrayList<>();

        try {
            File dataStorage = new File("data/duke.txt");
            Scanner s = new Scanner(dataStorage);
            while (s.hasNext()) {
                String curr = s.nextLine();
                String[] currTask = curr.split("|");
                Boolean isDone = currTask[1] == "1";
                if (currTask[0] == "T") {
                    orderList.add(new Todo(currTask[2], isDone));
                } else if(currTask[0] == "D") {
                    orderList.add(new Deadline(currTask[2],
                            LocalDateTime.parse(currTask[3], validFormat), isDone));
                } else if(currTask[0] == "E") {
                    orderList.add(new Event(currTask[2],
                            LocalDateTime.parse(currTask[3], validFormat), isDone));
                }
            }
        } catch (FileNotFoundException e) {
            if (new File("data").mkdir()) {
                System.out.println("folder data does not exist yet.");
            } else if(new File("data/duke.txt").createNewFile()) {
                System.out.println("File duke.txt does not exist yet.");
            }
        }

        return orderList;

    }

    public static void writeData(ArrayList<Task> orderlist) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt", false);
            for (Task task : orderlist) {
                fw.write(task.fileFormattedString() + "\n");
            }
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Scanner sc =  new Scanner(System.in);
        String seperateLine = "    _______________________________________";
        String spaceBeforeOder = "      ";
        System.out.println(seperateLine);
        System.out.println(spaceBeforeOder + "Hello! I'm Duke yy\n      What can I do for you?");
        System.out.println(seperateLine);
        String order = sc.nextLine();
        int numOfOders = 0;
        try {
            ArrayList<Task> orderList = loadData();
            while (!order.equals("bye")) {
                if (order.equals("list")) {
                    System.out.println(seperateLine);
                    System.out.println(spaceBeforeOder + "Here are the tasks in your list:");
                    for (int i = 0; i < numOfOders; i++){
                        System.out.println(spaceBeforeOder + (i + 1) + ". " +
                                orderList.get(i));
                    }
                    System.out.println(seperateLine);
                } else if (order != null){
                    String[] command = order.split(" ", 2);
                    if(command[0].equals("done")) {
                        if(command.length == 1) {
                            throw new NoTaskChosenException(command[0]);
                        } else if(!isInteger(command[1]) || Integer.parseInt(command[1]) > numOfOders){
                            throw new NoThisNumOfTaskException();
                        } else {
                            int toMark = Integer.parseInt(command[1]) - 1;
                            orderList.get(toMark).markDone();
                            System.out.println(seperateLine);
                            System.out.println(spaceBeforeOder + "Nice! I've marked this task as done:");
                            System.out.println(spaceBeforeOder + "  " + orderList.get(toMark));
                            writeData(orderList);
                            System.out.println(spaceBeforeOder + "Now you have " + numOfOders + " tasks in the list.");
                            System.out.println(seperateLine);
                        }
                    } else if(command[0].equals("delete")) {
                        if(command.length == 1) {
                            throw new NoTaskChosenException(command[0]);
                        } else if(!isInteger(command[1]) || Integer.parseInt(command[1]) > numOfOders){
                            throw new NoThisNumOfTaskException();
                        } else {
                            Task removed = orderList.remove(Integer.parseInt(command[1]) - 1);
                            System.out.println(seperateLine);
                            System.out.println(spaceBeforeOder + "Noted. I've removed this task:");
                            System.out.println(spaceBeforeOder + "  " + removed);
                            writeData(orderList);
                            System.out.println(spaceBeforeOder + "Now you have " + (numOfOders - 1) + " tasks in the list.");
                            System.out.println(seperateLine);
                            numOfOders--;
                        }
                    }
                    else {
                        if(command.length == 1) {
                            throw new NoDescriptionException(command[0]);
                        }else if(command[0].equals("deadline")) {
                            String[] splitAgain = command[1].split("/by ");
                            if (splitAgain.length == 1) {
                                throw new NoTimeException(command[0]);
                            }
                            orderList.add(new Deadline(splitAgain[0], LocalDateTime.parse(splitAgain[1], validFormat), false));
                        } else if(command[0].equals("event")) {
                            String[] splitAgain = command[1].split("/at ");
                            if (splitAgain.length == 1) {
                                throw new NoTimeException(command[0]);
                            }
                            orderList.add(new Event(splitAgain[0], LocalDateTime.parse(splitAgain[1], validFormat), false));
                        } else if(command[0].equals("todo")) {
                            orderList.add( new Todo(command[1], false));
                        } else {
                            throw new NoSuchOrderException();
                        }

                        System.out.println(seperateLine);
                        System.out.println(spaceBeforeOder + "Got it. I've added this task: ");
                        System.out.println(spaceBeforeOder + "  " + orderList.get(numOfOders));
                        writeData(orderList);
                        System.out.println(spaceBeforeOder + "Now you have " + (numOfOders + 1) + " tasks in the list.");
                        System.out.println(seperateLine);
                        numOfOders++;
                    }

                }

                order = sc.nextLine();
            }
        } catch (NoDescriptionException | NoTimeException | NoSuchOrderException | NoTaskChosenException | NoThisNumOfTaskException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(seperateLine);
        System.out.println(spaceBeforeOder + "Bye. Very nice to meet you!");
        System.out.println(spaceBeforeOder + "Hope to see you again soon! ");
        System.out.println(seperateLine);
        sc.close();
    }

}
