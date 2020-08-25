package src.test;


import org.junit.jupiter.api.Test;
import src.main.java.duke.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {


    @Test
    public void test_main() throws FileNotFoundException {
        String STORAGE_DIRECTORY = System.getProperty("user.dir") + "/data";
        String TEXT_FILE_NAME = "/duke.txt";
        String result = STORAGE_DIRECTORY + TEXT_FILE_NAME;
        Main.main(new String[] {result});
    }

    @Test
    public void test() {
        assertEquals(2,2);
    }
}
