package board_p;

import model_p.BoardDAO;
import model_p.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class BoardDetail implements BoardService {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        BoardDAO dao = new BoardDAO();
        dao.addCnt(id);
        BoardDTO dto = dao.detail(id);

//		System.out.println(dto);

        int nowPage = Integer.parseInt(request.getParameter("nowPage"));

        request.setAttribute("nowPage",nowPage);
        request.setAttribute("mainUrl", "board/detail.jsp");
        request.setAttribute("dto", dto);


    }

}
