package board_p;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import model_p.BoardDAO;
import model_p.BoardDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class BoardDeleteReg implements BoardService {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getRealPath("/fff");
        path = "/Users/song-chanwook/workplace/git_repo/JSP_practice/mvcProj/src/main/webapp/fff";

        BoardDTO dto = new BoardDTO();

        dto.setId(Integer.parseInt(request.getParameter("id")));
        dto.setPw(request.getParameter("pw"));


        String msg = "암호가 일치하지 않습니다.";
        String goUrl = "BoardDeleteForm?id="+dto.getId();

        BoardDTO fileDto = new BoardDAO().detail(dto.getId());

        int cnt = new BoardDAO().delete(dto);

        if(cnt>0) {
            msg = "삭제 되었습니다.";
            goUrl = "BoardList";

            if(fileDto.getUpfile() != null) {
                new File(path+"/"+fileDto.getUpfile()).delete();

            }
        }

        request.setAttribute("mainUrl", "board/alert.jsp");
        request.setAttribute("msg", msg);
        request.setAttribute("goUrl", goUrl);
    }

}