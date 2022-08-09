package board_p;



import model_p.BoardDAO;
import model_p.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class BoardList implements BoardService{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        int nowPage = (int) request.getAttribute("nowPage");
        int limit = 5;
        int pageLimit = 4;
        int total = new BoardDAO().total();
        int totalPage = total / limit;
        int first = (nowPage - 1) * limit;
        int firstPage = (nowPage - 1) / pageLimit * pageLimit + 1;
        int endPage = firstPage + pageLimit - 1;

        if((total % limit) != 0) {
            totalPage ++;
        }

        if(endPage > totalPage) {
            endPage = totalPage;
        }

        ArrayList<BoardDTO> mainData = new BoardDAO().list(first, limit);

        request.setAttribute("first", first);
        request.setAttribute("firstPage", firstPage);
        request.setAttribute("endPage", endPage);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("mainData",mainData);
        request.setAttribute("mainUrl", "board/list.jsp");

    }
}
