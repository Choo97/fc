package com.example.fc.pageNation.pageVo.support.supportVo;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberOneToOneVo {

    private Long id;
    private String title;
    private String detail;
    private String email;
    private int ing;
    private String updateDate;
    private String createDate;
    private String categories;

}