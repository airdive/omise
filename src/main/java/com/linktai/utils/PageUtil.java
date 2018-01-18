package com.linktai.utils;

import java.util.List;

/**
 * 分页工具
 * @author 17770
 *
 * @param <E>
 */

public class PageUtil<E> {
	private List<E> list;
	/**
	 * 当前页
	 */
	private Integer cp;
	//每页数量
	private Integer ps;
	private Integer allPage;
	public PageUtil() {
		// TODO Auto-generated constructor stub
	}
	public PageUtil(List<E> list, Integer cp, Integer ps,Integer allPage) {
		super();
		this.list = list;
		this.cp = cp;
		this.ps = ps;
		this.allPage = allPage;
	}
	public List<E> getList() {
		return list;
	}
	public void setList(List<E> list) {
		this.list = list;
	}
	public Integer getCp() {
		return cp;
	}
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	public Integer getPs() {
		return ps;
	}
	public void setPs(Integer ps) {
		this.ps = ps;
	}
	public Integer getAllPage() {
		return allPage;
	}
	public void setAllPage(Integer allPage) {
		this.allPage = allPage;
	}
	
	
	
}
