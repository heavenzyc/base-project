/**
 * @(#)Pagination.java
 *
 * Copyright 2008 naryou, Inc. All rights reserved.
 */

package com.base.pagination;


import java.io.Serializable;
import java.util.List;

/**
 *
 * 分页组件
 * @author heaven.zyc
 */
public class Pagination<T> implements Serializable {
	
	public static final int DEFAULT_PAGE_SIZE=15;
	public static final int DEFAULT_CURRENT_PAGE=1;
	public static final int DEFAULT_SKIP_SIZE=10;
	public static final int DEFAULT_CURRENT_SKIP=1;
	
	public static final int MAX_PAGE_SIZE=200;

    private List<T> record;

	/**
	 * 每页对象数
	 */
	private int pageSize;

	/**
	 * 页数
	 */
	private int pageCount;

	/**
	 * 对象数
	 */
	private int count;

	/**
	 * 当前页<br/>
	 * 大于零的整数,默认值为1
	 * 
	 */
	private int currentPage;

	/**
	 * 当前页开始的记录的位置
	 */
	private int begin;

	/**
	 * 当前页结束的记录的位置
	 */
	private int end;

	/**
	 * 快近页数量
	 */
	private int skipSize;

	/**
	 * 当前快近所在页面
	 */
	private int currentSkip = DEFAULT_CURRENT_SKIP;

    public List<T> getRecord() {
        return record;
    }

    public void setRecord(List<T> record) {
        this.record = record;
    }

    public Pagination(){
		this(DEFAULT_PAGE_SIZE,DEFAULT_CURRENT_PAGE,DEFAULT_SKIP_SIZE);
	}

	public Pagination(int pageSize, int currentPage) {
		this(pageSize,currentPage,DEFAULT_SKIP_SIZE);
	}
	

	public Pagination(int pageSize, int currentPage, int skipSize) {
		if(pageSize<=0||pageSize>MAX_PAGE_SIZE)
			pageSize=DEFAULT_PAGE_SIZE;
		if(currentPage<=0)
			currentPage=DEFAULT_CURRENT_PAGE;
		if(skipSize<=0)
			skipSize=DEFAULT_SKIP_SIZE;
		this.pageSize=pageSize;
		this.currentPage=currentPage;
		this.skipSize=skipSize;
	}

	public int getCurrentSkip() {
		return currentSkip;
	}

	public void setCurrentSkip(int currentSkip) {
		this.currentSkip = currentSkip;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setSkipSize(int skipSize) {
		this.skipSize = skipSize;
	}

	/**
	 * 返回当前页
	 * 
	 * @return 当前页
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 得到页数<br/>
	 * 默认值为1
	 * 
	 * @return 页数
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 得到每页记录条数<br/>
	 * 默认是15
	 * 
	 * @return 每页记录条数
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/**
	 * 得到记录条数
	 * 
	 * @return 记录条数
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 得到当前页的记录开始位置
	 * 
	 * @return 开始位置
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * 得到记录的结束位置
	 * 
	 * @return 结束位置
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * 得到快近页数
	 * 
	 * @return 快近页数
	 */
	public int getSkipSize() {
		return this.skipSize;
	}

	/**
	 * 设置记录条数
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
		if (count <= 0)
			return;
		this.pageCount = count / pageSize + ((count % pageSize == 0) ? 0 : 1);
		if (currentPage > pageCount)
			currentPage = pageCount;
		if (currentPage <= 0)
			currentPage = 1;

		begin = (currentPage - 1) * pageSize;
		end = currentPage * pageSize;
		if (end >= count)
			end = count;

		currentSkip = (currentPage / skipSize) * skipSize + 1;
		if (currentPage % skipSize == 0)
			currentSkip = currentSkip - skipSize;
	}

	/**
	 * 是否可以到第一页
	 * 
	 * @return
	 */
	public boolean hasPreviousPage() {
		return (this.currentPage > 1);
	}

	/**
	 * 是否可以到下一页
	 * 
	 * @return
	 */
	public boolean hasNextPage() {
		return (this.currentPage < this.pageCount);
	}
	
	public boolean isLastPage() {
		return this.pageCount==0 || this.currentPage == this.pageCount;
	}

	/**
	 * 得到前一页页码
	 * 
	 * @return
	 */
	public int previous() {
		if (this.currentPage > 1)
			return this.currentPage - 1;
		else
			return 1;
	}

	public int getPrevious() {
		if (this.currentPage > 1){
			return  this.currentPage - 1;
		}else{
			return 1;
		}
	}

	/**
	 * 得到下一页页码
	 * 
	 * @return
	 */
	public int next() {
		if (this.currentPage < this.pageCount)
			return this.pageCount + 1;
		else
			return this.pageCount;
	}

	public int getNext() {
		if (this.currentPage < this.pageCount){
			return this.currentPage + 1;
		} else {
			return  this.pageCount;
		}
	}

	/**
	 * 是否可以向前快进
	 * 
	 * @return
	 */
	public boolean isCanSkipForward() {
		return getSkipForward() > 0;
	}

	/**
	 * 得到向前快近的页码
	 * 
	 * @return
	 */
	public int getSkipForward() {
		return this.currentSkip - skipSize;
	}

	/**
	 * 是否可以向后快进
	 *
	 * @return
	 */
	public boolean isCanSkipBackward() {
		return (getSkipBackward() <= this.pageCount);
	}

	/**
	 * 得到向后快近的页码
	 * 
	 * @return
	 */
	public int getSkipBackward() {
		return this.currentSkip + skipSize;
	}

	/**
	 * 得到当前显示的页码
	 * 
	 * @return
	 */
	public int[] getCurrentSkipPageNumbers() {
		int count = skipSize;
		if (currentSkip + skipSize > pageCount)
			count = pageCount - currentSkip + 1;
		int[] Result = new int[count];
		for (int i = 0; i < count; i++) {
			Result[i] = currentSkip + i;
		}
		return Result;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPage;
		result = prime * result + pageSize;
		result = prime * result + skipSize;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagination other = (Pagination) obj;
		if (currentPage != other.currentPage)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (skipSize != other.skipSize)
			return false;
		return true;
	}
	
	

}
