package net.shinc.service.common.impl;

import java.util.List;

import net.shinc.orm.mybatis.bean.common.AdminUser;
import net.shinc.orm.mybatis.bean.common.AuthGroupHasAuth;
import net.shinc.orm.mybatis.bean.common.Authority;
import net.shinc.orm.mybatis.bean.common.AuthorityGroup;
import net.shinc.orm.mybatis.bean.common.Company;
import net.shinc.orm.mybatis.mappers.common.AdminUserHasAuthGroupMapper;
import net.shinc.orm.mybatis.mappers.common.AdminUserMapper;
import net.shinc.orm.mybatis.mappers.common.AuthGroupHasAuthMapper;
import net.shinc.orm.mybatis.mappers.common.AuthorityGroupMapper;
import net.shinc.service.common.AuthorityGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * @ClassName AuthorityGroupServiceImpl 
 * @Description 权限组功能实现
 * @author guoshijie 
 * @date 2015年7月30日 下午12:25:39
 */
@Service
public class AuthorityGroupServiceImpl implements AuthorityGroupService {

	@Autowired
	private AuthorityGroupMapper authorityGroupMapper;
	
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@Autowired
	private AuthGroupHasAuthMapper authGroupHasAuthMapper;
	
	@Autowired
	private AdminUserHasAuthGroupMapper adminUserHasAuthGroupMapper;
	
	@Override
	public List<AuthorityGroup> getAuthorityGroupList(Company company) {
		if(null != company ) {
			return authorityGroupMapper.selectAllAuthorityGroup(company);
		}
		return null;
	}

	@Override
	public Integer getAuthorityGroupListCount() {
		return authorityGroupMapper.getAuthorityGroupListCount();
	}

	@Override
	public AuthorityGroup getAuthorityGroupById(AuthorityGroup authorityGroup) {
		if(null != authorityGroup) {
			return authorityGroupMapper.selectByPrimaryKey(authorityGroup.getId());
		}
		return null;
	}

	@Override
	public Integer addAuthorityGroup(AuthorityGroup authorityGroup) {
		if(null != authorityGroup) {
			return authorityGroupMapper.insert(authorityGroup);
		}
		return 0;
	}

	@Override
	@Transactional
	public Integer deleteAuthorityGroup(Integer authGroupId) {
		if(null != authGroupId){
			adminUserHasAuthGroupMapper.deleteAdminUserHasAuthGroup(authGroupId);//解除该权限组与用户的对应关系
			authGroupHasAuthMapper.deleteAuthGroupHasAuth(authGroupId);//删除权限组所有权限
			return authorityGroupMapper.deleteByPrimaryKey(authGroupId);//删除权限组
		}
		return 0;
	}

	@Override
	public Integer updateAuthorityGroup(AuthorityGroup authorityGroup) {
		if(null != authorityGroup){
			return authorityGroupMapper.updateByPrimaryKeySelective(authorityGroup);
		}
		return 0;
	}

	@Override
	public List<AdminUser> getAdminUserListByPosition(AuthorityGroup authorityGroup,PageBounds pageBounds) {
		List<AdminUser> list = adminUserMapper.getAdminUserListByPosition(authorityGroup.getId(),pageBounds);
		return list;
	}

	@Override
	public Integer addAuthGroupHasAuth(List<AuthGroupHasAuth> list) {
		if(null != list && list.size() > 0) {
			return authGroupHasAuthMapper.insertBatch(list);
		}
		return 0;
	}
	
	@Override
	public Integer deleteAuthGroupHasAuth(Integer authGroupId) {
		if(null != authGroupId) {
			return authGroupHasAuthMapper.deleteAuthGroupHasAuth(authGroupId);
		}
		return 0;
	}

	@Override
	public List<Authority> getAuthorityList(AuthorityGroup authorityGroup) {
		if(null != authorityGroup) {
			return authGroupHasAuthMapper.getAuthList(authorityGroup);
		}
		return null;
	}

	@Override
	public Integer deleteAuthGroupHasUser(Integer authGroupId) {
		return null;
	}

}
