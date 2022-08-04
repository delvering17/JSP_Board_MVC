package nonView_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface NonViewService {

    void execute(HttpServletRequest request, HttpServletResponse response);
}
