package com.example.fc.pageNation.pageVo.support.supportDao;


import com.example.fc.pageNation.pageVo.support.supportVo.NoticeFilesVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeFilesDao {

    int insertNoticeFiles(NoticeFilesVo noticeFilesVo);

    List<NoticeFilesVo> noticeFilesList(int noticeBoard);

}
