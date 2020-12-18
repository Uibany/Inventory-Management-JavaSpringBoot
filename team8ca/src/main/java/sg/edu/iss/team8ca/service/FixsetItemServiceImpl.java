package sg.edu.iss.team8ca.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.team8ca.model.FixsetItem;
import sg.edu.iss.team8ca.repo.FixsetItemRepo;

@Service
@Transactional
public class FixsetItemServiceImpl implements FixsetItemService{

	FixsetItemRepo fixsetItemRepo;
	
	public FixsetItemServiceImpl(FixsetItemRepo fixsetItemRepo) {
		this.fixsetItemRepo = fixsetItemRepo;
	}
	
	@Override
	public FixsetItem create(FixsetItem fixsetItem) {
		return this.fixsetItemRepo.save(fixsetItem);
	}
}
