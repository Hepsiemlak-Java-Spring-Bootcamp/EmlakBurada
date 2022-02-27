package emlakburada.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import emlakburada.dto.response.UserResponse;
import emlakburada.repository.UserRepository;

@SpringBootTest
class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Test
	void getAllUserTest() {

		List<UserResponse> allUser = userService.getAllUser();

		assertNotNull(allUser);
		
		assertThat(allUser.size()).isNotZero();
	}

}
