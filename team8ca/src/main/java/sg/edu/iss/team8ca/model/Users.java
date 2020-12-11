
package sg.edu.iss.team8ca.model;

import java.util.List;

import javax.persistence.OneToMany;

public class Users {

	@OneToMany(mappedBy = "users")
	private List<TransHistory> transHistory;

}
