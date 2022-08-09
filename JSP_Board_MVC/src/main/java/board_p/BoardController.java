package board_p;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {

    HashMap<String,String> nonService = new HashMap<>();

    public BoardController() {

        nonService.put("BoardInsertForm","board/insertForm.jsp");
        nonService.put("BoardDeleteForm","board/deleteForm.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int nowPage = 1;

        if(request.getParameter("nowPage") != null) {
            nowPage = Integer.parseInt(request.getParameter("nowPage"));
        }

        request.setAttribute("nowPage", nowPage);



        String service = request.getRequestURI().substring((request.getContextPath()+"/board/").length());

        try {

            if (nonService.containsKey(service)) {
                request.setAttribute("mainUrl", nonService.get(service));
            } else {
                BoardService bs = (BoardService) Class.forName("board_p." + service).newInstance();
                bs.execute(request, response);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/template.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
