package com.douzone.mysite.vo;

public class PagingVo {
	private int pageNo=1;
	private int blockNo=1;
	private int pagerNo;
	private int pageCount;
	private int viewNo;
	public int getPagerNo() {
		return pagerNo;
	}
	public void setPagerNo(int pagerNo) {
		this.pagerNo = pagerNo;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getViewNo() {
		return viewNo;
	}
	public void setViewNo(int viewNo) {
		this.viewNo = viewNo;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getBlockNo() {
		return blockNo;
	}
	public void setBlockNo(int blockNo) {
		this.blockNo = blockNo;
	}
	@Override
	public String toString() {
		return "PagingVo [pageNo=" + pageNo + ", blockNo=" + blockNo + ", pagerNo=" + pagerNo + ", pageCount="
				+ pageCount + ", viewNo=" + viewNo + "]";
	}
	
	
}
