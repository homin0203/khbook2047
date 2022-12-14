package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

/**
 * Servlet implementation class DupIdChkServlet
 */
@WebServlet("/dupid.lo")
public class DupIdChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DupIdChkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = new MemberService();
		String id = request.getParameter("id");
		int result = service.dupldChk(id);
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			//1이상이면 중복이니까 실패
			out.append("fail");
		} else {
			//0이하면 중복이 없으니 성공
			out.append("ok");
		}
		out.flush();
		out.close();
	}

}
