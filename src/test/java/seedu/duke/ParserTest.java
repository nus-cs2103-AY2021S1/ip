package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;



public class ParserTest {
    //set up a Storage instance for testing
    private Storage defaultStorage;
    private File file;

    public ParserTest() throws IOException {
        this.defaultStorage = new Storage("parserTest.txt");
        FileWriter clearFile = new FileWriter("parserTest.txt");
        clearFile.write("");
        clearFile.close();
        file = new File("parserTest.txt");
    }

    @Test
     public void parseInputToFileTest() throws IOException, DukeException {
        String msg = "todo sleep";
        Parser.parseInput(msg, defaultStorage);
        Scanner fileSc = new Scanner(file);
        String taskString = fileSc.nextLine();
        assertEquals("[T] [✘] sleep", taskString, "wrong file recording");
    }
}
