package org.xiaomi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiaomi.entity.KimiMessage;
import org.xiaomi.mapper.KimiMessageMapper;
import java.util.List;

@Service
public class KimiMessageService {

    @Autowired
    private KimiMessageMapper kimiMessageMapper;

    public KimiMessage getById(int id) {
        return kimiMessageMapper.selectById(id);
    }

    public List<KimiMessage> getAll() {
        return kimiMessageMapper.selectAll();
    }

    public int insertMessage(KimiMessage kimiMessage) {
        return kimiMessageMapper.insert(kimiMessage);
    }

    public int updateMessage(KimiMessage kimiMessage) {
        return kimiMessageMapper.updateById(kimiMessage);
    }

    public int deleteMessage(int id) {
        return kimiMessageMapper.deleteById(id);
    }

    public List<KimiMessage> getByQuestion(String question) {
        return kimiMessageMapper.selectByQuestion(question);
    }

    public List<KimiMessage> getByTime(String startTime, String endTime) {
        return kimiMessageMapper.selectByTime(startTime,endTime);
    }
}