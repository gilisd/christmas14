package be.digan.learning.christmas;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChristmasControllerTest.TestConfiguration.class)
public class ChristmasControllerTest {
    @Configuration
    @ComponentScan(excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ChristmasPresentsApplication.class),
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ChristmasBusiness.class)
    })
    static class TestConfiguration {
        @Bean
        public ChristmasBusiness christmasBusiness() {
            return mock(ChristmasBusiness.class);
        }
    }

    @Autowired
    ChristmasController christmasController;
    @Autowired
    ChristmasBusiness christmasBusiness;

    @Before
    public void setup() {
        Mockito.reset(christmasBusiness);
    }

    @Test
    public void unknownUserGetsCorrectResponse() throws Exception {
        when(christmasBusiness.getPresentsFor(anyString())).thenThrow(new UnknownUserException());

        String result = christmasController.getPresentsFor("Ali Baba");

        assertEquals("Sorry, I don't know you", result);
    }

    @Test
    public void noPresentsGetCorrectResponse() throws Exception {
        when(christmasBusiness.getPresentsFor(anyString())).thenReturn(new ArrayList<String>());

        String result = christmasController.getPresentsFor("David");

        assertEquals("No presents for you", result);
    }

    @Test
    public void onePresentGetCorrectResponse() throws Exception {
        when(christmasBusiness.getPresentsFor(anyString())).thenReturn(Arrays.asList(new String[]{"one"}));

        String result = christmasController.getPresentsFor("David");

        assertEquals("one\n", result);
    }

    @Test
    public void multiplePresentsGetCorrectResponse() throws Exception {
        when(christmasBusiness.getPresentsFor(anyString())).thenReturn(Arrays.asList(new String[]{"one", "two"}));

        String result = christmasController.getPresentsFor("David");

        assertEquals("one\ntwo\n", result);
    }

}