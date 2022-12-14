package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.MemberVo;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/mdelete.lo")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = new MemberService();
		
		String id = request.getParameter("id");
		
		int result = service.delete(id);
		if(result > 0) {
			//삭제가 정상이면 세션 만료로 데이터 제거
			HttpSession session = request.getSession(false);
			session.invalidate();
		} 
		//메인페이지로 이동
		response.sendRedirect(request.getContextPath()+"/WEB-INF/view/index.jsp");
		
	}


}
