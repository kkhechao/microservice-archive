package com.zqkh.archive.context.appservice.impl.domain.repository.query;

import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.FamilyMemberDmo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hty
 * @create 2018-01-30 17:09
 **/

public interface FamilyMemberQuery {
    List<FamilyMemberDmo> selectFamilyMemberByCreaterId(String createrId);

    /**
     *
     * @param createId
     * @return
     */
    List<String> getFamilyMemberIdByCreateId(@Param("createId") String createId);

    /**
     * 根据家庭成员姓名查询家庭成员编号
     * @param name:家庭成员姓名
     * @return
     */
    List<String> searchFamilyIdByName(@Param("name") String name);
}
