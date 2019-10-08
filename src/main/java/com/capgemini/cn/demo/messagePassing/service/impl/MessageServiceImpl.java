package com.capgemini.cn.demo.messagePassing.service.impl;

import com.capgemini.cn.demo.messagePassing.vo.request.*;
import com.capgemini.cn.demo.messagePassing.vo.response.*;
import com.capgemini.cn.demo.messagePassing.dto.MailBoxDTO;
import com.capgemini.cn.demo.messagePassing.entity.Message;
import com.capgemini.cn.demo.messagePassing.entity.MessageToUser;
import com.capgemini.cn.demo.messagePassing.mapper.MailBoxMapper;
import com.capgemini.cn.demo.messagePassing.mapper.MessageMapper;
import com.capgemini.cn.demo.userSystem.mapper.UserMapper;
import com.capgemini.cn.demo.messagePassing.service.MessageService;
import com.capgemini.cn.demo.utils.ConvertMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:消息传递接口实现类
 * @Classname :MessageServiceImpl
 * @author: Skye Kong
 * @date: 2019/9/18
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MailBoxMapper mailBoxMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 显示消息列表
     * @param messageReq
     * @return
     */
    @Override
    public MessageListResp selectMessageList(MessageReq messageReq) {
        MessageListResp messageListResp = new MessageListResp();
        List<MessageResp> messages = messageMapper.selectMessageList(messageReq);
        List<MessageResp> messageList = new ArrayList<>();
        for(MessageResp messageResp : messages){
            List<Long> users = messageMapper.selectToUserList(messageResp.getMessageId());
            List<String> userList = new ArrayList<>();
            if(messageResp.getIfAll() == 1){
                messageResp.setSendUser("所有人");
            }else {
                messageResp.setSendUser("特定对象");
                for(Long userId : users){
                    userList.add(messageMapper.getUserNameByUserId(userId));
                }
                messageResp.setUserList(userList);
            }
            messageList.add(messageResp);
        }
        messageListResp.setListCount(messageMapper.count(messageReq));
        messageListResp.setMessageList(messageList);
        return messageListResp;
    }

    /**
     * 通过日期查询消息列表
     * @param messageReq
     * @return
     */
    @Override
    public MessageListResp selectMessageListByDay(MessageReq messageReq) {
        MessageListResp messageListResp = new MessageListResp();
        List<MessageResp> messages = messageMapper.selectMessageByDay(messageReq);
        List<MessageResp> messageList = new ArrayList<>();
        for(MessageResp messageResp : messages){
            if(messageResp.getIfAll() == 1){
                messageResp.setSendUser("所有人");
            }else {
                messageResp.setSendUser("特定对象");
            }
            messageList.add(messageResp);
        }
        messageListResp.setListCount(messageMapper.count(messageReq));
        messageListResp.setMessageList(messageList);
        return messageListResp;
    }

    /**
     * 通过本周查询消息列表
     * @param messageReq
     * @return
     */
    @Override
    public MessageListResp selectMessageListByWeek(MessageReq messageReq) {
        MessageListResp messageListResp = new MessageListResp();
        List<MessageResp> messages = messageMapper.selectMessageByWeek(messageReq);
        List<MessageResp> messageList = new ArrayList<>();
        for(MessageResp messageResp : messages){
            if(messageResp.getIfAll() == 1){
                messageResp.setSendUser("所有人");
            }else {
                messageResp.setSendUser("特定对象");
            }
            messageList.add(messageResp);
        }
        messageListResp.setListCount(messageMapper.count(messageReq));
        messageListResp.setMessageList(messageList);
        return messageListResp;
    }

    /**
     * 通过本月查询消息列表
     * @param messageReq
     * @return
     */
    @Override
    public MessageListResp selectMessageListByMonth(MessageReq messageReq) {
        MessageListResp messageListResp = new MessageListResp();
        List<MessageResp> messages = messageMapper.selectMessageByMonth(messageReq);
        List<MessageResp> messageList = new ArrayList<>();
        for(MessageResp messageResp : messages){
            if(messageResp.getIfAll() == 1){
                messageResp.setSendUser("所有人");
            }else {
                messageResp.setSendUser("特定对象");
            }
            messageList.add(messageResp);
        }
        messageListResp.setListCount(messageMapper.count(messageReq));
        messageListResp.setMessageList(messageList);
        return messageListResp;
    }

    /**
     * 通过id删除消息
     * @param messageDeleteReq
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteMessageById(MessageDeleteReq messageDeleteReq) {
        return messageMapper.deleteMessageById(messageDeleteReq);
    }

    /**
     * 添加消息
     * @param messageInsertReq
     * @return
     */
    @Override
    public Integer insertMessage(MessageInsertReq messageInsertReq) {
        Message message = ConvertMessage.convertMesVoToEntity(messageInsertReq);
        message.setType(messageMapper.getIdByTypeName(messageInsertReq.getMessageTypeName()));
        messageMapper.insertMessage(message);
        //message.setMessageId(message.getMessageId());
        MessageToUser messageToUser = new MessageToUser();
        if(messageInsertReq.getIfAll() == 1){
            List<Long> userIds = userMapper.getAllUserIds();
            for(int i = 0; i < userIds.size(); i++){
                messageToUser.setMessageId(message.getMessageId());
                messageToUser.setTouserId(userIds.get(i));
                messageMapper.insertToUser(messageToUser);
            }
        }else {
            messageToUser.setMessageId(message.getMessageId());
            messageToUser.setTouserId(messageInsertReq.getUserId());
            messageMapper.insertToUser(messageToUser);
        }
        return 0;
    }

    /**
     * 发布消息
     * @param messagePublishReq
     * @return
     */
    @Override
    public Integer publishMessage(MessagePublishReq messagePublishReq) {
        //List<ReadCommonMessage> readCommonMessages = new ArrayList<>();
        Message message = ConvertMessage.convertPublishVoToEntity(messagePublishReq);
        return messageMapper.publishMessage(message);

        /*List<Long> userIds = messageMapper.getUserIdByMessageId(message.getMessageId());
        for(Long userId  : userIds){
            ReadCommonMessage readCommonMessage = new ReadCommonMessage();
            readCommonMessage.setMessageId(messagePublishReq.getMessageId());
            readCommonMessage.setUserId(userId);
            readCommonMessages.add(readCommonMessage);
        }
        return messageMapper.insertReadCommonMessage(readCommonMessages);*/
    }

    /**
     * 修改消息
     * @param messageEditReq
     * @return
     */
    @Override
    public Integer updateMessage(MessageEditReq messageEditReq) {
        Integer ifPublished = messageMapper.getIfPublishById(messageEditReq.getMessageId());
        if(ifPublished == 1){
            return 0;
        }else{
            Message message = ConvertMessage.convertMessageEditReqToMessage(messageEditReq);
            message.setType(messageMapper.getIdByTypeName(messageEditReq.getMessageTypeName()));
            return messageMapper.updateMessage(message);
        }
    }

    @Override
    public MessageListResp countMessage(MessageCountReq messageCountReq) {
        MessageListResp messageListResp = new MessageListResp();
        //统计messagetouser表中消息条数
        Long i = mailBoxMapper.countMessage(messageCountReq);
        //减去删除了多少条消息
        Long a = i - messageCountReq.getCount();
        messageListResp.setListCount(a);
        return messageListResp;
    }

    @Override
    public MessageListResp countNoRead(MessageCountReq messageCountReq) {
        MessageListResp messageListResp = new MessageListResp();
        messageListResp.setListCount(mailBoxMapper.countNoRead(messageCountReq));
        return messageListResp;
    }

    @Override
    public MessageListResp countAlreadyPublish(MessageCountReq messageCountReq) {
        MessageListResp messageListResp = new MessageListResp();
        //统计messagetouser表中消息条数
        Long i = mailBoxMapper.countMessage(messageCountReq);
        //减去删除了多少条消息
        Long a = i - messageCountReq.getCount();
        messageListResp.setListCount(a);
        return messageListResp;
    }

    @Override
    public MailListResp listMail(MessageCountReq messageCountReq) {
        MailListResp mailListResp = new MailListResp();
        List<MailBoxDTO> mailBoxDTOs = mailBoxMapper.listMail(messageCountReq);
        List<MailResp> mailResps = new ArrayList<>();
        for(MailBoxDTO mailBoxDTO : mailBoxDTOs){
            mailResps.add(ConvertMessage.convertMailDtoToVo(mailBoxDTO));
        }
        mailListResp.setListCount(mailBoxMapper.countMessage(messageCountReq));
        mailListResp.setMailMessageList(mailResps);
        return mailListResp;
    }

    @Override
    public MailListResp listAlreadySend(MessageCountReq messageCountReq) {
        MailListResp mailListResp = new MailListResp();
        List<MailBoxDTO> mailBoxDTOs = mailBoxMapper.listAlreadySend(messageCountReq);
        List<MailResp> mailResps = new ArrayList<>();
        for(MailBoxDTO mailBoxDTO : mailBoxDTOs){
            mailResps.add(ConvertMessage.convertMailDtoToVo(mailBoxDTO));
        }
        mailListResp.setListCount(mailBoxMapper.countMessage(messageCountReq));
        mailListResp.setMailMessageList(mailResps);
        return mailListResp;
    }

    @Override
    public Integer updateRead(MailBoxEditReq mailBoxEditReq) {
        MailBoxDTO mailBoxDTO = ConvertMessage.covertReadVoToDto(mailBoxEditReq);
        return mailBoxMapper.updateRead(mailBoxDTO);
    }

    @Override
    public BranchListResp selectBranchList(BranchDepReq branchDepReq) {
        BranchListResp branchListResp = new BranchListResp();
        List<BranchResp> branchResps = messageMapper.selectBranchList(branchDepReq);
        branchListResp.setBranchList(branchResps);
        return branchListResp;
    }

    @Override
    public DepartListResp selectDepartList(BranchDepReq branchDepReq) {
        DepartListResp departListResp = new DepartListResp();
        List<DepartResp> departResps = messageMapper.selectDepartList(branchDepReq);
        departListResp.setDepartList(departResps);
        return departListResp;
    }

    @Override
    public DepartListResp selectDepartListByBranch(UserReq userReq) {
        Long branchId = messageMapper.getBranchNameById(userReq);
        DepartListResp departListResp = new DepartListResp();
        List<DepartResp> departResps = messageMapper.selectDepartListByBranch(branchId);
        departListResp.setDepartList(departResps);
        departListResp.setBranchName(userReq.getBranchName());
        return departListResp;
    }

    @Override
    public UserListResp selectUserList(BranchDepReq branchDepReq) {
        UserListResp userListResp = new UserListResp();
        List<UserResp> userResps = messageMapper.selectUserList(branchDepReq);
        userListResp.setUserList(userResps);
        return userListResp;
    }

    @Override
    public UserListResp selectUserByDep(UserReq userReq) {
        UserListResp userListResp = new UserListResp();
        List<UserResp> userResps = messageMapper.selectUserListByDep(userReq.getDepartName());
        userListResp.setUserList(userResps);
        return userListResp;
    }

    @Override
    public UserListResp selectUserByName(UserReq userReq) {
        UserListResp userListResp = new UserListResp();
        List<UserResp> userResps = messageMapper.selectUserByName(userReq);
        userListResp.setUserList(userResps);
        return userListResp;
    }

    @Override
    public Long getBranchNameById(UserReq userReq) {
        Long branchId = messageMapper.getBranchNameById(userReq);
        return branchId;
    }

    @Override
    public List<DepartResp> selectDepart(Long branchId) {
        List<DepartResp> departResps = messageMapper.selectDepartListByBranch(branchId);
        return departResps;
    }

    @Override
    public UserListResp selectUsersByBranchId(Long branchId) {
        UserListResp userListResp = new UserListResp();
        List<UserResp> userResps = messageMapper.selectUsersByBranchId(branchId);
        userListResp.setUserList(userResps);
        return userListResp;
    }

}
