package fr.insa.mas.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = { "spring.config.location=classpath:../.github/workflows/main_userservice1.yml" })
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
