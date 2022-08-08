package board_p;

import model_p.BoardDAO;
import model_p.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardModifyForm implements BoardService{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("id"));

        BoardDTO dto = new BoardDAO().detail(id);

        request.setAttribute("mainUrl", "board/modifyForm.jsp");
        request.setAttribute("dto", dto);


    }
}
