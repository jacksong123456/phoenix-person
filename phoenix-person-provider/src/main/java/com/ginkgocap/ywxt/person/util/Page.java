package com.ginkgocap.ywxt.person.util;

public class Page {
    public Long page  = 1l;
    public Long size = 10l ;
    public Long totalCount ;
    public Long totalPageCount = null ;
    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        if (page<1){
            page=1l ;
        }
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPageCount(Long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getStartNumber(){
        return (this.page-1)*this.size ;
    }
    public String getLimit(){
        return this.getStartNumber()+","+this.getSize() ;
    }
    public Long getTotalPageCount(){
        if (totalPageCount!=null){
            return totalPageCount ;
        }
        Long  tmp  =  totalCount%size ;
        totalPageCount  =totalCount/size ;
        if (tmp>0){
            totalPageCount++ ;
        }
        return totalPageCount ;
    }
}
