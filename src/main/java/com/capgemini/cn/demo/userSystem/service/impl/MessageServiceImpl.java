package com.capgemini.cn.demo.userSystem.service.impl;

import com.capgemini.cn.demo.userSystem.dto.MailBoxDTO;
import com.capgemini.cn.demo.userSystem.entity.Message;
import com.capgemini.cn.demo.userSystem.entity.MessageToUser;
import com.capgemini.cn.demo.userSystem.entity.ReadCommonMessage;
import com.capgemini.cn.demo.userSystem.mapper.MailBoxMapper;
import com.capgemini.cn.demo.userSystem.mapper.MessageMapper;
import com.capgemini.cn.demo.userSystem.mapper.UserMapper;
import com.capgemini.cn.demo.userSystem.service.MessageService;
import com.capgemini.cn.demo.userSystem.vo.request.*;
import com.capgemini.cn.demo.userSystem.vo.response.*;
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
    /*@Override
    public Integer insertMessage(MessageInsertReq messageInsertReq) {
        List<Message> messages = new ArrayList<>();
        Message message = ConvertMessage.convertMesVoToEntity(messageInsertReq);
        message.setType(messageMapper.getIdByTypeName(messageInsertReq.getMessageTypeName()));
        messages.add(message);
        messageMapper.insertMessage(messages);
        Integer m = messageMapper.searchMessageId();
        MessageToUser messageToUser = ConvertMessage.convertSendVoToEntity(messageInsertReq);
        messageToUser.setMessageId(messageMapper.searchMessageId());
        List<Long> a = messageInsertReq.getUserId();
        Integer b = messageInsertReq.getIfAll();
        if(b == 0){
            for(int i = 0; i < a.size(); i++){
                messageToUser.setTouserId(a.get(i));
                List<MessageToUser> messageToUsers = new ArrayList<>();
                messageToUsers.add(messageToUser);
                messageMapper.insertToUser(messageToUsers);
            }
        }
        if(b == 1){
            messageMapper.updateAllSend(m);
            List<Long> v = userMapper.getToUserId(messageInsertReq.getFromUserId());
            for(int j = 0; j < v.size(); j++){
                messageToUser.setTouserId(a.get(j));
                List<MessageToUser> messageToUsers = new ArrayList<>();
                messageToUsers.add(messageToUser);
                messageMapper.insertToUser(messageToUsers);
            }
        }
        return 0;
    }*/

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
        messageListResp.setListCount(mailBoxMapper.countMessage(messageCountReq));
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
        messageListResp.setListCount(mailBoxMapper.countAlreadyPublish(messageCountReq));
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
            if(mailBoxDTO.getIfAll() == 1){
                mailBoxDTO.setSendUser("所有人");
            }else {
                mailBoxDTO.setSendUser("特定对象");
            }
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
    public UserListResp selectUserList(BranchDepReq branchDepReq) {
        UserListResp userListResp = new UserListResp();
        List<UserResp> userResps = messageMapper.selectUserList(branchDepReq);
        userListResp.setUserList(userResps);
        return userListResp;
    }
}
