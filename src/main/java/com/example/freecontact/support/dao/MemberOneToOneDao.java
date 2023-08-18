package com.example.freecontact.support.dao;


import com.example.fc.pageNation.pageVo.support.supportVo.MemberOneToOneVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberOneToOneDao {

    //    멤버 일대일문의 등록
    int mPersonalInquire(MemberOneToOneVo memberOneToOneVo);
    //    공지사항 수정

}
