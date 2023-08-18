package com.example.freecontact.support.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EpOneToOneVo {

    private Long id;
    private String title;
    private String detail;
    private String email;
    private int ing;
    private String updateDate;
    private String createDate;
    private String categories;

}
