package login.test.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import login.test.configuration.JwtProvider;
import login.test.coverter.UserConverter;
import login.test.dto.UserDTO;
import login.test.repository.UserRepository;
import login.test.model.User;

@Service
public class UserService  extends AbstractService <User, UserDTO> {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserConverter converter;
	
	
	public UserDTO findById (Long id) {
		return converter.toDTO(repository.findByid(id));
	}
	public UserDTO findByName (String username) {
		return converter.toDTO(repository.findByName(username));
	}
	public UserDTO findByNameAndPassword (String name, String password) {
		
		return converter.toDTO(repository.findByNameAndPassword(name, password));
	}
	@Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String username, String password) {
        User user = this.repository.findByName(username);
        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
        ObjectNode userNode = new ObjectMapper().convertValue(user, ObjectNode.class);
        userNode.remove("password");
        Map claimMap = new HashMap<>(0);
        claimMap.put("user", userNode);
        return JwtProvider.createJwt(username, claimMap);
    }
	}
	


