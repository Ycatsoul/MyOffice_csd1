package com.capgemini.cn.demo.schedule.controller;

import com.capgemini.cn.demo.schedule.bean.MyNote;
import com.capgemini.cn.demo.schedule.service.MyNoteService;
import com.capgemini.cn.demo.schedule.vo.MyNoteVo;
import com.capgemini.cn.demo.utils.IdToBeJson;
import com.capgemini.cn.demo.utils.RespBean;
import com.capgemini.cn.demo.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "我的便签")
public class MyNoteController {
	@Autowired
	MyNoteService myNoteService;
	@PutMapping("selectAllMyNote")
	@ApiOperation(value = "查找所有自己的便签")
	public RespBean findAllMyNote() {
		List<MyNote> list = myNoteService.selectAllMyNote(UserUtils.getCurrentUser().getUsername());
		return RespBean.ok("成功", list);
	}
	@PutMapping("addMyNote")
	@ApiOperation(value = "添加便签")
	public RespBean addMyNote(@Valid @RequestBody MyNoteVo myNoteVo) {
		MyNote myNote = new MyNote();
		myNote.setCreateTime(new Date());
		myNote.setCreateUser(UserUtils.getCurrentUser().getUsername());
		myNote.setNoteContent(myNoteVo.getNoteContent());
		myNote.setNoteTitle(myNoteVo.getNoteTitle());
		myNoteService.insertMyNote(myNote);
		return RespBean.ok("添加成功");
	}
	@PutMapping("modifyMyNoteById")
	@ApiOperation(value = "根据id修改便签")
	public RespBean modifyMyNoteById(@Valid @RequestBody MyNoteVo myNoteVo) {
		myNoteService.updateMyNoteById(myNoteVo);
		return RespBean.ok("修改成功");
	}
	@PutMapping("deleteMyNoteById")
	@ApiOperation(value = "根据id删除便签")
	public RespBean deleteMyNoteById(@Valid @RequestBody IdToBeJson id) {
		myNoteService.deleteMyNoteById(id);
		return RespBean.ok("删除成功");
	}
}
