package board_p;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import model_p.BoardDAO;
import model_p.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class BoardReplyReg implements BoardService {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        BoardDTO dto = new BoardDTO();
        dto.setGid(Integer.parseInt(request.getParameter("gid")));
        dto.setSeq(Integer.parseInt(request.getParameter("seq")));
        dto.setLevel(Integer.parseInt(request.getParameter("level")));
        dto.setTitle(request.getParameter("title"));
        dto.setPname(request.getParameter("pname"));
        dto.setPw(request.getParameter("pw"));
        dto.setContent(request.getParameter("content"));


        new BoardDAO().reply(dto);

//            System.out.println(dto);

        request.setAttribute("mainUrl", "board/alert.jsp");
        request.setAttribute("msg", "입력되었습니다.");
        request.setAttribute("goUrl", "BoardDetail?id="+dto.getId() + "&nowPage=" + request.getAttribute("nowPage"));

    }

}
