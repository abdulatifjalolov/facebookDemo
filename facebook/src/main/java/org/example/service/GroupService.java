package org.example.service;

import org.example.model.Group;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupService implements BaseService {
    static Map<Group,List<Integer>> groups=new HashMap<>();
    public boolean createGroup(String name,int adminId,List<Integer> membersIdList){
        for (Group c : groups.keySet()) {
            if (c!=null){
                if (!c.getName().equals(name)){
                    Group group=new Group();
                    group.setName(name);
                    group.setAdminId(adminId);
                    groups.put(group,membersIdList);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addMember(Group group,int memberId,List<Integer> membersIdList){
        for (Group group1 : groups.keySet()) {
            if (group1!=null){
                if (group1.getName().equals(group.getName())) {
                    membersIdList.add(memberId);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean deleteMember(Group group,int memberId,List<Integer> membersIdList){
        for (Group group1 : groups.keySet()) {
            if (group1!=null){
                if (group1.getName().equals(group.getName())) {
                    membersIdList.remove(memberId);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object getById(int id) {
        for (Group group : groups.keySet()) {
            if (group!=null){
                if (group.getId()==id) {
                    return group;
                }
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        for (Group group : groups.keySet()) {
            if (group!=null){
                if (group.getId()==id) {
                    groups.remove(group);
                    return true;
                }
            }
        }
        return false;
    }



}
