package com.example.freecontact.epRecruit.dao;

import com.example.freecontact.epRecruit.vo.EpRecruitEmailVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EpRecruitEmailDao {
    //채용 추천서 보내기
    int sendEmailToMember(EpRecruitEmailVo vo);
}
