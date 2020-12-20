package sg.edu.iss.team8ca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.team8ca.model.FixsetItem;
import sg.edu.iss.team8ca.model.FixsetItemPK;

public interface FixsetItemRepo extends JpaRepository<FixsetItem, FixsetItemPK>{

}
