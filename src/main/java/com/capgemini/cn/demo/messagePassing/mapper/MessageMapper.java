package com.capgemini.cn.demo.messagePassing.mapper;

import com.capgemini.cn.demo.messagePassing.entity.Message;
import com.capgemini.cn.demo.messagePassing.entity.MessageToUser;
import com.capgemini.cn.demo.messagePassing.entity.ReadCommonMessage;
import com.capgemini.cn.demo.messagePassing.vo.request.BranchDepReq;
import com.capgemini.cn.demo.messagePassing.vo.request.MessageDeleteReq;
import com.capgemini.cn.demo.messagePassing.vo.request.MessageReq;
import com.capgemini.cn.demo.messagePassing.vo.request.UserReq;
import com.capgemini.cn.demo.messagePassing.vo.response.BranchResp;
import com.capgemini.cn.demo.messagePassing.vo.response.DepartResp;
import com.capgemini.cn.demo.messagePassing.vo.response.MessageResp;
import com.capgemini.cn.demo.messagePassing.vo.response.UserResp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MessageMapper {
    //显示消息
    List<MessageResp> selectMessageList(@Param("messageReq") MessageReq messageReq);

    Long count(@Param("messageReq") MessageReq messageReq);

    Integer deleteMessageById(@Param("messageDeleteReq") MessageDeleteReq messageDeleteReq);

    Integer insertMessage(Message message);

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
    //添加发布的消息
    Integer insertReadCommonMessage(@Param("readCommonMessages") List<ReadCommonMessage> readCommonMessages);
    //查找机构列表
    List<BranchResp> selectBranchList(BranchDepReq branchDepReq);
    //查找部门列表
    List<DepartResp> selectDepartList(BranchDepReq branchDepReq);
    //查找员工列表
    List<UserResp> selectUserList(BranchDepReq branchDepReq);
    //通过消息ID查找发送对象ID
    List<Long> selectToUserList(Integer messageId);
    //通过员工ID查找员工名
    String getUserNameByUserId(Long userId);
    //通过机构ID查找部门
    List<DepartResp> selectDepartListByBranch(Long branchId);
    //通过机构名查找机构ID
    Long getBranchNameById(@Param("userReq") UserReq userReq);
    //通过部门名查询该部门下的员工
    List<UserResp> selectUserListByDep(String departName);
    //模糊查询员工名
    List<UserResp> selectUserByName(@Param("userReq") UserReq userReq);
    //通过机构ID查找该机构下的所有员工
    List<UserResp> selectUsersByBranchId(Long branchId);
}
