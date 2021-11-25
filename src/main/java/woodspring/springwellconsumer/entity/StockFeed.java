package woodspring.springwellconsumer.entity;

import java.io.Serializable;

public class StockFeed implements Serializable {
	Long id;
	String symbol;
	Double price;
	String side;
	Long atTime;
	
	public StockFeed(Long id, String symbol, Double price, String side, Long atTime_) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.price = price;
		this.side = side;
		this.atTime = atTime_;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public Long getAtTime() {
		return atTime;
	}
	public void setAtTime(Long atTime) {
		this.atTime = atTime;
	}
	@Override
	public String toString() {
		return "StockFeed [id=" + id + ", symbol=" + symbol + ", price=" + price + ", side=" + side + ", atTime="
				+ atTime + "]";
	}

}
