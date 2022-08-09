package board_p;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model_p.BoardDAO;
import model_p.BoardDTO;

public class FileDelete implements BoardService {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getRealPath("/fff");
        path = "/Users/song-chanwook/workplace/git_repo/JSP_practice/mvcProj/src/main/webapp/fff";

        int size = 1024*1024 *10;
        try {
            MultipartRequest mr = new MultipartRequest(
                    request,
                    path,
                    size,
                    "UTF-8",
                    new DefaultFileRenamePolicy());



            BoardDTO dto = new BoardDTO();
            dto.setId(Integer.parseInt(mr.getParameter("id")));
            dto.setTitle(mr.getParameter("title"));
            dto.setPname(mr.getParameter("pname"));
            dto.setPw(mr.getParameter("pw"));
            dto.setContent(mr.getParameter("content"));
            dto.setUpfile(mr.getParameter("upfile"));

            int cnt = new BoardDAO().fileDelete(dto);

            String msg = "파일 삭제 실패";
            if(cnt>0) {
                new File(path+"/"+dto.getUpfile()).delete();
                msg = "파일 삭제 성공";
                dto.setUpfile(null);
            }

            System.out.println(dto);

            int nowPage = Integer.parseInt(mr.getParameter("nowPage"));

            request.setAttribute("nowPage", nowPage);

            request.setAttribute("mainUrl", "board/modifyForm.jsp");
            request.setAttribute("msg", msg);
            request.setAttribute("dto", dto);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
