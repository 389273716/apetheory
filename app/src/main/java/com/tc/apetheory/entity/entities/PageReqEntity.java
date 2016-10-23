package com.tc.apetheory.entity.entities;

import com.tc.apetheory.common.GlobalConstants;

/**
 * @author tc
 * @version 1.0
 * @date 2016/4/6 21:44
 */
public class PageReqEntity {
    private int page = 1;           //否    当前第几页 i
    private int size = GlobalConstants.REQUEST_PAGE_SIZE;       //否    一页显示数量

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {

        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "page=" + page + "&size=" + size;
    }
}
