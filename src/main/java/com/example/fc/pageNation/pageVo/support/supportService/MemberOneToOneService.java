package com.example.fc.pageNation.pageVo.support.supportService;

import com.example.fc.pageNation.pageVo.support.supportDao.MemberOneToOneDao;
import com.example.fc.pageNation.pageVo.support.supportVo.MemberOneToOneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberOneToOneService {

    @Autowired
    MemberOneToOneDao memberOneToOneDao;


    //    공지사항 등록
    public void mPersonalInquire(MemberOneToOneVo memberOneToOneVo) {
            memberOneToOneDao.mPersonalInquire(memberOneToOneVo);
    }
}
