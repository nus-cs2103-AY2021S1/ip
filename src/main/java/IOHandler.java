import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Handles the input by the user and generates the respective output.
 */
public abstract class IOHandler {

    /**
     * Handles the input by the user and prints the respective output.
     */
    public abstract String handleIO(String input, TaskManager taskManager, FileHandler fileHandler) throws IOException;

//        try {

//            String fileName = "data/duke.txt";
//            File file = new File(fileName);
//
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            String error = DukeExceptionHandler.handleException(input);
//            List<String> files = FileHandler.readSavedFile(fileName);

//            for (String value : files) {
//                Task task = TextAndTaskConverter.textConverter(value);
//                taskManager.getTasksList().add(task);
//            }


            //FileHandler.writeToFile(fileName, taskManager);
//        catch(FileNotFoundException e){
//
//            return "File not found!";
//            //System.out.println("File not found!");
//        }
//        catch(IOException e){
//            return "OOPS something went wrong!";
////            System.out.println("OOPS something went wrong!");
//        }
}







