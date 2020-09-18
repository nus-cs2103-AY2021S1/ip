package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class ParserTest {
    //set up a Storage instance for testing
    Storage defaultStorage;
    File file;

    public ParserTest() throws IOException {
        this.defaultStorage = new Storage("parserTest.txt");
        FileWriter clearFile = new FileWriter("parserTest.txt");
        clearFile.write("");
        clearFile.close();
        file = new File("parserTest.txt");
    }

    @Test
     public void parse_input_to_file_test() throws IOException, DukeException {
        String msg = "todo sleep";
        Parser.parseInput(msg,defaultStorage);
        Scanner fileSc = new Scanner(file);
        String taskString = fileSc.nextLine();
        assertEquals("[T] [✘] sleep", taskString, "wrong file recording");
    }
}