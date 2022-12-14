package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import member.model.service.MemberService;
import member.model.vo.MemberVo;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자의 정보를 json 형식으로 전달 위한 contextType변경
		response.setContentType("application/json;charset=UTF-8");
		MemberService mservice = new MemberService();
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		JsonObject job = new JsonObject();
		// 입력받은 사용자의 ID와 비밀번호를 인자로 하여 Service의 loginMember() 호출
		 MemberVo vo = mservice.loginMember(id, passwd);
		 if(vo != null) {
		 HttpSession session = request.getSession();
		 session.setAttribute("member", vo);
		 job.addProperty("result", "ok");
		 job.addProperty("name", vo.getName());
		 } else {
		job.addProperty("result", "fail");
		}
		 PrintWriter out = response.getWriter();
		 out.println(job.toString());
		 out.flush();
		 out.close();
		 //gson을 등록해서 메소드가 다른거 같음.
	}

}
