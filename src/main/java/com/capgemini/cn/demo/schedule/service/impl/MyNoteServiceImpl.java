package com.capgemini.cn.demo.schedule.service.impl;

import com.capgemini.cn.demo.schedule.bean.MyNote;
import com.capgemini.cn.demo.schedule.mapper.MyNoteMapper;
import com.capgemini.cn.demo.schedule.service.MyNoteService;
import com.capgemini.cn.demo.schedule.vo.MyNoteVo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyNoteServiceImpl implements MyNoteService{
	@Autowired
	MyNoteMapper mapper;
	@Override
	public List<MyNote> selectAllMyNote(String name) {
		return mapper.selectAllMyNote(name);
	}

	@Override
	public void insertMyNote(MyNote myNote) {
		mapper.insertMyNote(myNote);
	}

	@Override
	public void deleteMyNoteById(IdToBeJson id) {
		mapper.deleteMyNoteById(id);
	}

	@Override
	public void updateMyNoteById(MyNoteVo myNoteVo) {
		mapper.updateMyNoteById(myNoteVo);
	}

}
