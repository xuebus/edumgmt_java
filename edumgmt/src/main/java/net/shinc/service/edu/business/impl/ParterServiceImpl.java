package net.shinc.service.edu.business.impl;

import java.util.List;

import net.shinc.orm.mybatis.bean.edu.Parter;
import net.shinc.orm.mybatis.mappers.edu.ParterMapper;
import net.shinc.service.edu.business.ParterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ParterService
 * @Description: 书商服务接口实现
 * @author hushichong
 * @date 2015年9月15日 下午1:03:21
 */
@Service
public class ParterServiceImpl implements ParterService {
	
	@Autowired
	private ParterMapper parterMapper;

	@Override
	public Integer addParter(Parter parter) {
		return parterMapper.insert(parter);
	}

	@Override
	public Integer updateParter(Parter parter) {
		return parterMapper.update(parter);
	}

	@Override
	public Integer deleteParterById(Integer id) {
		return parterMapper.deleteById(id);
	}

	@Override
	public Parter getParterById(Integer id) {
		return parterMapper.findById(id);
	}

	@Override
	public List<Parter> getParterList(Parter parter) {
		return parterMapper.findAll(parter);
	}

	

}
