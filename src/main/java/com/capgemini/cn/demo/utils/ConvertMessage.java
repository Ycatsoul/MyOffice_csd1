package com.capgemini.cn.demo.utils;

import com.capgemini.cn.demo.userSystem.dto.MailBoxDTO;
import com.capgemini.cn.demo.userSystem.entity.Message;
import com.capgemini.cn.demo.userSystem.entity.MessageToUser;
import com.capgemini.cn.demo.userSystem.vo.request.MailBoxEditReq;
import com.capgemini.cn.demo.userSystem.vo.request.MessageEditReq;
import com.capgemini.cn.demo.userSystem.vo.request.MessageInsertReq;
import com.capgemini.cn.demo.userSystem.vo.request.MessagePublishReq;
import com.capgemini.cn.demo.userSystem.vo.response.MailResp;

/**
 * @Description:
 * @Classname :ConvertMessage
 * @author: Skye Kong
 * @date: 2019/9/20
 */

public class ConvertMessage {

    /**
     *将 vo 类中的信息转成实体类的信息
     * @param vo
     * @return
     */

    public static Message convertMesVoToEntity(MessageInsertReq vo){
        Message entity = new Message();
        entity.setMessageId(vo.getMessageId());
        entity.setTitle(vo.getTitle());
        entity.setBeginTime(vo.getBeginTime());
        entity.setEndTime(vo.getEndTime());
        entity.setContent(vo.getContent());
        entity.setIfPublish(vo.getIfPublish());
        entity.setIfAll(vo.getIfAll());
        entity.setFromUserId(vo.getFromUserId());
        entity.setRecordTime(vo.getRecordTime());
        return entity;
    }

    public static MessageToUser convertSendVoToEntity(MessageInsertReq vo){
        MessageToUser entity = new MessageToUser();
        entity.setIfRead(vo.getIfRead());
        return entity;
    }

    public static Message convertMessageEditReqToMessage(MessageEditReq vo){
        Message entity = new Message();
        entity.setMessageId(vo.getMessageId());
        entity.setTitle(vo.getTitle());
        entity.setBeginTime(vo.getBeginTime());
        entity.setEndTime(vo.getEndTime());
        entity.setContent(vo.getContent());
        return entity;
    }

    public static Message convertPublishVoToEntity(MessagePublishReq vo){
        Message entity = new Message();
        entity.setMessageId(vo.getMessageId());
        return entity;
    }

    public static MailResp convertMailDtoToVo(MailBoxDTO dto){
        MailResp vo = new MailResp();
        vo.setMessageId(dto.getMessageId());
        vo.setName(dto.getName());
        vo.setIfRead(dto.getIfRead());
        vo.setTitle(dto.getTitle());
        vo.setRecordTime(dto.getRecordTime());
        vo.setMessageTypeName(dto.getMessageTypeName());
        vo.setContent(dto.getContent());
        vo.setSendUser(dto.getSendUser());
        return vo;
    }

    public static MailBoxDTO covertReadVoToDto(MailBoxEditReq vo){
        MailBoxDTO dto = new MailBoxDTO();
        dto.setId(vo.getId());
        return dto;
    }
}

