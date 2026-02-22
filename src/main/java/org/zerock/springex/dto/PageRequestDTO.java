package org.zerock.springex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import java.time.LocalDate;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default // 빌더 패턴으로 객체가 생성될 때 값이 지정되지 않으면 설정된 기본 값으로 값을 넣는다.
    @Min(value = 1)
    @Positive // 양수
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    private String link;

    public int getSkip(){

        return (page -1) * 10; // 만약 사용자가 2페이지를 보고 싶다면 10개를 건너뛴다.
    }
    public String getLink() {
      if(link == null){
          StringBuilder builder = new StringBuilder();

          builder.append("page=" + this.page);

          builder.append("&size=" + this.size);
          link = builder.toString();
      }
      return link;
  }


}