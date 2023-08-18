package com.example.freecontact.epRecruit.dao;

import com.example.freecontact.epRecruit.vo.EpRecruitFilesVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EpRecruitFilesDao {

    //    기업 구인 사진들 등록
    public int epRecruitFilesSave(EpRecruitFilesVo epRecruitFilesVo);

    public List<EpRecruitFilesVo> findEpRecruitFilesAll();

}
