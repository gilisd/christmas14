package be.digan.learning.christmas;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChristmasBusinessTest.TestConfiguration.class)
public class ChristmasBusinessTest {
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

    @Configuration
    static class TestConfiguration {
        @Bean
        public ChristmasBusiness christmasBusiness() {
            return new ChristmasBusiness();
        }
        @Bean
        public PresentDao presentDao() {
            return mock(PresentDao.class);
        }
    }

    @Autowired
    PresentDao presentDao;
    @Autowired
    ChristmasBusiness christmasBusiness;

    @Before
    public void setup() {
        Mockito.reset(presentDao);
    }

    @Test(expected = UnknownUserException.class)
    public void unknownUserThrowsError() throws Exception {
        when(presentDao.getData()).thenReturn(new ArrayList<Giver>());
        christmasBusiness.getPresentsFor("Ali Baba");
    }

    @Test
    public void onlyGiverGetsEmptyResult() throws Exception {
        when(presentDao.getData()).thenReturn(Arrays.asList(new Giver(DAVID, new ArrayList<Present>())));
        List<String> result = christmasBusiness.getPresentsFor(DAVID);
        assertEquals(0, result.size());
    }

    @Test
    public void multipleResult() throws Exception {
        List<Giver> data = new ArrayList<Giver>();
        GiverBuilder david = new GiverBuilder().withName(DAVID);
        david.addPresent(new Present(CANDY, TOM));
        david.addPresent(new Present(CANDY, MICHAEL));
        data.add(david.build());

        GiverBuilder vinciane = new GiverBuilder().withName(VINCIANE);
        vinciane.addPresent(new Present(TICKET, TOM));
        data.add(vinciane.build());

        when(presentDao.getData()).thenReturn(data);

        List<String> result = christmasBusiness.getPresentsFor(TOM);
        assertEquals(2, result.size());
        assertTrue(result.contains("1 " + TICKET));
        assertTrue(result.contains("1 " + CANDY));

    }

    @Test
    public void duplicateResultsAreCounted() throws Exception {
        List<Giver> data = new ArrayList<Giver>();
        GiverBuilder david = new GiverBuilder().withName(DAVID);
        david.addPresent(new Present(CANDY, TOM));
        david.addPresent(new Present(CANDY, TOM));
        data.add(david.build());

        when(presentDao.getData()).thenReturn(data);

        List<String> result = christmasBusiness.getPresentsFor(TOM);
        assertEquals(1, result.size());
        assertEquals("2 " + CANDY, result.get(0));
    }

}