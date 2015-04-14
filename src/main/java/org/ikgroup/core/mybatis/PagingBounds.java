package org.ikgroup.core.mybatis;

import org.apache.ibatis.session.RowBounds;

/**
 * 
 * 
 * @author glenlivet
 *
 */
public class PagingBounds extends RowBounds {
	
	/**
	 * 总纪录数
	 */
	private int total;
	
	/**
	 * 查询起始位置
	 */
	private int offset;
	
	/**
	 * 查询多少行纪录
	 */
	private int limit;
	
	public PagingBounds(int offset, int limit){
		this.offset = offset;
		this.limit = limit;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void reset(){
		this.offset = NO_ROW_OFFSET;
		this.limit = NO_ROW_LIMIT;
	}
	
	public int getSelectCount(){
		return limit + offset;
	}

}
