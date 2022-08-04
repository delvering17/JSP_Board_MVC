package board_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardList implements BoardService{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("BoardList 진입");
    }
}
