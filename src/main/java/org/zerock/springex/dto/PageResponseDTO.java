package org.zerock.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end =   (int)(Math.ceil(this.page / 10.0 )) *  10;
        // 올림

        this.start = this.end - 9;

        int last =  (int)(Math.ceil((total/(double)size)));
        // 마지막 페이지
        this.end =  end > last ? last: end;
        // 만약 데이터의수는 103 page는 11페이지를 보고 있다면 end, 즉 마지막 페이지는 20이다.
        // 그렇다면 우리는 존재하지도 않는 12~20페이지를 보여줘야하니 이 수식을 써서 마지막 페이지 묶음일 때는 최대 페이지로 end를 쓴다.
        
        this.prev = this.start > 1;

        this.next =  total > this.end * this.size;

    }

}