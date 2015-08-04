package net.shinc.service.edu.video.impl;

import java.util.List;

import net.shinc.orm.mybatis.bean.VideoPic;
import net.shinc.orm.mybatis.mappers.VideoPicMapper;
import net.shinc.service.edu.video.VideoPicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoPicServiceImpl implements VideoPicService {
	@Autowired
	private VideoPicMapper videlPicMapper;

	@Override
	public List<VideoPic> selectPicByVideoBaseId(Integer id) {
		if (null != id) {
			return videlPicMapper.selectPicByVideoBaseId(id);
		}
		return null;
	}
	
	@Override
	public VideoPic selectPicById(Integer id) {
		if (null != id) {
			return videlPicMapper.selectPicById(id);
		}
		return null;
	}

	@Override
	public Integer deletePicById(Integer id) {
		if (null != id) {
			return videlPicMapper.deletePicById(id);
		}
		return 0;
	}
	
	/**
	 * 批量删除截图
	 */
	@Override
	public Integer deletePicBatch(List<VideoPic> list) {
		if(null != list && list.size() > 0) {
			return videlPicMapper.deletePicBatch(list);
		}
		return 0;
	}

	@Override
	public Integer insertPic(VideoPic pic) {
		if (null != pic) {
			return videlPicMapper.insertPic(pic);
		}
		return 0;
		
	}
	/**
	 * 批量增加截图
	 */
	@Override
	public Integer insertPicBatch(List<VideoPic> list) {
		if(null != list && list.size() > 0) {
			return videlPicMapper.insertPicBatch(list);
		}
		return 0;
	}
}