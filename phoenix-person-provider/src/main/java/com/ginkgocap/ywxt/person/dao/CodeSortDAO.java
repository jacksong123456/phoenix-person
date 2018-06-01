package com.ginkgocap.ywxt.person.dao;

import java.util.List;

import com.ginkgocap.ywxt.person.model.CodeSort;
import com.ginkgocap.ywxt.person.model.IndustryDirection;

/**
 * 编码接口
 *
 * @author yangjie
 */
public interface CodeSortDAO {

    /**
     * 按照类型查询编码表
     *
     * @param codeType
     * @return
     */
    public List<CodeSort> findCodeSortByType(String codeType);

    public CodeSort get(Long id) ;

    public List<CodeSort> selectBySortId(String sortId) ;
    
    public List<CodeSort> selectCodeSortName(String sortId,String name);
    
    public List<IndustryDirection> getIndustryDirection(int pid,int index,int size);

    public long getIndustryDirectionCount(int pid);
    
    public List<IndustryDirection> getIndustryDirectionBykeyword(String keyword,int size);
}
