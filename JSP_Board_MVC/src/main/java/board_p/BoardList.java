package board_p;



import model_p.BoardDAO;
import model_p.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class BoardList implements BoardService{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("BoardList 진입");

        ArrayList<BoardDTO> mainData = new BoardDAO().list();

        request.setAttribute("mainData",mainData);
        request.setAttribute("mainUrl", "board/list.jsp");

    }
}
