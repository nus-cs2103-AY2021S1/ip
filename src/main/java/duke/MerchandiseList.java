package duke;

import duke.merchandise.Merchandise;

import java.util.ArrayList;

public class MerchandiseList {
    private final ArrayList<Merchandise> merchandises;

    public MerchandiseList() {
        this.merchandises = new ArrayList<>();
    }

    public MerchandiseList(ArrayList<Merchandise> merchandises) {
        this.merchandises = merchandises;
    }

    public ArrayList<Merchandise> getMerchandises() {
        return this.merchandises;
    }

    public void addMerchandise(Merchandise merchandise) {
        merchandises.add(merchandise);
    }

    public void deleteMerchandise(Merchandise merchandise) {
        merchandises.remove(merchandise);
    }

    public String printCollection() {
        String collection = "";
        for (int i = 0; i < merchandises.size(); i++) {
            collection += merchandises.get(i) + "\n";
        }
        return collection;
    }
}
