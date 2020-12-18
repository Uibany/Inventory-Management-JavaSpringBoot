package sg.edu.iss.team8ca.dto;

import sg.edu.iss.team8ca.model.Inventory;

public class FixsetItemDto {
	
	private Inventory item;
    private Integer quantity;

    public Inventory getItem() {
        return item;
    }

    public void setItem(Inventory item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
