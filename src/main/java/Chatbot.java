import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Chatbot {

    private ArrayList<Task> tasks = new ArrayList<>();
    private String directoryPath = "data";
    private String filePath = directoryPath + "/duke.txt";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");


}
