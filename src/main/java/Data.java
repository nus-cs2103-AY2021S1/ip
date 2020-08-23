package main.java;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Data {
    File saved;

    public Data(String filePath) {
        try {
            File saved = new File(filePath);
            if (!saved.exists()) {
                saved.createNewFile();
            }
            this.saved = saved;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String load() {
        String result = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(saved));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                result = result.concat(strCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void save(String taskList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(saved));
            bw.write(taskList);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
