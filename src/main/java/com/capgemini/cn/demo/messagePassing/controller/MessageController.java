package com.capgemini.cn.demo.messagePassing.controller;

import com.capgemini.cn.demo.baseVo.RespBean;
import com.capgemini.cn.demo.messagePassing.vo.request.*;
import com.capgemini.cn.demo.messagePassing.vo.response.*;
import com.capgemini.cn.demo.messagePassing.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:消息传递控制器
 * @Classname :MessageController
 * @author: Skye Kong
 * @date: 2019/9/18
 */
@Slf4j
@Api
@RestController
@RequestMapping("/myoffice/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @ApiOperation(value = "查询消息记录")
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public RespBean selectMessageList(@RequestBody MessageReq messageReq) {
        MessageListResp messageListResp = messageService.selectMessageList(messageReq);

        if (messageListResp != null) {
            return RespBean.ok(messageListResp);
        }

        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "通过本日查询消息记录")
    @ResponseBody
    @RequestMapping(value = "/searchByDay", method = RequestMethod.POST)
    public RespBean selectMessageByDay(@RequestBody MessageReq messageReq){
        MessageListResp messageListResp = messageService.selectMessageListByDay(messageReq);

        if(messageListResp != null){
            return RespBean.ok(messageListResp);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "通过本周查询消息记录")
    @ResponseBody
    @RequestMapping(value = "/searchByWeek", method = RequestMethod.POST)
    public RespBean selectMessageByWeek(@RequestBody MessageReq messageReq){
        MessageListResp messageListResp = messageService.selectMessageListByWeek(messageReq);

        if(messageListResp != null){
            return RespBean.ok(messageListResp);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "通过本月查询消息记录")
    @ResponseBody
    @RequestMapping(value = "/searchByMonth", method = RequestMethod.POST)
    public RespBean selectMessageByMonth(@RequestBody MessageReq messageReq){
        MessageListResp messageListResp = messageService.selectMessageListByMonth(messageReq);

        if(messageListResp != null){
            return RespBean.ok(messageListResp);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "查询所有机构")
    @ResponseBody
    @RequestMapping(value = "/listBranch", method = RequestMethod.POST)
    public RespBean listBranch(@RequestBody BranchDepReq branchDepReq){
        BranchListResp branchListResp = messageService.selectBranchList(branchDepReq);
        if(branchListResp != null){
            return RespBean.ok(branchListResp);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "查询所有部门")
    @ResponseBody
    @RequestMapping(value = "/listDepart", method = RequestMethod.POST)
    public RespBean listDepart(@RequestBody BranchDepReq branchDepReq){
        DepartListResp departListResp = messageService.selectDepartList(branchDepReq);
        if(departListResp != null){
            return RespBean.ok(departListResp);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "查询所有员工")
    @ResponseBody
    @RequestMapping(value = "/listUser", method = RequestMethod.POST)
    public RespBean listUser(@RequestBody BranchDepReq branchDepReq){
        UserListResp userListResp = messageService.selectUserList(branchDepReq);
        if(userListResp != null){
            return RespBean.ok(userListResp);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "新增消息记录")
    @ResponseBody
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public RespBean addMessage(@RequestBody MessageInsertReq messageInsertReq, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("currentUserId");
        Long operationUserId = null;
        if(object != null){
            operationUserId = (Long)object;
        }
        messageInsertReq.setFromUserId(operationUserId);
        System.out.println(messageInsertReq.getMessageId());
        if(messageService.insertMessage(messageInsertReq) != null){
            return RespBean.ok("新增成功！");
        }
        return RespBean.error("新增失败！");
    }

    @ApiOperation(value = "查询机构下对应的部门")
    @ResponseBody
    @RequestMapping(value = "/listDepByBranch", method = RequestMethod.POST)
    public RespBean listDepByBranch(@RequestBody UserReq userReq){
        if(userReq.getBranchId() != null){
            DepartListResp departListResp = messageService.selectDepartListByBranch(userReq);
            return RespBean.ok(departListResp);
        }
        return RespBean.error("查询失败！该机构下没有部门！");
    }

    @ApiOperation(value = "查询机构或部门下对应的员工")
    @ResponseBody
    @RequestMapping(value = "/listUserByDepBranch", method = RequestMethod.POST)
    public RespBean listUserByDepBranch(@RequestBody UserReq userReq){
        if(userReq.getBranchName() != null && userReq.getDepartName() == null){
            //通过机构名找到机构ID
            Long branchId = messageService.getBranchNameById(userReq);
            //通过机构ID查找该机构下的所有员工
            UserListResp userListResp = messageService.selectUsersByBranchId(branchId);
            return RespBean.ok(userListResp);
        }
        if(userReq.getDepartName() != null){
            UserListResp userListResp = messageService.selectUserByDep(userReq);
            return RespBean.ok(userListResp);
        }
        return RespBean.error("查询失败！该机构下没有部门！");
    }

    @ApiOperation(value = "模糊查询")
    @ResponseBody
    @RequestMapping(value = "/listUserName", method = RequestMethod.POST)
    public RespBean listUserName(@RequestBody UserReq userReq){
        //通过部门名查询该部门下的员工名
        if(userReq.getDepartName() != null && userReq.getName() == null){
            UserListResp userListResp = messageService.selectUserByDep(userReq);
            return RespBean.ok(userListResp);
        }
        //模糊查询员工名
        if(userReq.getName() != null){
            UserListResp userListResp = messageService.selectUserByName(userReq);
            return RespBean.ok(userListResp);
        }
        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "修改消息")
    @ResponseBody
    @RequestMapping(value = "/updateMessage", method = RequestMethod.POST)
    public RespBean updateMessage(@RequestBody MessageEditReq messageEditReq) {
        if(messageService.updateMessage(messageEditReq) != 0){
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("已发布，无法更新！");
    }

    @ApiOperation(value = "发布消息")
    @ResponseBody
    @RequestMapping(value = "/publishMessage", method = RequestMethod.POST)
    public RespBean publishMessage(@RequestBody MessagePublishReq messagePublishReq) {
        if (messageService.publishMessage(messagePublishReq) != 0) {
            return RespBean.ok("发布成功！");
        }
        return RespBean.error("发布失败!已发布过！");
    }

    @ApiOperation(value = "通过消息ID删除消息记录")
    @ResponseBody
    @RequestMapping(value = "/deleteByMessageId", method = RequestMethod.DELETE)
    public RespBean deleteMessageById(@RequestBody MessageDeleteReq messageDeleteReq) {
        if (messageService.deleteMessageById(messageDeleteReq) != 0) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "计算收件箱所有消息数量")
    @ResponseBody
    @RequestMapping(value = "/countMessage", method = RequestMethod.POST)
    public RespBean countMessage(@RequestBody MessageCountReq messageCountReq, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("currentUserId");
        Long operationUserId = null;
        if(object != null){
            operationUserId = (Long)object;
        }
        messageCountReq.setOperationId(operationUserId);
        MessageListResp messageListResp = messageService.countMessage(messageCountReq);
        if(messageListResp != null){
            return RespBean.ok(messageListResp);
        }
        return RespBean.error("计算失败!");
    }

    @ApiOperation(value = "计算收件箱所有未读消息数量")
    @ResponseBody
    @RequestMapping(value = "/countNoRead", method = RequestMethod.POST)
    public RespBean countNoRead(@RequestBody MessageCountReq messageCountReq, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("currentUserId");
        Long operationUserId = null;
        if(object != null){
            operationUserId = (Long)object;
        }
        messageCountReq.setOperationId(operationUserId);
        MessageListResp messageListResp = messageService.countNoRead(messageCountReq);
        if(messageListResp != null){
            return RespBean.ok(messageListResp);
        }
        return RespBean.error("计算失败!");
    }

    @ApiOperation(value = "计算已发送消息数量")
    @ResponseBody
    @RequestMapping(value = "/countAlreadyPublish", method = RequestMethod.POST)
    public RespBean countAlreadyPublish(@RequestBody MessageCountReq messageCountReq, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("currentUserId");
        Long operationUserId = null;
        if(object != null){
            operationUserId = (Long)object;
        }
        messageCountReq.setOperationId(operationUserId);
        MessageListResp messageListResp = messageService.countAlreadyPublish(messageCountReq);
        if(messageListResp != null){
            return RespBean.ok(messageListResp);
        }
        return RespBean.error("计算失败!");
    }

    @ApiOperation(value = "列出收件箱")
    @ResponseBody
    @RequestMapping(value = "/listMail", method = RequestMethod.POST)
    public RespBean loadMail(@RequestBody MessageCountReq messageCountReq, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("currentUserId");
        Long operationUserId = null;
        if(object != null){
            operationUserId = (Long)object;
        }
        messageCountReq.setOperationId(operationUserId);
        MailListResp mailListResp = messageService.listMail(messageCountReq);

        if (mailListResp != null) {
            return RespBean.ok(mailListResp);
        }

        return RespBean.error("查询失败！");
    }

    @ApiOperation(value = "读取消息")
    @ResponseBody
    @RequestMapping(value = "/updateRead", method = RequestMethod.POST)
    public RespBean updateRead(@RequestBody MailBoxEditReq mailBoxEditReq) {
        if(messageService.updateRead(mailBoxEditReq) != 0){
            return RespBean.ok("读取成功");
        }
        return RespBean.error("读取失败!");
    }

    @ApiOperation(value = "列出已发送信息")
    @ResponseBody
    @RequestMapping(value = "/listAlreadySend", method = RequestMethod.POST)
    public RespBean loadAlreadySend(@RequestBody MessageCountReq messageCountReq, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("currentUserId");
        Long operationUserId = null;
        if(object != null){
            operationUserId = (Long)object;
        }
        messageCountReq.setOperationId(operationUserId);
        MailListResp mailListResp = messageService.listAlreadySend(messageCountReq);

        if (mailListResp != null) {
            return RespBean.ok(mailListResp);
        }

        return RespBean.error("查询失败！");
    }

}
