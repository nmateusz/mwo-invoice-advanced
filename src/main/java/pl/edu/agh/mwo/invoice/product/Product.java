package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax)  throws IllegalArgumentException{
		if(name == null)
			throw new IllegalArgumentException("name is null");
		else if(name == "")
			throw new IllegalArgumentException("name is empty");
		else
			this.name = name;
		if(price == null)
			throw new IllegalArgumentException("price is null");
		else if(price.floatValue()<0)
			throw new IllegalArgumentException("price is negative");
		else
			this.price = price;
		this.taxPercent = tax;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public BigDecimal getPriceWithTax() {
		return price.add(price.multiply(taxPercent));
	}
}
