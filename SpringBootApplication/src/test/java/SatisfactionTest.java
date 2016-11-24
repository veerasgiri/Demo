import org.junit.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SatisfactionTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void satisfactionTest() {
		String body = this.restTemplate.getForObject("http://localhost:8080/getData?TimeSlice=100", String.class);
		assertThat(body).contains("Maximum Satisfaction");
	}

}