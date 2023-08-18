package com.example.freecontact.epRecruit.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EpRecruitMainThumbnailVo {
  private Long id;
  private Long epRecruitBoard;
  private String savePath;
  private String thumbnailFileName;

}
