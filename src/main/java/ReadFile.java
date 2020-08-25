import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    public final static String TODO = "T";
    public final static String DEADLINE = "D";
    public final static String EVENT = "E";

    public static void readFile() {
        try {
            File myObj = new File("data/duke.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataSplit = data.split("\\|");
                if (dataSplit[3].equals("null")) {
                    switch (dataSplit[0]) {
                        case TODO:
                            if (dataSplit[1].equals("1")) {
                                TaskList.addTask(Duke.TaskType.TODOS, dataSplit[2]).markAsDone();
                            } else {
                                TaskList.addTask(Duke.TaskType.TODOS, dataSplit[2]);
                            }
                            break;
                        case DEADLINE:
                            if (dataSplit[1].equals("1")) {
                                TaskList.addTask(Duke.TaskType.DEADLINE, dataSplit[2]).markAsDone();
                            } else {
                                TaskList.addTask(Duke.TaskType.DEADLINE, dataSplit[2]);
                            }
                            break;
                        case EVENT:
                            if (dataSplit[1].equals("1")) {
                                TaskList.addTask(Duke.TaskType.EVENT, dataSplit[2]).markAsDone();
                            } else {
                                TaskList.addTask(Duke.TaskType.EVENT, dataSplit[2]);
                            }
                    }
                } else if (dataSplit.length == 5) {
                    switch (dataSplit[0]) {
                        case TODO:
                            if (dataSplit[1].equals("1")) {
                                TaskList.addTask(Duke.TaskType.TODOS, dataSplit[2], dataSplit[3] + " " + dataSplit[4]).markAsDone();
                            } else {
                                TaskList.addTask(Duke.TaskType.TODOS, dataSplit[2], dataSplit[3] + " " + dataSplit[4]);
                            }
                            break;
                        case DEADLINE:
                            if (dataSplit[1].equals("1")) {
                                TaskList.addTask(Duke.TaskType.DEADLINE, dataSplit[2], dataSplit[3] + " " + dataSplit[4]).markAsDone();
                            } else {
                                TaskList.addTask(Duke.TaskType.DEADLINE, dataSplit[2], dataSplit[3] + " " + dataSplit[4]);
                            }
                            break;
                        case EVENT:
                            if (dataSplit[1].equals("1")) {
                                TaskList.addTask(Duke.TaskType.EVENT, dataSplit[2], dataSplit[3] + " " + dataSplit[4]).markAsDone();
                            } else {
                                TaskList.addTask(Duke.TaskType.EVENT, dataSplit[2], dataSplit[3] + " " + dataSplit[4]);
                            }
                    }
                } else {
                    throw new UnexpectedErrorException("Corrupted File");
                }
            }
            myReader.close();
        } catch (FileNotFoundException | UnexpectedErrorException e) {
            System.out.println("No current data exists");
        }
    }
}
