package be.digan.learning.christmas;

import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChristmasControllerTest.TestConfiguration.class)
public class ChristmasControllerTest {
    @Configuration
    static class TestConfiguration {
        @Bean
        public PresentDao presentDao() {
            return mock(PresentDao.class);
        }
        @Bean
        public ChristmasController christmasController() {
            return new ChristmasController();
        }
    }

    @Autowired
    ChristmasController christmasController;
    @Autowired
    PresentDao presentDao;


    @Test
    @Ignore
    public void unknownUserGetsCorrectRespons() {
        when(presentDao.getData()).thenReturn(new ArrayList<Giver>());

        String result = christmasController.getPresentsFor("Ali Baba");

        assertEquals("Sorry, I don't know you", result);
    }

}