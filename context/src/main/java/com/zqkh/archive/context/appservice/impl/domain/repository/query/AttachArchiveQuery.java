package com.zqkh.archive.context.appservice.impl.domain.repository.query;

import com.jovezhao.nest.PageList;
import com.jovezhao.nest.mybatis.PageParames;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.AttachArchiveDmoWithBLOBs;
import com.zqkh.common.result.PageResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wenjie
 * @date 2018/1/30 0030 17:20
 */
public interface AttachArchiveQuery {

    PageList<AttachArchiveDmoWithBLOBs> getByMemberId(@Param("id") String id, @Param("type") String type , PageParames pageParames);
}
