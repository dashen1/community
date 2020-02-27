package life.yuanma.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean showPreviousPage;
    private boolean showNextPage;
    private boolean showLastPage;
    private boolean showFirstPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;
    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);
        for (int i =1 ;i <= 3; i++){
            if(page - i >0){
                pages.add( 0,page - i);
            }
            if (page + i <= totalPage){
                pages.add(page+i);
            }
        }

        if(page == 1){
            showPreviousPage = false;
        }else {
            showPreviousPage = true;
        }
        if(page == totalPage){
            showNextPage = false;
        }else {
            showNextPage = true;
        }

        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }

        if (pages.contains(totalPage)){
            showLastPage = false;
        }else {
            showLastPage = true;
        }
    }
}
