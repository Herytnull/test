package login.test.repository;

import org.springframework.stereotype.Repository;

import login.test.model.User;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository <User, Long> {
	User findByid(Long id);
	User findByName (String name);
	User findByNameAndPassword (String name, String password);
	
	

}
