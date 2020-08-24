import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Gel {
    public static void keepingList() {
        // initialise list and scanner
        try {
            FileManager.checkFileExistence();
            TaskList taskList = FileManager.readTaskList();
            Scanner sc = new Scanner(System.in);

            System.out.println("    Hello! I'm Gel\n    What can I do for you?\n");

            label:
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String[] inputArr = input.split(" ");
                String keyword = inputArr[0];
                try {
                    switch (keyword) {
                    case "bye": { //bye
                        FileManager.updateFile(taskList);
                        System.out.println("\n    Bye. Hope to see you again soon!\n");
                        break label;
                    }
                    case "list": { //list
                        taskList.showListOfTask();
                        break;
                    }
                    case "done": { //done
                        taskList.doneTask(input);
                        break;
                    }
                    case "delete": { //delete
                        if (inputArr.length <= 1) {
                            throw new GelException("    Yo tell me what you want to delete la");
                        }
                        try {
                            taskList.deleteTask(inputArr[1]);
                            break;
                        } catch (Exception e) {
                            throw new GelException("    Yoyoyo please input a valid number after delete");
                        }
                    }
                    case "deadline": {//deadline
                        int dateIndex = input.lastIndexOf("/");
                        if (dateIndex == -1) {
                            throw new GelException("    Bruh you need the /by tag for deadlines");
                        } else {
                            String[] dateDetails = input.substring(dateIndex).split(" ");
                            String checkBy = dateDetails[0];
                            if (!checkBy.equals("/by")) {
                                throw new GelException("    Bruh you need the /by tag for deadlines");
                            } else if (dateDetails.length <= 1) {
                                throw new GelException("    Bruh you left out the deadline");
                            }
                        }
                        taskList.addDeadline(input, dateIndex);
                        break;
                    }
                    case "event": {//event
                        int dateIndex = input.lastIndexOf("/");
                        if (dateIndex == -1) {
                            throw new GelException("    Bruh you need the /at tag for events");
                        } else {
                            String[] dateDetails = input.substring(dateIndex).split(" ");
                            String checkAt = dateDetails[0];
                            if (!checkAt.equals("/at")) {
                                throw new GelException("    Bruh you need the /at tag for events");
                            } else if (dateDetails.length <= 1) {
                                throw new GelException("    Bruh you left out the event date");
                            }
                        }
                        taskList.addEvent(input, dateIndex);
                        break;
                    }
                    case "todo": {
                        if (inputArr.length <= 1) {
                            throw new GelException("    Yo tell me what you want todo");
                        }
                        taskList.addTodo(input);
                        break;
                    }
                    default: {
                        throw new GelException();
                    }
                    }

                } catch (GelException e) {
                    System.out.println(e.getMessage());
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
