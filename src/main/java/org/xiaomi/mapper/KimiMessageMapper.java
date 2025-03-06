package org.xiaomi.mapper;

import org.apache.ibatis.annotations.*;
import org.xiaomi.entity.KimiMessage;
import java.util.List;

@Mapper
public interface KimiMessageMapper {

    /**
     * 根据 ID 查询 KimiMessage
     */
    @Select("SELECT timestamp, answer, question FROM kimi_message WHERE id = #{id}")
    KimiMessage selectById(int id);

    /**
     * 根据 question 精确查询 KimiMessage 列表
     */
    @Select("SELECT timestamp, answer, question FROM kimi_message WHERE question = #{question}")
    List<KimiMessage> selectByQuestion(String question);

    /**
     * 查询所有 KimiMessage
     */
    @Select("SELECT timestamp, answer, question FROM kimi_message")
    List<KimiMessage> selectAll();

    @Select("SELECT * FROM kimi_message WHERE timestamp BETWEEN #{startTime} AND #{endTime}")
    List<KimiMessage> selectByTime(String startTime,String endTime);
    /**
     * 插入一条 KimiMessage 记录
     */
    @Insert("INSERT INTO kimi_message (timestamp, answer, question) VALUES (#{timestamp}, #{answer}, #{question})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(KimiMessage kimiMessage);

    /**
     * 根据 ID 更新 KimiMessage
     */
    @Update("UPDATE kimi_message SET timestamp = #{timestamp}, answer = #{answer}, question = #{question} WHERE id = #{id}")
    int updateById(KimiMessage kimiMessage);

    /**
     * 根据 ID 删除 KimiMessage
     */
    @Delete("DELETE FROM kimi_message WHERE id = #{id}")
    int deleteById(int id);
}