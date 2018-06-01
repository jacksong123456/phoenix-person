package com.ginkgocap.ywxt.person.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ginkgocap.ywxt.person.model.PersonCategoryRelation;

public interface PersonCategoryRelationDAO {

    public void createPersonCategoryRelation(PersonCategoryRelation personCategoryRelation);
    public void createPersonCategoryRelationBatch(List<PersonCategoryRelation> personCategoryRelationList);

    public void updatePersonCategoryRelation(Map<String, Object> parameter);

    public List<PersonCategoryRelation> selectFavoriteRelation(Map<String, Object> parameter);

    public void cancelFavorite(PersonCategoryRelation relation);

    public int deleteByPerIdAndperType(Long personId, Integer personType);

    public void batchCreate(List<PersonCategoryRelation> personCategoryRelations) throws Exception;

    public int deleteByPerIdAndperTypeAndCtype(Long personId, Integer personType, Integer ctype);

    public int deleteByPerIdAndperTyAndCtyAndUid(Long personId, Integer personType, Integer ctype, Long userId);

    public List<PersonCategoryRelation> selectByUidAndPerAndPerTy(Long userId, Long personId, Byte personType);

    public int deleteByPerIdAndPerTypeAndUid(Long personId, Integer personType , Long userId) ;

    public Long selectUserCount(Integer personType , Long userId) ;

    public Set<Long> selectUser(Integer personType , Long userId);
    public Set<Long> selectCollectUser(Long userId);
    public Set<Long> selectPersonUser(Long userId);
    public Set<Long> selectAllPerson(Long userId);
    List<Long> selectAll();

    List<Map<String, Object>> selectPersonCategories(Long userId, Long personId, Byte personType);
    
    public long selectCountByPersonIdForUserId(Map<String,Object> map);
    
	public long countPersonByCategoryId(Long id,Long userId);
}
