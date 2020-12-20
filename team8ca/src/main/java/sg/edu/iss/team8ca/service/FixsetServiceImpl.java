package sg.edu.iss.team8ca.service;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team8ca.model.Fixset;
import sg.edu.iss.team8ca.repo.FixsetRepo;

@Service
@Transactional
public class FixsetServiceImpl implements FixsetService {
	
	private FixsetRepo frepo;
	
	public FixsetServiceImpl (FixsetRepo frepo) {
		this.frepo = frepo;
	}
	
	@Override
	public Iterable<Fixset> getAllFixsets() {
		return this.frepo.findAll();
	}
	
	@Override
	public Fixset create(Fixset fixset) {
		return this.frepo.save(fixset);
	}
	
	@Override
	public void update(Fixset fixset) {
		this.frepo.save(fixset);
	}
}
