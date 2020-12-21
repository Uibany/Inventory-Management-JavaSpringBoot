package sg.edu.iss.team8ca.service;

import sg.edu.iss.team8ca.model.Fixset;

public interface FixsetService {
	
	Iterable<Fixset> getAllFixsets();

	Fixset create(Fixset fixset);

	void update(Fixset fixset);
}
