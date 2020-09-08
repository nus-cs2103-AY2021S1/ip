import java.io.IOException;

/**
 * Handles the input by the user and generates the respective output.
 */
public abstract class Command {

    /**
     * Handles the input by the user and prints the respective output.
     */
    public abstract String handle(String input, TaskManager taskManager, Storage fileHandler) throws IOException;
    
    
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







