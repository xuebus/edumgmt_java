package net.shinc.service.edu.video.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.common.AdminUser;
import net.shinc.orm.mybatis.bean.common.QueryBean;
import net.shinc.orm.mybatis.bean.edu.Keyword;
import net.shinc.orm.mybatis.bean.edu.KnowledgePoint;
import net.shinc.orm.mybatis.bean.edu.VideoBase;
import net.shinc.orm.mybatis.bean.edu.VideoBaseKeywordKey;
import net.shinc.orm.mybatis.bean.edu.VideoBaseKnowledgePointKey;
import net.shinc.orm.mybatis.bean.edu.VideoDetail;
import net.shinc.orm.mybatis.bean.edu.VideoPastpaper;
import net.shinc.orm.mybatis.mappers.edu.VideoBaseKeywordMapper;
import net.shinc.orm.mybatis.mappers.edu.VideoBaseKnowledgePointMapper;
import net.shinc.orm.mybatis.mappers.edu.VideoBaseMapper;
import net.shinc.orm.mybatis.mappers.edu.VideoDetailMapper;
import net.shinc.orm.mybatis.mappers.edu.VideoPastpaperMapper;
import net.shinc.service.edu.video.VideoPastpaperService;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: VideoPastpaperServiceImpl
 * @Description: 真题、模拟题服务接口实现
 * @author hushichong
 * @date 2015年7月31日 下午5:43:01
 */
@Service
public class VideoPastpaperServiceImpl implements VideoPastpaperService {

	@Autowired
	private VideoPastpaperMapper videoPastpaperMapper;
	@Autowired
	private VideoBaseMapper videoBaseMapper;
	@Autowired
	private VideoDetailMapper videoDetailMapper;
	@Autowired
	private VideoBaseKnowledgePointMapper videoBaseKnowledgePointMapper;
	@Autowired
	private VideoBaseKeywordMapper videoBaseKeywordMapper;
	

	@Override
	public void deleteVideoPastpaperById(Integer id) {
		videoPastpaperMapper.deleteVideoPastpaperById(id);

	}

	@Override
	public Integer insertVideoPastpaper(VideoPastpaper videoPastpaper) {
		VideoBase videoBase = videoPastpaper.getVideoBase();
		videoBase.setAdminUserId(AdminUser.getCurrentUser().getId());
		videoBase.setUpdatetime(new Date());
		videoBaseMapper.insertVideoBase(videoBase);
		videoPastpaper.setVideoBaseId(videoBase.getId());
		// 插入视频详情
		if (videoPastpaper.getVideoBase() != null && videoPastpaper.getVideoBase().getVideoDetailList() != null && videoPastpaper.getVideoBase().getVideoDetailList().size() > 0) {
			for (VideoDetail vd : (List<VideoDetail>) videoPastpaper.getVideoBase().getVideoDetailList()) {
				vd.setVideoBaseId(videoBase.getId());
				vd.setUpdatetime(new Date());
				videoDetailMapper.insertVideoDetail(vd);
			}
		}

		// 插入知识点关系
		if (videoPastpaper.getVideoBase() != null && videoPastpaper.getVideoBase().getKnowledgetPointList() != null
				&& videoPastpaper.getVideoBase().getKnowledgetPointList().size() > 0) {
			for (KnowledgePoint vd : (List<KnowledgePoint>) videoPastpaper.getVideoBase().getKnowledgetPointList()) {
				VideoBaseKnowledgePointKey videoBaseKnowledgePointKey = new VideoBaseKnowledgePointKey();
				videoBaseKnowledgePointKey.setVideoBaseId(videoBase.getId());
				videoBaseKnowledgePointKey.setKnowledgePointId(vd.getId());
				videoBaseKnowledgePointMapper.insert(videoBaseKnowledgePointKey);
			}
		}

		// 插入关键字关系
		if (videoPastpaper.getVideoBase() != null && videoPastpaper.getVideoBase().getKeywordList() != null
				&& videoPastpaper.getVideoBase().getKeywordList().size() > 0) {
			for (Keyword vd : (List<Keyword>) videoPastpaper.getVideoBase().getKeywordList()) {
				VideoBaseKeywordKey videoBaseKeywordKey = new VideoBaseKeywordKey();
				videoBaseKeywordKey.setVideoBaseId(videoBase.getId());
				videoBaseKeywordKey.setKeywordId(vd.getId());
				videoBaseKeywordMapper.insertVideoKeyword(videoBaseKeywordKey);
			}
		}

		return videoPastpaperMapper.insertVideoPastpaper(videoPastpaper);
	}

