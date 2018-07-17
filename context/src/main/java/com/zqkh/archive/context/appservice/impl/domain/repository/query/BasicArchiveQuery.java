package com.zqkh.archive.context.appservice.impl.domain.repository.query;

import org.apache.ibatis.annotations.Param;

/**
 * @author hty
 * @create 2018-01-31 13:36
 **/

public interface BasicArchiveQuery {
     String selectBasicArchiveIdByMemberId(@Param("memberId") String memberId);
}
