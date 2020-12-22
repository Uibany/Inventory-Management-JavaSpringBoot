package sg.edu.iss.team8ca.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sg.edu.iss.team8ca.model.Fixset;

@Repository
@Transactional
public interface FixsetRepo extends JpaRepository<Fixset, Long>{
	
	@Modifying
    @Transactional
    @Query("SELECT id, dateCreated, fixsetName, quantity FROM Fixset")
	Iterable<Fixset> findAllFixsets();
}
