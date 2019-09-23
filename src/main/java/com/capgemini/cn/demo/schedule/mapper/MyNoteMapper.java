package com.capgemini.cn.demo.schedule.mapper;

import com.capgemini.cn.demo.schedule.bean.MyNote;
import com.capgemini.cn.demo.schedule.vo.MyNoteVo;
import com.capgemini.cn.demo.utils.IdToBeJson;

import java.util.List;

/**
 * @author FanTao
 */
public interface MyNoteMapper {
	List<MyNote> selectAllMyNote(String name);
	void insertMyNote(MyNote myNote);
	void deleteMyNoteById(IdToBeJson id);
	void updateMyNoteById(MyNoteVo myNoteVo);
}
