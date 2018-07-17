package com.zqkh.archive.context.appservice.impl;

import com.jovezhao.nest.PageList;
import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.StringIdentifier;
import com.jovezhao.nest.ddd.builder.ConstructLoader;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.builder.RepositoryLoader;
import com.jovezhao.nest.ddd.identifier.IdGenerator;
import com.jovezhao.nest.mybatis.PageParames;
import com.jovezhao.nest.starter.AppService;
import com.zqkh.archive.context.appservice.impl.domain.AttachArchive;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.AttachArchiveDmoWithBLOBs;
import com.zqkh.archive.context.appservice.impl.domain.repository.query.AttachArchiveQuery;
import com.zqkh.archive.context.appservice.inter.AttachArchiveService;
import com.zqkh.archive.feign.dto.AttachArchiveDto;
import com.zqkh.archive.feign.dto.AttachArchiveReqDto;
import com.zqkh.common.exception.BusinessException;
import com.zqkh.common.result.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AppService
public class AttachArchiveServiceImpl implements AttachArchiveService {

    private EntityLoader<AttachArchive> archiveConstructLoader = new ConstructLoader<>(AttachArchive.class);
    private EntityLoader<AttachArchive> attachRepositoryLoader = new RepositoryLoader<>(AttachArchive.class);

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    private AttachArchiveQuery attachArchiveQuery;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void addAttachArchive(AttachArchiveDto attachArchiveDto) {
        Identifier id = IdGenerator.getInstance().generate(AttachArchive.class);
        AttachArchive attachArchive = archiveConstructLoader.create(id);
        attachArchive.init(attachArchiveDto.getType(), attachArchiveDto.getDate(), attachArchiveDto.getDesc(), attachArchiveDto.getMemberId(), attachArchiveDto.getAttachList());
    }


    @Override
    public PageResult<AttachArchiveDto> getAttachArchiveByType(AttachArchiveReqDto attachArchiveReqDto) {
        PageParames pageParames = PageParames.create(attachArchiveReqDto.getPageIndex(), attachArchiveReqDto.getPageSize());
        PageList<AttachArchiveDmoWithBLOBs> attachArchiveDmoWithBLOBsPageList = attachArchiveQuery.getByMemberId(attachArchiveReqDto.getMemberId(), attachArchiveReqDto.getType(), pageParames);
        List<AttachArchiveDto> lists = attachArchiveDmoWithBLOBsPageList.getList().stream().map(a ->{
            String attach = a.getAttach();
            return new AttachArchiveDto(a.getId(), a.getType(), a.getDate(), a.getDescription(), a.getFamilyMemberId(), StringUtils.isNotEmpty(attach) ? Arrays.asList(a.getAttach().split(",")) : null);}
        ).collect(Collectors.toList());
        PageResult<AttachArchiveDto> attachArchiveDtoPageResult = new PageResult<>();
        attachArchiveDtoPageResult.setList(lists);
        attachArchiveDtoPageResult.setPageSize(attachArchiveDmoWithBLOBsPageList.getPageSize());
        attachArchiveDtoPageResult.setTotalCount(attachArchiveDmoWithBLOBsPageList.getTotalCount());
        return attachArchiveDtoPageResult;
    }

    @Override
    public void editAttachArchive(AttachArchiveDto attachArchiveDto) {
        AttachArchive attachArchive = attachRepositoryLoader.create(new StringIdentifier(attachArchiveDto.getId()));
        if (attachArchive == null) {
            throw new BusinessException("未找到对应附件档案", attachArchiveDto.getId());
        }
        attachArchive.update(attachArchiveDto.getDate(), attachArchiveDto.getDesc(), attachArchiveDto.getAttachList());
    }

    @Override
    public void delAttachArchive(String id) {
        AttachArchive attachArchive = attachRepositoryLoader.create(new StringIdentifier(id));
        attachArchive.delete();
    }
}
