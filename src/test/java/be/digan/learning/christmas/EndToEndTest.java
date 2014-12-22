package be.digan.learning.christmas;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ChristmasPresentsApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0"})
public class EndToEndTest {
	@Value("${local.server.port}")
	int port;

	@Before
	public void setup() throws IOException {
		RestAssured.port = port;
	}

	@Test
	@Ignore
	public void unknownUser() {
		String result = when().get("christmas/getPresentsFor/Gaetan").asString();
		assertThat(result, equalTo("Sorry, I don't know you"));
	}

}