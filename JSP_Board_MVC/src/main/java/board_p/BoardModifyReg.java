package board_p;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model_p.BoardDAO;
import model_p.BoardDTO;

public class BoardModifyReg implements BoardService {

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


            int nowPage = Integer.parseInt(mr.getParameter("nowPage"));
            request.setAttribute("nowPage", nowPage);

            BoardDTO dto = new BoardDTO();
            dto.setId(Integer.parseInt(mr.getParameter("id")));
            dto.setTitle(mr.getParameter("title"));
            dto.setPname(mr.getParameter("pname"));
            dto.setPw(mr.getParameter("pw"));
            dto.setContent(mr.getParameter("content"));
            if(mr.getParameter("upfile")!=null) {
                dto.setUpfile(mr.getParameter("upfile"));
            }else {
                dto.setUpfile(mr.getFilesystemName("upfile"));
            }

            int cnt = new BoardDAO().modify(dto);

            String msg = "수정 실패";
            String mainUrl = "board/modifyForm.jsp";
            if(cnt>0) {

                msg = "수정 성공";
                mainUrl = "board/alert.jsp";
                request.setAttribute("goUrl", "BoardDetail?id="+dto.getId()+"&nowPage="+nowPage);
            }

            System.out.println(dto);

            request.setAttribute("mainUrl", mainUrl);
            request.setAttribute("msg", msg);
            request.setAttribute("dto", dto);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}