package member.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import common.jdbc.JdbcTemplate;
import member.model.dao.MemberDao;
import member.model.vo.MemberVo;

public class MemberService {
	private MemberDao dao = new MemberDao();

	//사용자 로그인 - 매개변수로 받은 값으로 db접속 데이터 조회
		public MemberVo loginMember(String id,String passwd) {
			MemberVo vo = null;
			Connection conn = JdbcTemplate.getConnection();
			vo = dao.loginMember(conn, id, passwd);
			
			return vo;
		}
		//아이디 중복체크
			public int dupldChk(String id) {
				int result = 0;
				Connection conn = JdbcTemplate.getConnection();
				result = dao.dupldChk(conn, id);
				
				return result;
			}
		// DataBase에 Member 객체를 추가하는 메소드
		public int insert(MemberVo vo) {
			int result = 0;
			Connection conn = JdbcTemplate.getConnection();
			result = dao.insert(conn, vo);
			
			return result;
		}
		//기존 사용자 삭제
		public int delete(String id) {
			int result = 0;
			Connection conn = JdbcTemplate.getConnection();
			result = dao.delete(conn, id);
			return result;
		}
		//기존 사용자의 정보를 수정
		public int update(MemberVo vo) {
			int result = 0;
			Connection conn = JdbcTemplate.getConnection();
			result = dao.update(conn, vo);
			
			return result;
		}
}
