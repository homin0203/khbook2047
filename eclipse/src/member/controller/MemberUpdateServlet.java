package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.MemberVo;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/mupdate.lo")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션에 저장되어 있던 회원 정보 가져오기
		HttpSession session = request.getSession(false);
		MemberVo vo = (MemberVo)session.getAttribute("member");
		
		MemberService service = new MemberService();
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		PrintWriter out = response.getWriter();
		
		if(vo!=null && vo.getId().equals(id)) { // 기존 아이디와 일치하면 수정
			vo.setPasswd(passwd);
			vo.setName(name);
			vo.setEmail(email);
			if(service.update(vo) > 0) { // 업데이트가 정상
				session.setAttribute("member", vo);
				response.sendRedirect(request.getContextPath()+"/WEB-INF/view/index.jsp");
			} else { // 업데이트 비정상
				out.append("<script>alert('회원 정보 수정 오류!\n'+'관리자에게 문의하세요!')</script>");
			}
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/view/error/errorPage.jsp");
			request.setAttribute("msg", "회원 정보 수정 오류 발생");
			view.forward(request, response);
		}
	}

}
