package main.java;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.util.zip.DataFormatException;

class Store {
    private final String line = "____________________________________________________________\n";
    private File savedCopy;
    private List<Task> allItems;

//    private final String TODO = "todo";
//    private final String DEADLINE = "deadline";
//    private final String EVENT = "event";

    Store() {
        try {
            this.allItems = new ArrayList<>();
            this.savedCopy = new File("./data/save.txt");
            if (!this.savedCopy.exists()) {
                File directory = new File("./data");
                directory.mkdir();
                this.savedCopy.createNewFile();
            } else {
                this.retrieveStorage();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong:  " + e.getMessage());
        }
    }

    private void retrieveStorage() {
// LIMITATION CANNOT HAVE COMMA IN DESCRIPTION OF TASKS
        try {
            FileReader fileReader = new FileReader(this.savedCopy);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String eachLine = bufferedReader.readLine();
            while (eachLine != null) {
                String[] simplerData = eachLine.split(",");
                Task toAdd;
                if (simplerData.length == 3) {
                    String description = simplerData[2].strip();
                    boolean isDone = simplerData[1].strip().equals("N") ? false : true;
                    toAdd = new Task(description, isDone);
                    this.allItems.add(toAdd);
                } else {
//                    assert simplerData.length == 4;
                    if (simplerData[0].equals("D")) {
                        String description = simplerData[2].strip();
                        boolean isDone = simplerData[1].strip().equals("N") ? false : true;
                        String date = simplerData[3].strip();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                        LocalDate actualDate = LocalDate.parse(date, formatter);
                        toAdd = new Deadline(description, actualDate, isDone);
                        this.allItems.add(toAdd);

                    } else if (simplerData[0].equals("E")){
                        String description = simplerData[2].strip();
                        boolean isDone = simplerData[1].strip().equals("N") ? false : true;
                        String date = simplerData[3].strip();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                        LocalDate actualDate = LocalDate.parse(date, formatter);
                        toAdd = new Event(description, actualDate, isDone);
                        this.allItems.add(toAdd);
                    }
                }
                eachLine = bufferedReader.readLine();
            }

        } catch (IOException e) {
            System.out.println("Something went wrong:  " + e.getMessage());
        }
    }

    public void addToStore(String type, String item) {
        try {
            String actual_item = item.strip();
            Task toAdd;
            if (type.equals(TYPES.TODO.text)) {
                toAdd = new Task(actual_item);
                this.addTask(toAdd);
            } else {
                String[] partsOfTask = actual_item.split("/");
                if (partsOfTask.length != 2) {
                    String instruction = "<type of task> <description> / <deadline>";
                    if (type.equals(TYPES.EVENT.text)) instruction = "<type of task> <description> / <date of event>";
                    throw new DukeGotNoArgumentsException(instruction);
                } else {
                    String description = partsOfTask[0];
                    String date = partsOfTask[1].strip();

//                    parsing the date
                    if (date.length() != 8) {
                        throw new DataFormatException();
                    }

                    Integer day = Integer.valueOf(date.substring(0,2));
                    Integer month = Integer.valueOf(date.substring(2,4));
                    Integer year = Integer.valueOf(date.substring(4,8));
                    LocalDate actualDate = LocalDate.of(year,month,day);

                    if (type.equals(TYPES.DEADLINE.text)) {
                        toAdd = new Deadline(description.strip(), actualDate);
                        this.addTask(toAdd);

                    } else if (type.equals(TYPES.EVENT.text)) {
                        toAdd = new Event(description.strip(), actualDate);
                        this.addTask(toAdd);

                    } else {
                        // should not reach here
                        System.out.println("It's not you, it's me. Something went wrong\n" +
                                "Please try again.\n" + line);
                    }
                }
            }
        } catch (DukeGotNoArgumentsException e) {
            System.out.println(e.getMessage() + "\n" + line);
        } catch (DataFormatException e) {
            System.out.println("Please key in again with the date in the ddmmyyyy format." + "\n" + line);
        } catch (DateTimeException e) {
            System.out.println("Please key in again with a valid date." + "\n" + line);
        }
    }

    private void addTask(Task toAdd) {
        this.allItems.add(toAdd);
        System.out.println("Alright, its in your list now!\n\t" + toAdd +
                "\nNow you have " + this.allItems.size() + " tasks.\n" + line);
    }

    public void saveIntoHarddisk() {
        try {
            FileWriter writer = new FileWriter("./data/save.txt", false);
            writer.write("");
            writer.close();
            writer = new FileWriter("./data/save.txt", true);
            for (Task task: this.allItems) {
                writer.write(task.getStoreRepresentation() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong:  " + e.getMessage());
        }
    }


    public boolean printStore() {
        String printList;
        if (this.allItems.size() == 0) {
            printList = "There are no tasks added till now.\nAdd one by just typing its name.\n" + line;
        } else {
            printList = "Please finish these tasks ASAP!\n";
            int counter = 1;
            for (Task task : this.allItems) {
                printList = printList.concat("[" + counter + "] " + task + "\n");
                counter++;
            }
            printList = printList.concat("If you're brave enough to start,\n" + "You're strong enough to finish it!\n" + line);
        }
        System.out.println(printList);
        return true;
    }

    public boolean completeTask(String answer) {
        try {
            Integer oneIndex = Integer.valueOf(answer);
            Integer realIndex = oneIndex - 1;
            Task toComplete = this.allItems.get(realIndex);
            toComplete.finishTask();
        } catch (NumberFormatException e) {
            System.out.println("I can't seem to understand what task you are referring to.\n" +
                    "Please let me know in this format: done <number of task>\n" + line);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Hmm... I don't have a task numbered " + answer + "\n" + line);
        }
        return true;
    }

    public boolean deleteTask(String answer) {
        try {
            int oneIndex = Integer.parseInt(answer);
            int realIndex = oneIndex - 1;
            this.allItems.remove(realIndex);
            System.out.println("I have removed the task from your list.\n" + line);
        } catch (NumberFormatException e) {
            System.out.println("I can't seem to understand what task you are referring to.\n" +
                    "Please let me know in this format: done <number of task>\n" + line);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Hmm... I don't have a task numbered " + answer + "\n" + line);
        }
        return true;
    }

    enum TYPES {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");
        private String text;

        TYPES(String text) {
            this.text = text;
        }
    }
}
