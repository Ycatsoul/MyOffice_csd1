package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.dto.MailBoxDTO;
import com.capgemini.cn.demo.userSystem.vo.request.MessageCountReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MailBoxMapper {
    //计算收件箱有多少条消息
    Long countMessage(@Param("messageCountReq") MessageCountReq messageCountReq);
    //统计未读消息数量
    Long countNoRead(@Param("messageCountReq") MessageCountReq messageCountReq);
    //统计已发送消息个数
    Long countAlreadyPublish(@Param("messageCountReq") MessageCountReq messageCountReq);
    //列出收件箱消息
    List<MailBoxDTO> listMail(@Param("messageCountReq") MessageCountReq messageCountReq);
    //列出收件箱已读消息
    List<MailBoxDTO> listAlreadySend(@Param("messageCountReq") MessageCountReq messageCountReq);
    //读取消息
    Integer updateRead(@Param("mailBoxDTO") MailBoxDTO mailBoxDTO);
}
