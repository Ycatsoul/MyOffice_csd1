package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.entity.Message;
import com.capgemini.cn.demo.userSystem.entity.MessageToUser;
import com.capgemini.cn.demo.userSystem.entity.ReadCommonMessage;
import com.capgemini.cn.demo.userSystem.vo.request.BranchDepReq;
import com.capgemini.cn.demo.userSystem.vo.request.MessageDeleteReq;
import com.capgemini.cn.demo.userSystem.vo.request.MessageReq;
import com.capgemini.cn.demo.userSystem.vo.response.BranchResp;
import com.capgemini.cn.demo.userSystem.vo.response.DepartResp;
import com.capgemini.cn.demo.userSystem.vo.response.MessageResp;
import com.capgemini.cn.demo.userSystem.vo.response.UserResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MessageMapper {
    List<MessageResp> selectMessageList(@Param("messageReq") MessageReq messageReq);

    Long count(@Param("messageReq") MessageReq messageReq);

    Integer deleteMessageById(@Param("messageDeleteReq") MessageDeleteReq messageDeleteReq);

    Integer insertMessage(@Param("message") Message message);

    Integer insertToUser(@Param("messageToUser") MessageToUser messageToUser);
    //通过类型id查询类型名
    Integer getIdByTypeName(@Param("typeName") String typeName);

    Integer searchMessageId();

    Integer updateAllSend(Integer messageId);
    //通过日期查找消息
    List<MessageResp> selectMessageByDay(@Param("messageReq") MessageReq messageReq);
    //通过本周查找消息
    List<MessageResp> selectMessageByWeek(@Param("messageReq") MessageReq messageReq);
    //通过本月查找消息
    List<MessageResp> selectMessageByMonth(@Param("messageReq") MessageReq messageReq);
    //通过消息id检查是否发布
    Integer getIfPublishById(Integer messageId);
    //修改消息
    Integer updateMessage(@Param("messageEdit") Message messageEdit);
    //发布消息
    Integer publishMessage(@Param("messagePublish") Message messagePublish);
    //通过消息id查找userid
    List<Long> getUserIdByMessageId(Integer messageId);
    //添加发布的消息
    Integer insertReadCommonMessage(@Param("readCommonMessages") List<ReadCommonMessage> readCommonMessages);
    //查找机构列表
    List<BranchResp> selectBranchList(BranchDepReq branchDepReq);
    //查找部门列表
    List<DepartResp> selectDepartList(BranchDepReq branchDepReq);
    //查找员工列表
    List<UserResp> selectUserList(BranchDepReq branchDepReq);
}