	@Override
	public void updateVideoPastpaper(VideoPastpaper videoPastpaper) {
		VideoBase videoBase = videoPastpaper.getVideoBase();
		videoBase.setUpdatetime(new Date());
		
		videoPastpaperMapper.updateVideoPastpaper(videoPastpaper);
		videoBaseMapper.updateVideoBase(videoBase);
		
		// 更新视频详情
		if (videoPastpaper.getVideoBase() != null && videoPastpaper.getVideoBase().getVideoDetailList() != null && videoPastpaper.getVideoBase().getVideoDetailList().size() > 0) {
			for (VideoDetail vd : (List<VideoDetail>) videoPastpaper.getVideoBase().getVideoDetailList()) {
				vd.setVideoBaseId(videoBase.getId());
				vd.setUpdatetime(new Date());
				videoDetailMapper.updateVideoDetail(vd);
			}
		}

		// 更新知识点关系
		if (videoPastpaper.getVideoBase() != null && videoPastpaper.getVideoBase().getKnowledgetPointList() != null
				&& videoPastpaper.getVideoBase().getKnowledgetPointList().size() > 0) {
			for (KnowledgePoint vd : (List<KnowledgePoint>) videoPastpaper.getVideoBase().getKnowledgetPointList()) {
				VideoBaseKnowledgePointKey videoBaseKnowledgePointKey = new VideoBaseKnowledgePointKey();
				videoBaseKnowledgePointKey.setVideoBaseId(videoBase.getId());
				videoBaseKnowledgePointKey.setKnowledgePointId(vd.getId());
				videoBaseKnowledgePointMapper.deleteVideoBaseKnowledgePoint(videoBaseKnowledgePointKey);
				videoBaseKnowledgePointMapper.insert(videoBaseKnowledgePointKey);
			}
		}

		// 更新关键字关系
		if (videoPastpaper.getVideoBase() != null && videoPastpaper.getVideoBase().getKeywordList() != null
				&& videoPastpaper.getVideoBase().getKeywordList().size() > 0) {
			for (Keyword vd : (List<Keyword>) videoPastpaper.getVideoBase().getKeywordList()) {
				VideoBaseKeywordKey videoBaseKeywordKey = new VideoBaseKeywordKey();
				videoBaseKeywordKey.setVideoBaseId(videoBase.getId());
				videoBaseKeywordKey.setKeywordId(vd.getId());
				videoBaseKeywordMapper.deleteVideoKeywordById(videoBaseKeywordKey);
				videoBaseKeywordMapper.insertVideoKeyword(videoBaseKeywordKey);
			}
		}
		

	}

	@Override
	public VideoPastpaper getByVideoPastpaperById(Integer id) {
		return videoPastpaperMapper.getByVideoPastpaperById(id);
	}

	@Override
	public List<VideoPastpaper> getVideoPastpaperList(VideoPastpaper videoPastpaper) {
		return videoPastpaperMapper.getVideoPastpaperList(videoPastpaper);
	}

	@Override
	public Integer getVideoPastpaperListCount(VideoPastpaper videoPastpaper) {
		return videoPastpaperMapper.getVideoPastpaperListCount(videoPastpaper);
	}

	@Override
	public VideoPastpaper getVideoPastpaper(VideoPastpaper videoPastpaper) {
		List list = getVideoPastpaperList(videoPastpaper);
		if (list != null && list.size() == 1) {
			return (VideoPastpaper) list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Map> getVideoPastpaperAndRelevantInfoList(QueryBean queryBean,RowBounds rowBounds) {
		return videoPastpaperMapper.getVideoPastpaperAndRelevantInfoList(queryBean, rowBounds);
	}

	@Override
	public List<VideoPastpaper> getVideoPastpaperAndRelevantInfoListCount(VideoPastpaper videoPastpaper) {
		// TODO Auto-generated method stub
		return null;
	}

}