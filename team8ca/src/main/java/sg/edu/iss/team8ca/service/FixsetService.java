package sg.edu.iss.team8ca.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import sg.edu.iss.team8ca.model.Fixset;

@Validated
public interface FixsetService {
	
	@NotNull 
	Iterable<Fixset> getAllFixsets();

    Fixset create(@NotNull(message = "The fixset cannot be null.") @Valid Fixset fixset);

    void update(@NotNull(message = "The fixset cannot be null.") @Valid Fixset fixset);

}
