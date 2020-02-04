package com.cloudfish.controller;

import java.util.Map;

/**
 * 分页类
 * @author FengJuan
 * @Date
 */
public class Pager {

	//每页数据条数，默认10条
	private int pageSize = 10;
	//数据总数，默认0
	private int dataCount = 0;
	//当前页数，默认第一页
	private int currentPage = 1;
	//开始条数
	private int limitStart;
	
	//查询条件的封装
	private Map<String,Object> condition;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getDataCount() {
		return dataCount;
	}
	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public Map<String, Object> getCondition() {
		return condition;
	}
	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}
	public int getLimitStart() {
		return limitStart;
	}
	public void setLimitStart(int limitStart) {
		this.limitStart = limitStart;
	}
}
