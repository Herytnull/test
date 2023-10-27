package login.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	public enum Usertype {
		ADMIN, USER;

		Object map(Object object) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String name;
	private String password;
	@Enumerated(EnumType.STRING) 
	private Usertype usertype;

		
}
