/**
 * Created by Elaphoil on 3/22/2018.
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class GraphServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //Retrieve the squares
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        System.out.println(start);
        System.out.println(end);

        //Algotithm called
        System.out.println("Initializing new KnightJaunt request");
        KnightJaunt jauntCalculate = new KnightJaunt(start, end);
        String solution = jauntCalculate.shortestJaunt();

        // Set the response message parameters
        response.setContentType("application/JSON");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(solution);
        out.flush();
        out.close();
    }

}
