package net.shinc.orm.mybatis.mappers.edu;

import java.util.List;

import net.shinc.orm.mybatis.bean.edu.QuestionBank;
import net.shinc.orm.mybatis.bean.edu.QuestionBankQuestionTypeKey;
import net.shinc.orm.mybatis.bean.edu.QuestionType;

/** 
 * @ClassName QuestionBankQuestionTypeMapper 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年7月31日 下午5:00:42  
 */
public interface QuestionBankQuestionTypeMapper {
    int deleteByPrimaryKey(QuestionBankQuestionTypeKey key);

    int insert(QuestionBankQuestionTypeKey record);

    int insertSelective(QuestionBankQuestionTypeKey record);
    
    public List<QuestionType> getQuestionTypeByQuestionBank(QuestionBank questionBank);
}