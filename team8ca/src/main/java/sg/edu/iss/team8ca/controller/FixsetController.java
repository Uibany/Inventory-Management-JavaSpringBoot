//package sg.edu.iss.team8ca.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.Objects;
//
//import javax.validation.constraints.NotNull;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.CollectionUtils;
//
//import sg.edu.iss.team8ca.dto.FixsetItemDto;
//import sg.edu.iss.team8ca.model.Fixset;
//import sg.edu.iss.team8ca.model.FixsetItem;
//import sg.edu.iss.team8ca.service.FixsetItemService;
//import sg.edu.iss.team8ca.service.FixsetService;
//import sg.edu.iss.team8ca.service.ProductListingInterface;
//
//@RestController
//@RequestMapping("/api/fixset")
//public class FixsetController {
//	ProductListingInterface itemService;
//	FixsetService fixsetService;
//	FixsetItemService fixsetItemService;
//
//	public FixsetController(ProductListingInterface itemService, FixsetService fixsetService,
//			FixsetItemService fixsetItemService) {
//		this.itemService = itemService;
//		this.fixsetService = fixsetService;
//		this.fixsetItemService = fixsetItemService;
//	}
//
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//	public @NotNull Iterable<Fixset> list() {
//		return this.fixsetService.getAllFixsets();
//	}
//
//	@PostMapping
//	public ResponseEntity<Fixset> create(@RequestBody FixsetForm form) {
//		List<FixsetItemDto> formDtos = form.getFixsetItems();
//		validateItemsExistence(formDtos);
//		Fixset fixsets = new Fixset();
//		fixsets = this.fixsetService.create(fixsets);
//
//		List<FixsetItem> fixsetItems = new ArrayList<>();
//		for (FixsetItemDto dto : formDtos) {
//			fixsetItems.add(fixsetItemService.create(
//					new FixsetItem(fixsets, itemService.getProduct(dto.getItem().getId()), dto.getQuantity())));
//		}
//
//		fixsets.setFixsetItems(fixsetItems);
//
//		this.fixsetService.update(fixsets);
//
//		String uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/fixset/{id}")
//				.buildAndExpand(fixsets.getId()).toString();
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Location", uri);
//
//		return new ResponseEntity<>(fixsets, headers, HttpStatus.CREATED);
//	}
//	
//	private void validateItemsExistence(List<FixsetItemDto> fixsetItems) {
//        List<FixsetItemDto> list = fixsetItems
//          .stream()
//          .filter(fi -> Objects.isNull(itemService.getProduct(fi
//            .getItem()
//            .getId())))
//          .collect(Collectors.toList());
//
//        if (!CollectionUtils.isEmpty(list)) {
//            new ResourceNotFoundException("Item not found");
//        }
//    }
//	
//	public static class FixsetForm {
//
//        private List<FixsetItemDto> fixsetItems;
//
//        public List<FixsetItemDto> getFixsetItems() {
//            return fixsetItems;
//        }
//
//        public void setFixsetItems(List<FixsetItemDto> fixsetItems) {
//            this.fixsetItems = fixsetItems;
//        }
//    }
//
//}
