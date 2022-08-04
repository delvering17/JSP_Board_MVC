package board_p;

import model_p.BoardDAO;
import model_p.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardDetail implements BoardService{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("id"));

        BoardDAO dao = new BoardDAO();
        dao.addCnt(id);
        BoardDTO dto = dao.detail(id);

        request.setAttribute("dto",dto);
        request.setAttribute("mainUrl","board/detail.jsp");
    }
}
