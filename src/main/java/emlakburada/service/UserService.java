package emlakburada.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emlakburada.model.User;
import emlakburada.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void getAllUser() {
		// System.out.println("KullaniciService -> ilanRepository: " +
		// ilanRepository.toString());
	}

	public void saveUser(User user) {
		//userRepository.save(user);
	}

}
