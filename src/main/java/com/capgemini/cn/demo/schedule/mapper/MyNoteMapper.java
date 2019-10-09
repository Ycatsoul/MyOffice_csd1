package com.capgemini.cn.demo.schedule.mapper;

import com.capgemini.cn.demo.schedule.bean.MyNote;
import com.capgemini.cn.demo.schedule.vo.MyNoteVo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author FanTao
 */
@Mapper
@Repository
public interface MyNoteMapper {
	List<MyNote> selectAllMyNote(String name);
	void insertMyNote(MyNote myNote);
	void deleteMyNoteById(IdToBeJson id);
	void updateMyNoteById(MyNoteVo myNoteVo);
}
