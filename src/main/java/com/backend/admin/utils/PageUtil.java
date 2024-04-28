package com.backend.admin.utils;

import com.backend.admin.constant.Constant;
import com.backend.admin.dto.PageDto;


public class PageUtil {
    private Integer page = Constant.PAGE;
    private Integer limit = Constant.LIMIT;
    public PageDto pageVerify(PageDto pageDto) {
        if(pageDto.getPage() != null && pageDto.getPage() > 0) {
            this.page = pageDto.getPage();
        }
        if(pageDto.getLimit() != null && pageDto.getLimit() > 0) {
            this.limit = pageDto.getLimit();
        }
        return new PageDto(this.page,this.limit);
    }
}
