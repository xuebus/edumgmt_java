package net.shinc.service.edu.business.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.edu.Problem;
import net.shinc.orm.mybatis.bean.edu.ProblemHasVideoBase;
import net.shinc.orm.mybatis.mappers.edu.ProblemHasVideoBaseMapper;
import net.shinc.orm.mybatis.mappers.edu.ProblemMapper;
import net.shinc.service.common.QNService;
import net.shinc.service.common.QRService;
import net.shinc.service.edu.business.ProblemService;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;



/**
 * @ClassName: ProblemService
 * @Description: 题目服务接口实现
 * @author hushichong
 * @date 2015年9月15日 下午1:03:21
 */
@Service
public class ProblemServiceImpl implements ProblemService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ProblemMapper problemMapper;
	@Autowired
	private ProblemHasVideoBaseMapper problemHasVideoBaseMapper;
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	private QNService qnService;
	
	@Autowired
	private QRService qrService;

	@SuppressWarnings("unchecked")
	@Override
	public Integer addProblem(Problem problem) {
		Map param = new HashMap();
		param.put(QRService.QRPARAM_PROBLEMID, problem.getId());
		param.put(QRService.QRPARAM_TYPE, QRService.QRPARAM_TYPE_PROBLEMID);
		param.put(QRService.QRPARAM_ID, problem.getId());
		String link = qrService.generateQrCode(param);
		problem.setTwoCode(link);
		
		Integer seq = this.sqlSessionTemplate.selectOne("net.shinc.orm.mybatis.mappers.edu.ProblemMapper.selectMaxSeq", problem.getBookId());
		if(seq != null) {
			seq++;
		} else {
			seq = 1;
		}
		problem.setSeq(seq);
		
		return problemMapper.insert(problem);
	}

	@Override
	public Integer updateProblem(Problem problem) {
		return problemMapper.update(problem);
	}

	@Override
	public Integer deleteProblemById(Integer id) {
		return problemMapper.deleteById(id);
	}

	@Override
	public Problem getProblemById(Integer id) {
		return (Problem)problemMapper.findById(id);
	}

	@Override
	public List<Problem> getProblemList(Problem problem) {
		return problemMapper.findAll(problem);
	}

	@Override
	public Integer addProblemVideoBase(Problem problem) {
		int i = 0;
		if (StringUtils.isNotEmpty(problem.getVideoBaseIds())) {
			for (String videoBaseId : StringUtils.split(problem.getVideoBaseIds(), ",")) {
				ProblemHasVideoBase p = new ProblemHasVideoBase();
				p.setProblemId(problem.getId());
				p.setVideoBaseId(Integer.valueOf(videoBaseId));
				p.setVideoType(problem.getVideoType());
				try {
					problemHasVideoBaseMapper.insert(p);
					i++;
				} catch(DuplicateKeyException e) {
					
				}
			}
		}
		return i;
	}

	@Override
	public Integer deleteProblemVideoBaseById(Integer id) {
		return problemHasVideoBaseMapper.deleteById(id);
	}

	@Override
	public List<Map> getProblemVideoBaseList(ProblemHasVideoBase problemHasVideoBase) {
		return problemMapper.getProblemVideoBaseList(problemHasVideoBase);
	}

	@Override
	public boolean isProblemHasVideo(Problem problem) {
		ProblemHasVideoBase problemHasVideoBase = new ProblemHasVideoBase();
		problemHasVideoBase.setProblemId(problem.getId());
		List list = problemHasVideoBaseMapper.findAll(problemHasVideoBase);
		if (list == null || list.size() == 0) {
			return false;
		}
		return true;
	}

}
