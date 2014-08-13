package com.base;
import java.util.List;
/**
 *
 分页封装类
 .
 可以适用于任何一张表
 *
 @author
 Administrator
 *
 */
@SuppressWarnings("unchecked")
public class  Pager<T> {

    private List<T> list;				//
    private int pageNumber;				//当前页号
    private int pageSize;				//每页显示的记录数
    private int totalPage;				//总页数
    private int totalRow;				//总记录数


    public Pager(List<T> list, int pageNumber, int pageSize, int totalPage, int totalRow) {
        this.list = list;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.totalRow = totalRow;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }
}