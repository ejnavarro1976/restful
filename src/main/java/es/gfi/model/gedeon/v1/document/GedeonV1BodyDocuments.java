package es.gfi.model.gedeon.v1.document;

import java.io.Serializable;

public class GedeonV1BodyDocuments implements Serializable {

	private static final long serialVersionUID = -6141828574808469913L;

	
	private Integer count;	
	private GedeonV1Document<?, ?, ?> items;
	private Integer limit;
	private Integer offset;
	private Integer total;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public GedeonV1Document<?, ?, ?> getItems() {
		return items;
	}

	public void setItems(GedeonV1Document<?, ?, ?> items) {
		this.items = items;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
