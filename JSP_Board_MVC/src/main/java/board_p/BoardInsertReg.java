package board_p;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import model_p.BoardDAO;
import model_p.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardInsertReg implements BoardService{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getRealPath("/fff");
        path = "/Users/song-chanwook/workplace/git_repo/JSP_Board_MVC/JSP_Board_MVC/src/main/webapp/fff";

        int size = 1024 * 1024 * 10;

        try {
            MultipartRequest mr = new MultipartRequest(
                    request,
                    path,
                    size,
                    "UTF-8",
                    new DefaultFileRenamePolicy()
            );

            BoardDTO dto = new BoardDTO();
            dto.setTitle(mr.getParameter("title"));
            dto.setPname(mr.getParameter("pname"));
            dto.setPw(mr.getParameter("pw"));
            dto.setUpfile(mr.getFilesystemName("upfile"));
            dto.setContent(mr.getParameter("content"));

            new BoardDAO().insert(dto);


            request.setAttribute("mainUrl", "board/alert.jsp");
            request.setAttribute("msg", "입력되었습니다.");
            request.setAttribute("goUrl", "BoardDetail?id="+dto.getId());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
