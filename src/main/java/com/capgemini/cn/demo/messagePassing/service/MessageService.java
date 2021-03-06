package com.capgemini.cn.demo.messagePassing.service;

import com.capgemini.cn.demo.messagePassing.vo.request.*;
import com.capgemini.cn.demo.messagePassing.vo.response.*;

import java.util.List;

public interface MessageService {
    //显示消息列表
    MessageListResp selectMessageList(MessageReq messageReq);
    //通过日期显示消息列表
    MessageListResp selectMessageListByDay(MessageReq messageReq);
    //通过本周显示消息列表
    MessageListResp selectMessageListByWeek(MessageReq messageReq);
    //通过本月纤细消息列表
    MessageListResp selectMessageListByMonth(MessageReq messageReq);
    //删除消息
    Integer deleteMessageById(MessageDeleteReq messageDeleteReq);
    //新增消息
    Integer insertMessage(MessageInsertReq messageInsertReq);
    //发布消息
    Integer publishMessage(MessagePublishReq messagePublishReq);
    //修改消息
    Integer updateMessage(MessageEditReq messageEditReq);
    //统计消息个数
    MessageListResp countMessage(MessageCountReq messageCountReq);
    //统计未读消息个数
    MessageListResp countNoRead(MessageCountReq messageCountReq);
    //统计已发送消息个数
    MessageListResp countAlreadyPublish(MessageCountReq messageCountReq);
    //显示收件箱消息列表
    MailListResp listMail(MessageCountReq messageCountReq);
    //显示收件箱已读消息列表
    MailListResp listAlreadySend(MessageCountReq messageCountReq);
    //读取消息
    Integer updateRead(MailBoxEditReq mailBoxEditReq);
    //返回机构列表
    BranchListResp selectBranchList(BranchDepReq branchDepReq);
    //返回部门列表
    DepartListResp selectDepartList(BranchDepReq branchDepReq);
    //通过机构查询部门列表
    DepartListResp selectDepartListByBranch(UserReq userReq);
    //返回员工列表
    UserListResp selectUserList(BranchDepReq branchDepReq);
    //通过部门名查询该部门下的员工
    UserListResp selectUserByDep(UserReq userReq);
    //模糊查询员工名
    UserListResp selectUserByName(UserReq userReq);
    //通过机构名查询机构ID
    Long getBranchNameById(UserReq userReq);
    //通过机构ID查找部门
    List<DepartResp> selectDepart(Long branchId);
    //通过机构ID查找该机构下的所有员工
    UserListResp selectUsersByBranchId(Long branchId);
}
