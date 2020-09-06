package duke.dukehelper.uiparts;

import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Statistics extends HBox {
    private final static String path = "data/stat.txt";
    private final static String[] categories = new String[]{"Todo", "Deadline", "Event"};
    private BarChart<String, Number> chart;

    /**
     * Saves current counter data
     * @param data current counter data
     */
    public void saveData(int[] data) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, false);
            String res = "";
            for(int i : data) {
                res += i + "\n";
            }
            writer.write(res);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads current counter data
     * @return current counter data
     */
    public int[] getData() {
        int[] data = new int[]{0,0,0};
        try {
            File savedFile = new File(path);
            Scanner scanner = new Scanner(savedFile);
            int index = 0;
            while (scanner.hasNextLine()) {
                String chartData = scanner.nextLine();
                data[index++] = Integer.parseInt(chartData);
            }
        } catch (FileNotFoundException e) {

        }
        return data;
    }
    public Statistics() {
        int[] data = getData();
        int max = 0;
        for (int num : data) {
            if (num > max) {
                max = num;
            }
        }
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(categories));
        xAxis.setLabel("Types of notes");

        NumberAxis yAxis = new NumberAxis(0.0, max, 1.0);
        yAxis.setLabel("Times used");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Since the start");
        for (int i = 0; i < data.length; i++) {
            series.getData().add(new XYChart.Data<String, Number>(categories[i], data[i]));
        }
        chart = new BarChart<>(xAxis, yAxis);
        chart.getData().add(series);
        this.getChildren().add(chart);
    }
}
