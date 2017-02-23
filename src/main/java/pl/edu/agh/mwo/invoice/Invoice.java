package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	
	private HashMap<Product, Integer> invoiceList;
	
	public Invoice(){
		invoiceList = new HashMap<Product, Integer>();
	}
	
	public void addProduct(Product product) {
		addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) throws IllegalArgumentException{
		if(quantity == 0)
			throw new IllegalArgumentException("quantity can't be 0");
		else if(quantity < 0)
			throw new IllegalArgumentException("quantity can't be negative");
		else{
			if(invoiceList.containsKey(product))
				invoiceList.put(product, invoiceList.get(product)+quantity);
			else
				invoiceList.put(product, quantity);
		}
	}

	public BigDecimal getSubtotal() {
		if(invoiceList.isEmpty())
			return BigDecimal.ZERO;
		else{
			BigDecimal totalPrice = new BigDecimal("0");
			for(Map.Entry<Product, Integer> entry : invoiceList.entrySet()){
				BigDecimal tempQuantity = new BigDecimal(entry.getValue());
				BigDecimal tempPrice = entry.getKey().getPrice();
				totalPrice = totalPrice.add(tempPrice.multiply(tempQuantity));
			}
			return totalPrice;
		}
	}

	public BigDecimal getTax() {
		if(invoiceList.isEmpty())
			return BigDecimal.ZERO;
		else{
			BigDecimal totalTax = new BigDecimal("0");
			for(Map.Entry<Product, Integer> entry : invoiceList.entrySet()){
				BigDecimal tempQuantity = new BigDecimal(entry.getValue());
				BigDecimal tempPrice = entry.getKey().getPrice();
				BigDecimal tempTaxPercent = entry.getKey().getTaxPercent();
				BigDecimal tempTax = tempQuantity.multiply(tempPrice.multiply(tempTaxPercent));
				totalTax = totalTax.add(tempTax);
			}
			return totalTax;
		}
	}

	public BigDecimal getTotal() {
		if(invoiceList.isEmpty())
			return BigDecimal.ZERO;
		else{
			BigDecimal total = new BigDecimal("0");
			total = getTax().add(getSubtotal());
			return total;
		}
	}
}
