package be.digan.learning.christmas;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PresentDao {
    public static final String DAVID = "David";
    public static final String MICHAEL = "Michael";
    public static final String TOM = "Tom";

    public static final String CANDY = "Candy";

    public static final String EURO = " euro";
    public static final String VINCIANE = "Vinciane";
    public static final String WOUTER = "Wouter";
    public static final String TICKET = "Ticket";
    public static final String JEAN_MARC = "Jean-Marc";
    public static final String LOLLIPOP = "Lollipop";
    public static final String PLECTRUM = "Plectrum";

    private List<Giver> data = new ArrayList<Giver>();

    public PresentDao() {
        GiverBuilder david = new GiverBuilder().withName(DAVID);
        david.addPresent(new Present(CANDY, TOM));
        david.addPresent(new Present(20 + EURO, MICHAEL));
        data.add(david.build());

        GiverBuilder vinciane = new GiverBuilder().withName(VINCIANE);
        vinciane.addPresent(new Present(10 + EURO, MICHAEL));
        vinciane.addPresent(new Present(CANDY, TOM));
        vinciane.addPresent(new Present(TICKET, WOUTER));
        data.add(vinciane.build());

        GiverBuilder wouter = new GiverBuilder().withName(WOUTER);
        wouter.addPresent(new Present(LOLLIPOP, JEAN_MARC));
        wouter.addPresent(new Present(CANDY, TOM));
        wouter.addPresent(new Present(PLECTRUM, MICHAEL));
        wouter.addPresent(new Present(CANDY, VINCIANE));
        data.add(wouter.build());
    }

    public List<Giver> getData() {
        return data;
    }
}
