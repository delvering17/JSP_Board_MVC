package nonView_p;

import board_p.BoardService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/nonView/*")
public class NonViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String service = request.getRequestURI().substring((request.getContextPath()+"/nonView/").length());
        try {

            NonViewService bs = (NonViewService) Class.forName("nonView_p." + service).newInstance();
            bs.execute(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
