package be.digan.learning.christmas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 12/22/14.
 */
public class Giver {
    String name;
    List<Present> presents = new ArrayList<Present>();

    public Giver(String name, List<Present> presents) {
        this.name = name;
        this.presents = presents;
    }

    public String getName() {
        return name;
    }

    public List<Present> getPresents() {
        return presents;
    }
}
