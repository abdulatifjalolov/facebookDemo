package org.example.service;

import org.example.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageService implements BaseService {
    static List<Message> messages=new ArrayList<>();

    public boolean createMessage(int userId,String text,int groupId){
        Message message=new Message();
        message.setText(text);
        message.setUserId(userId);
        message.setGroupId(groupId);
        if (message!=null){
            messages.add(message);
            return true;
        }
        return true;
    }

    @Override
    public Object getById(int id) {
        for (Message message : messages) {
            if (message!=null){
                if (message.getId()==id) {
                    return message;
                }
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
