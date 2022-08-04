package nonView_p;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLEncoder;

public class FileDown implements NonViewService{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String path = request.getRealPath("fff");
        path = "/Users/song-chanwook/workplace/git_repo/JSP_Board_MVC/JSP_Board_MVC/src/main/webapp/fff";

        String upfile = request.getParameter("upfile");

        try {
            FileInputStream fis = new FileInputStream(path + "/" + upfile);

            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(upfile, "UTF-8"));

            ServletOutputStream sos = response.getOutputStream();

            byte[] buf = new byte[1024];

            while(fis.available() > 0) {
                int len = fis.read(buf);
                sos.write(buf,0, len);
            }

            sos.close();
            fis.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
