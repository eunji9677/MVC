package sec01.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberController
 */
//@WebServlet("/mem.do")
@WebServlet("/member.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     MemberDAO dao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		dao = new MemberDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		List<MemberVO> membersList = dao.listMembers();
//		request.setAttribute("membersList", membersList);
//		RequestDispatcher dispatch = request.getRequestDispatcher("/test01/listMember.jsp");
//		dispatch.forward(request, response);
		String nextPage =null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action : "+ action);
		
		if(action==null||action.equals("/listMember.do"))
		{
			List<MemberVO> membersList = dao.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage="/test01/listMember.jsp";
		}else if(action.equals("addMember.do"))
		{
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO vo = new MemberVO(id,pwd, name, email);
			dao.addMember(vo);
			
			nextPage="/member/listMember.do";
		}else if(action.equals("/MemberForm.do"))
		{
			System.out.println("dididididi");
			nextPage="/test01/MemberForm.jsp";
		}else
		{
			List<MemberVO>membersList = dao.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test01/listMember.jsp";
		}
	
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);

	}
}
