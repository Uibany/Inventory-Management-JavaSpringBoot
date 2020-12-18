package sg.edu.iss.team8ca.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import sg.edu.iss.team8ca.model.FixsetItem;

@Validated
public interface FixsetItemService {
	FixsetItem create(@NotNull(message = "The items for fixset cannot be null.") @Valid FixsetItem fixsetItem);
}
