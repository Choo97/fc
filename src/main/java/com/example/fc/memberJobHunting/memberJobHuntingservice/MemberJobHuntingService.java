package com.example.fc.memberJobHunting.memberJobHuntingservice;

import com.example.fc.member.memberVo.MemberVo;
import com.example.fc.memberJobHunting.memberJobHuntingDao.MemberJobHuntingDao;
import com.example.fc.memberJobHunting.memberJobHuntingDao.MemberJobHuntingFilesDao;
import com.example.fc.memberJobHunting.memberJobHuntingDao.MemberJobHuntingThumnailDao;
import com.example.fc.memberJobHunting.memberJobHuntingVo.MemberJobHuntingFilesVo;
import com.example.fc.memberJobHunting.memberJobHuntingVo.MemberJobHuntingStackVo;
import com.example.fc.memberJobHunting.memberJobHuntingVo.MemberJobHuntingVo;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MemberJobHuntingService {

    private final MemberJobHuntingDao memberJobHuntingDao;
    private final MemberJobHuntingFilesDao memberJobHuntingFilesDao;

    private final MemberJobHuntingThumnailDao memberJobHuntingThumnailDao;

    @Value("${JobHuntingUploadPath}")
    String savePath; //파일저장 위치

    @Value("${JobHuntingUploadThumnailPath}")
    String thumnailPath;


    //사용자 정보 불러오기 공통 메서드
    public MemberVo memberInfo(Long memberId) {
        System.out.println("memberId ser = " + memberId);
        MemberVo info = memberJobHuntingDao.findMemberById(memberId);

        return info;
    }



    /*멀티 파티를 사용했었을 떄 코드*/
    //구직게시글 insert기능, 타임스탬프 이용함으로써 게시글 저장 날짜 자동 생성(now()사용 x)
//    public int insertJobHunting(MemberJobHuntingVo jobHunt, MultipartFile[] file) throws IOException {
//
//        System.out.println("file.length = " + file.length);
//        //파일이 미첨부시 바로 저장
//        if (file[0].isEmpty() || file[0].getOriginalFilename() == null || file[0].getOriginalFilename().equals("")) {
//            jobHunt.setFileAttached(0);//첨부된 파일 없다.
//
//            int result = memberJobHuntingDao.insertJobHunting(jobHunt); //파일 저장
//
//            /*member_stack테이블에 stack 저장*/
//            String[] stack = jobHunt.getStack().split(" "); //공백을 기준으로 스택을 나눔
//            HashMap map = new HashMap();
//
//            //게시글 정보로 member_board 값을 찾음 => member_stack에 값을 넣기 위해
//            int memberBoard = memberJobHuntingDao.findJobHuntingMemberBoard(jobHunt);
//            String string_member_board = String.valueOf(memberBoard);
//
//            //찾아온 member_board로 member_stack값에 저장
//            for (int i = 0; i < stack.length; i++) {
//                map.put("stack", stack[i]);
//                map.put("string_member_board", string_member_board);
//                int insertMemberStack = memberJobHuntingDao.insertMemberStack(map);
//            }
//            return 1;
//
//        } else {//파일이 첨부 된 경우
//
//            jobHunt.setFileAttached(1); // 파일 첨부값을 1로 변경
//            System.out.println("jobHunt = " + jobHunt.getFileAttached());
//            int result = memberJobHuntingDao.insertJobHunting(jobHunt); //파일 저장 == 게시글 저장*/
//
//            /*member_stack테이블에 stack 저장*/
//            String[] stack = jobHunt.getStack().split(" "); //공백을 기준으로 스택을 나눔
//            HashMap map = new HashMap();
//            int memberBoard = memberJobHuntingDao.findJobHuntingMemberBoard(jobHunt); //게시글 고유번호 찾기
//            String stringMemberBoard = String.valueOf(memberBoard);  // int를 String으로 변환*/ 왜필요한거지??
//            System.out.println("memberBoard1 = " + memberBoard);
//
//            if (jobHunt.getStack() != null) {
//                //찾아온 member_board로 member_stack값에 저장
//                for (int i = 0; i < stack.length; i++) {
//                    System.out.println(" = 여기는 들어가니?");
//                    map.put("stack", stack[i]);
//                    map.put("stringMemberBoard", stringMemberBoard);
//                    int insertMemberStack = memberJobHuntingDao.insertMemberStack(map);
//                }
//            }
//
//            //파일 관련 코드 시작
//            for (MultipartFile getFile : file) {
//                UUID uuid = UUID.randomUUID(); // 중복되지 않은 값
//                String originalFileName = getFile.getOriginalFilename(); //파일의 본래 파일명
//                String storedFileName = uuid + "_" + originalFileName; // 파일의 중복되지 않는 값을 더한 파일명
//
//                File saveFile = new File(savePath, storedFileName);
//                getFile.transferTo(saveFile);//파일이동
//
//                //섬네일 파일 이름 생성
//                String thumbnailName ="s_"+storedFileName;
//                // 파일 경로 + 이름
//                File thumbnailFile = new File(savePath, "s_"+storedFileName);
//
//                // BufferedImage에 ImageIo를 이용하여 saveFile 객체를 담음
//                BufferedImage bo_image = ImageIO.read(saveFile);
//                // 비어있는 이미지 생성
//                BufferedImage bt_image = new BufferedImage(100, 100, BufferedImage.TYPE_3BYTE_BGR);
//
//                // 오지리날 이미지를 썸네일 이미지로 변환
//                Graphics2D graphic = bt_image.createGraphics();
//                graphic.drawImage(bo_image, 0, 0,100,100, null);
//
//                // 저장 :: 1. 만든 썸네일 이미지 2. 사진 형식 3. File
//                ImageIO.write(bt_image, "png", thumbnailFile);
//
//
//                /*파일 저장*/
//                MemberJobHuntingFilesVo files = MemberJobHuntingFilesVo.toSetterFilesVo((long) memberBoard, originalFileName, storedFileName, savePath);
//                int resultFiles = memberJobHuntingFilesDao.insertJobHuntingFiles(files);
//                System.out.println("resultFiles = " + resultFiles);
//
//                /*섬네일 저장*/
//                MemberJobHuntingThumnailVo thumbnails = MemberJobHuntingThumnailVo.builder().memberBoard(Long.valueOf(memberBoard)).savePath(thumnailPath).build();
//                int resultThumbnails = memberJobHuntingThumnailDao.insertJobHuntingThumnailVo(thumbnails);
//
//
//            }
//        }
//        /*   System.out.println("서비스 게시판 저장 result = " + result);*/
//        return 1;
//    }

    //게시글 저장
    public int insertJobHunting(MemberJobHuntingVo jobHunt) {
        System.out.println("jobHunt.getShowingPeriod() = " + jobHunt.getShowingPeriod());
        System.out.println("jobHunt 싸비스 = " + jobHunt);
        int result = memberJobHuntingDao.insertJobHunting(jobHunt);
        if (result == 0) return 0; //게시글 실패

        //게시글 스택 저장
        //구직 게시글에 한 컬럼에 받아온 여러 기술 값을 배열에 하나씩 삽입
        if (jobHunt.getStack() != null) {
            String[] stack = jobHunt.getStack().split(",");
            HashMap map = new HashMap();

            //게시글 정보로 member_board 값을 찾음 => member_stack에 값을 넣기 위해
            int member_board = memberJobHuntingDao.findJobHuntingMemberBoard(jobHunt);
            String string_member_board = String.valueOf(member_board);

            //찾아온 member_board로 member_stack값에 저장
            for (int i = 0; i < stack.length; i++) {
                map.put("memberId", jobHunt.getMemberId());
                map.put("stack", stack[i]);
                map.put("stringMemberBoard", string_member_board);
                int insertMemberStack = memberJobHuntingDao.insertMemberStack(map);
            }
            return result;// 게시글 성공

        }
        return result;
    }

    // 게시글 파일 저장
    public JsonObject insertFile(MultipartFile multipartFile) {
        JsonObject jsonObject = new JsonObject();

        String fileRoot = savePath; //저장될 외부 파일 경로  C:\\summernote_image\\
        String originalFileName = multipartFile.getOriginalFilename();    //오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));   //파일 확장자

        String savedFileName = UUID.randomUUID() + extension;    //저장될 파일 명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);    //파일 저장
            jsonObject.addProperty("url", "/jobHuntingContent/" + savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);    //저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        System.out.println("/uploadSummernoteImageFile >>> 요청드러옹ㅁ");
        return jsonObject;

    }




/*        if (epRecruitVO.getStack() != null) {
            //          스택테이블에 저장
            String[] stackLst = epRecruitVO.getStack().split(",");
//          스택 길이만큼 스택 테이블에 저장
            for (String stack : stackLst) {
//              필요한것: 현재 로그인한 id, 게시글 번호, 스택
                Long epId = epVo.getEpId();
                Long epBoard = epRecruitDao.epRecruitLastId();
                EpRecruitStackVO epRecruitStackVO = EpRecruitStackVO.builder().epId(epId).epBoard(epBoard).stack(stack).build();
                int res = epRecruitStackDao.epRecruitStackSave(epRecruitStackVO);
                System.out.println("StackSave>" + stack + ">>>" + res);
            }
        }*/


    //모든 구직 게시글
    /*    public List<MemberJobHuntingVo> findAllJobHunting(int limit, int offset) {

     *//*컨트롤러에서 정한 한 페이지에 보여줄 게시글 갯수랑 초기 페이지값을 dao로 보내쥼*//*
        List<MemberJobHuntingVo> jobHuntingList = memberJobHuntingDao.findAllJobHunting(limit, offset);

        return jobHuntingList;
    }*/

    public List<MemberJobHuntingVo> findAllJobHunting() {

        /*컨트롤러에서 정한 한 페이지에 보여줄 게시글 갯수랑 초기 페이지값을 dao로 보내쥼*/
        List<MemberJobHuntingVo> jobHuntingList = memberJobHuntingDao.findAllJobHunting();

        return jobHuntingList;
    }

    //poster기능 : 특정 게시글 보기
    public MemberJobHuntingVo findAllByMemberBoard(MemberJobHuntingVo memberBoard) {
        MemberJobHuntingVo writerInfo = memberJobHuntingDao.findAllByMemberBoard(memberBoard);//작성자 모든 정보 불러오기

        return writerInfo;
    }

    //게시글에 해당되는 모든 파일들 찾기
    public List<MemberJobHuntingFilesVo> findAllFilesByMemberBoard(Long memberBoard) {
        List<MemberJobHuntingFilesVo> writerFilesInfo = memberJobHuntingFilesDao.findAllFilesByMemberBoard(memberBoard);//작성자가 등록한 이미지 모두 불러오기

        return writerFilesInfo;
    }


    //게시글에 해당되는 모든 스택찾기
    public List<MemberJobHuntingStackVo> findAllStacksByMemberBoard(MemberJobHuntingVo memberBoard) {
        List<MemberJobHuntingStackVo> list = memberJobHuntingDao.findAllStacksByMemberBoard(memberBoard);

        return list;
    }

    //게시글 수정
    public int updateJobHuntingBoard(MemberJobHuntingVo memberBoard , String showingDate, String showingHour, String showingMin) {
        String showingPeriod = showingDate + " " + showingHour + ":" + showingMin + ":59"; //yyyy-mm-dd hh:mm:ss 으로 저장
        memberBoard.setShowingPeriod(showingPeriod);
        int result = memberJobHuntingDao.updateJobHuntingBoard(memberBoard);

        return result;
    }

    //게시글 삭제
    public int deleteMemberBoardByMemberBoard(Long memberBoard) {
        int result = memberJobHuntingDao.deleteMemberBoardByMemberBoard(memberBoard);

        return 0;
    }

}
