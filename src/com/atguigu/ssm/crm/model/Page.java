package com.atguigu.ssm.crm.model;

import java.util.List;

public class Page<T> {
	private int pageNo;
	private int pageSize = 2;
	private int totalElements;
	private List<T> content;


	public int getPageNo() {
		if (this.pageNo > getTotalPages()) {
			return getTotalPages();
		}
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
			return;
		}
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getTotalPages() {
		int totalPages = this.totalElements / this.pageSize;
		if (this.totalElements % this.pageSize != 0) {
			totalPages++ ;
		}
		return totalPages;
	}
	
	public boolean isHasPrev(){
		return this.getPageNo() > 1;
	}
	public int getPrevPage(){
		if (isHasPrev()) {
			return pageNo - 1;
		}
		return 1;
	}
	
	public boolean isHasNext(){
		return this.getPageNo() < this.getTotalPages();
	}
	
	public int getNextPage(){
		if (isHasNext()) {
			return getPageNo() + 1;
		}
		return getTotalPages();
	}
}
