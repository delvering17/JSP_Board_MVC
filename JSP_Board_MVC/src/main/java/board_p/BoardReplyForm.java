package board_p;

import model_p.BoardDAO;
import model_p.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardReplyForm implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);


		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.detail(id);


		request.setAttribute("mainUrl", "board/replyForm.jsp");
		request.setAttribute("dto", dto);
	}

}
