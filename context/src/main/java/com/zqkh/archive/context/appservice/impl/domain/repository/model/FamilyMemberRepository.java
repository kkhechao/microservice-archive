package com.zqkh.archive.context.appservice.impl.domain.repository.model;

import com.jovezhao.nest.ddd.Identifier;
import com.jovezhao.nest.ddd.builder.EntityLoader;
import com.jovezhao.nest.ddd.repository.IRepository;
import com.zqkh.archive.context.appservice.impl.domain.FamilyMember;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.FamilyMemberDmoMapper;
import com.zqkh.archive.context.appservice.impl.domain.repository.mappers.dmo.FamilyMemberDmo;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author wenjie
 * @date 2017/12/7 0007 14:53
 */
@Repository("FamilyMember_Repository")
public class FamilyMemberRepository implements IRepository<FamilyMember>{

    @Autowired
    private FamilyMemberDmoMapper familyMemberDmoMapper;

    @Autowired
    private DozerBeanMapper modelMapper;

    @Override
    public FamilyMember getEntityById(Identifier id, EntityLoader<FamilyMember> entityLoader) {
        FamilyMemberDmo familyMemberDmo = familyMemberDmoMapper.selectByPrimaryKey(id.toValue());
        if (familyMemberDmo == null) {
            return null;
        }

        FamilyMember familyMember = entityLoader.create(id);
        modelMapper.map(familyMemberDmo, familyMember);

        return familyMember;
    }

    @Override
    public void save(FamilyMember familyMember) {
        if (familyMember == null) {
            return;
        }
        FamilyMemberDmo familyMemberDmo = modelMapper.map(familyMember, FamilyMemberDmo.class);
        if(familyMemberDmoMapper.updateByPrimaryKey(familyMemberDmo) == 0){
            familyMemberDmoMapper.insert(familyMemberDmo);
        }
    }

    @Override
    public void remove(FamilyMember familyMember) {
        if (familyMember == null) {
            return;
        }
        familyMemberDmoMapper.deleteByPrimaryKey(familyMember.getId().toValue());
    }
}
