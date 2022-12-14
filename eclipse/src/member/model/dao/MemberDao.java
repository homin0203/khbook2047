package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import common.jdbc.JdbcTemplate;
import member.model.vo.MemberVo;

public class MemberDao {
	//사용자 로그인 - 매개변수로 받은 값으로 db접속 데이터 조회
	public MemberVo loginMember(Connection conn,String id,String passwd) {
		MemberVo vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from TEST_MEMBER where id=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,passwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new MemberVo();
				vo.setId(rs.getString("id"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setEmail(rs.getString("email"));
				vo.setName(rs.getString("name"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(rs);
			JdbcTemplate.close(pstmt);
		}
		
		return vo;
	}
	//아이디 중복체크
		public int dupldChk(Connection conn,String id) {
			int result = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				//id로 행 개수를조회하여 있으면 1이상, 없으면 0
				String sql = "select count(*) from TEST_MEMBER where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1); // 첫 컬럼의 숫자값을 가져오기
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				JdbcTemplate.close(rs);
				JdbcTemplate.close(pstmt);
			}
			
			return result;
		}
	// DataBase에 Member 객체를 추가하는 메소드
	public int insert(Connection conn,MemberVo vo) {
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into test_member values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			result = pstmt.executeUpdate(); //실행결과를 반영된 행의 개수 리턴
											//1이상 성공, 0이하 실패
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		
		return result;
	}
	//기존 사용자 삭제
	public int delete(Connection conn,String id) {
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from test_member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		return result;
	}
	//기존 사용자의 정보를 수정
	public int update(Connection conn,MemberVo vo) {
		int result = 0;
		PreparedStatement pstmt = null;
		try {
			String sql = "update test_member set"+"passwd=?,name=?,email=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPasswd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTemplate.close(pstmt);
		}
		
		return result;
	}
//	public List<MemberVo> selectAll(Connection conn) {
//		List<MemberVo> volist = null;
//		
//		return volist;
//	}
//	public MemberVo selectOne(Connection conn,String id) {
//		MemberVo vo = null;
//		
//		return vo;
//	}
}
