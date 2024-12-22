package fr.insa.mas.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@TestPropertySource(properties = { "spring.config.location=classpath:../.github/workflows/main_userservice1.yml" })
@ExtendWith(SpringExtension.class)
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
