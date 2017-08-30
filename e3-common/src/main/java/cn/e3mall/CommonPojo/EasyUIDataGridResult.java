package cn.e3mall.CommonPojo;

import java.io.Serializable;
import java.util.List;

/**
 * easyUI返回json对象的封装
 * @author admin
 *
 */
public class EasyUIDataGridResult implements Serializable {

	private Integer total;
	private List<?> rows;

	

	public EasyUIDataGridResult() {
		super();
	}
	public EasyUIDataGridResult(Integer total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public EasyUIDataGridResult(Long total, List<?> rows) {
		super();
		this.total = total.intValue();
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
