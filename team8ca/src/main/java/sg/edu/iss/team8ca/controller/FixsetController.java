package sg.edu.iss.team8ca.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.iss.team8ca.model.Fixset;
import sg.edu.iss.team8ca.model.Product;
import sg.edu.iss.team8ca.service.FixsetService;
import sg.edu.iss.team8ca.service.ResourceNotFoundException;

@RestController
@RequestMapping("/api/fixset")
@CrossOrigin(origins = "http://localhost:8090")
public class FixsetController {
	
	private FixsetService fixsetService;
	
	@GetMapping(value = {"", "/"})
    public @NotNull Iterable<Fixset> getFixsets() {
        return fixsetService.findAll();
    }
	
	@PostMapping("/create")
	public ResponseEntity<Object> createUser(@Valid @RequestBody List<Fixset> fixsets) {
		List<String> listOfFixsetIds = new ArrayList<>();
		
		for (Fixset fixset : fixsets) {	
			try {
				fixsetService.create(fixset);
			} catch(ResourceNotFoundException e) {
				listOfFixsetIds.add(e.getMessage());
				return new ResponseEntity<Object>(listOfFixsetIds, HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<Object>(listOfFixsetIds, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable long id) {
		
		//delete individual product
		Fixset fixset = fixsetService.deleteById(id);
		if (fixset == null)
			throw new ResourceNotFoundException("id: "+id);
	}

}
