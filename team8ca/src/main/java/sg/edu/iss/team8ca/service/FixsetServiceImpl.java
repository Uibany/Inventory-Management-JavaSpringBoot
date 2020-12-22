package sg.edu.iss.team8ca.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.team8ca.model.Fixset;
import sg.edu.iss.team8ca.repo.FixsetRepo;
import sg.edu.iss.team8ca.repo.ProductRepo;

@Service
@Transactional
public class FixsetServiceImpl implements FixsetService {
	
	@Autowired
	private FixsetRepo fRepo;

	@Autowired
	private ProductRepo pRepo;

	@Override
	public Iterable<Fixset> getAllFixsets() {
		return this.fRepo.findAllFixsets();
	}

	@Override
	public Fixset create(Fixset fixset) {
		List<Integer> count = pRepo.getFixsetDetails(fixset.getProduct().getProductId(), fixset.getQuantity());
		if (count.get(0) == 1) {
			fixset.setDateCreated(LocalDate.now());
			Fixset fixsetResponse = this.fRepo.save(fixset);
			pRepo.updateQuantityOfProduct(fixset.getProduct().getProductId(), fixset.getQuantity());
			return fixsetResponse;
		}
		throw new ResourceNotFoundException("Item or quantity is invalid");
	}

	@Override
	public void update(Fixset fixset) {
		this.fRepo.save(fixset);
	}

	@Override
	public @NotNull Iterable<Fixset> findAll() {
		return this.fRepo.findAllFixsets();
	}

}
