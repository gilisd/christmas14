package be.digan.learning.christmas;

import java.util.ArrayList;
import java.util.List;

public class GiverBuilder {
    private String name;
    private List<Present> presents = new ArrayList<Present>();

    public GiverBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public GiverBuilder addPresent(Present present) {
        presents.add(present);
        return this;
    }

    public Giver build() {
        Giver result = new Giver(name, presents);
        return result;
    }
}
