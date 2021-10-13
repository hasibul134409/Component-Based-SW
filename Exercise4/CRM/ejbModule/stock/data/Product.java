package stock.data;

/** 
 * Simple Product datatype implemented as a normal Java class.
 * In the implementation of this component, this datatype is completely internal no special interfaces are needed.
 */
public class Product {
	
	protected long id;
	protected long orderid;
	protected boolean isFinished;
	
	public Product(long id, long orderid) {
		this.id = id;
		this.orderid = orderid;
		this.isFinished = false;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public long getId() {
		return id;
	}

	public long getOrderid() {
		return orderid;
	}
	

}
