package Nomination.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import Nomination.model.LoginUser;
import Nomination.model.Trainee;
//import com.mysql.jdbc.Connection; にするか悩む



public class GetNominationListDAO {
	private static final String LOGINUSER_SELECT ="select * from users where id = ?";
	private static final String TRAINEE_ALL_SELECT ="select * from trainee";
	private static final String COMPANY_SELECT ="select * from company where id = ?";
	private static final String STAR_SELECT ="select * from star where id = ?";
	private static final String TRAINEE_REPLIES_SELECT ="select replies from trainee where id = ?";
	private static final String PICTURE_ALL_SELECT ="select * from picture";
	private static final String TH_UPDATE = "update users set th=? where id=?";
	
	private DataSource source;
	
	public GetNominationListDAO(){
		source = DaoUtil.getSource();
	}
	/* 画像アドレス取得 */
	public ArrayList<String> getPictureAddress() throws SQLException {
		Connection con = source.getConnection();
		PreparedStatement pStmt = con.prepareStatement(PICTURE_ALL_SELECT);
		ResultSet rs = null;
		 
		try{
			rs = pStmt.executeQuery();
			return getPicture(rs);
		}catch(SQLException e){
			
		}finally{
			if(rs!=null){
				rs.close();
			}
			pStmt.close();
			con.close();
		}
		return null;
	}
	/* ログインユーザ取得 */
	public LoginUser getLoingUser(String id) throws SQLException {
		Connection con = source.getConnection();
		PreparedStatement pStmt = con.prepareStatement(LOGINUSER_SELECT);
		ResultSet rs = null;

		try{
			pStmt.setString(1, id);
			rs = pStmt.executeQuery();
			while(rs.next()){
				return getLoginUser(rs);
			}
		}catch(SQLException e){
			
		}finally{
			if(rs!=null){
				rs.close();
			}
			pStmt.close();
			con.close();
		}
		return null;
	}
	/* 回数更新 */
	public boolean updateTh(int th, String id) throws SQLException{
		Connection con = source.getConnection();
		PreparedStatement pStmt = con.prepareStatement(TH_UPDATE);
		ResultSet rs = null;
		try{
			con.setAutoCommit(false);
			pStmt.setInt(1, th);
			pStmt.setString(2, id);
			pStmt.executeUpdate();
			con.commit();
			
		}catch(SQLException e){
			con.rollback();
			return false;
		}finally{
			if(rs!=null){
				rs.close();
			}
			pStmt.close();
			con.close();
		}
		return true;
	}
	/* 研修生情報取得 */
	public ArrayList<Trainee> getTrainee() throws SQLException {
		Connection con = source.getConnection();
		PreparedStatement pStmt = con.prepareStatement(TRAINEE_ALL_SELECT);
		ResultSet rs = null;

		try{
			rs = pStmt.executeQuery();
			return getTraineeList(rs);
		}catch(SQLException e){
			
		}finally{
			if(rs!=null){
				rs.close();
			}
			pStmt.close();
			con.close();
		}
		return null;
	}
	/* 社名取得 */
	public String getCompany(int cpId) throws SQLException {
		Connection con = source.getConnection();
		PreparedStatement pStmt = con.prepareStatement(COMPANY_SELECT);
		ResultSet rs = null;

		try{
			pStmt.setInt(1, cpId);
			rs = pStmt.executeQuery();
			while(rs.next()){
				return rs.getString("name");
			}
		}catch(SQLException e){
			
		}finally{
			if(rs!=null){
				rs.close();
			}
			pStmt.close();
			con.close();
		}
		return null;
	}
	/* ランク取得 */
	public int getRank(int id) throws SQLException {
		Connection con = source.getConnection();
		PreparedStatement pStmt = con.prepareStatement(STAR_SELECT);
		ResultSet rs = null;

		try{
			pStmt.setInt(1, id);
			rs = pStmt.executeQuery();
			while(rs.next()){
				return rs.getInt("rank");
			}
		}catch(SQLException e){
			
		}finally{
			if(rs!=null){
				rs.close();
			}
			pStmt.close();
			con.close();
		}
		return 0;
	}
	/* 回答数取得 */
	public int getReplies(int id) throws SQLException {
		Connection con = source.getConnection();
		PreparedStatement pStmt = con.prepareStatement(TRAINEE_REPLIES_SELECT);
		ResultSet rs = null;

		try{
			pStmt.setInt(1, id);
			rs = pStmt.executeQuery();
			while(rs.next()){
				return rs.getInt("rank");
			}
		}catch(SQLException e){
			
		}finally{
			if(rs!=null){
				rs.close();
			}
			pStmt.close();
			con.close();
		}
		return 0;
	}
	private ArrayList<Trainee> getTraineeList(ResultSet rs) throws SQLException{
		ArrayList<Trainee> lTrainee = new ArrayList<Trainee>();
		
		while(rs.next()){
			// 戻り値格納
			Trainee trainee = new Trainee();
			trainee.setId(rs.getInt("id"));
			trainee.setName(rs.getString("name"));
			trainee.setAge(rs.getInt("age"));
			trainee.setCompany(getCompany(rs.getInt("company_id")));
			//trainee.setRank(blackStar(getRank(rs.getInt("id")),10));
			trainee.setRank(blackStar(getRank(rs.getInt("id")),10));
			lTrainee.add(trainee);
		}
		return lTrainee;
	}
	private LoginUser getLoginUser(ResultSet rs) throws SQLException{
		LoginUser lu = new LoginUser();
		// 戻り値格納
		lu.setId(rs.getString("id"));
		lu.setName(rs.getString("name"));
		lu.setPassword(rs.getString("password"));
		lu.setTh(rs.getInt("th"));
		
		return lu;
	}
	private ArrayList<String> getPicture(ResultSet rs) throws SQLException{
		ArrayList<String> address = new ArrayList<String>();
		// 戻り値格納
		while(rs.next()){
			address.add(rs.getString("address"));
		}
		return address;
	}
	/**
	 * 指定文字埋め.
	 * <p>
	 * 対象文字列が指定文字、バイト数未満の場合、対象文字列の右端から置き換え文字で埋める。
	 * </p>
	 * @param String  src     対象文字列
	 * @param boolean flg     文字、バイトフラグ(true:文字  false:バイト)
	 * @param int     size    指定文字、バイト数
	 * @param String  repChar 置き換え文字
	 * @return 整形結果
	*/
	public static String rightPad(String src, boolean flg, int size, String repChar) {
		// 挿入する置き換え文字、バイト数を計算
		int pads = 0;
		if(flg){
			// flg=文字の場合
			pads = size - src.length();
		}else{
			// flg=バイトの場合
			pads = size - src.getBytes().length;
		}
		// 指定文字数を超える場合（マイナスの場合）は処理を抜ける。
		if(pads <= 0){
			return src;
		}
		
		// 置き換え文の挿入
		StringBuffer sb = new StringBuffer(size);
		sb.append(src);
		for(int i = 0; i < pads; i++) {
			sb.append(repChar);
		}
		return sb.toString();
	}
	/**
	 * 黒星追加.
	 * <p>
	 * 引き渡されたランクから黒星を追加する。
	 * </p>
	 * @param String  star ランク
	 * @param String  star 最大数
	 * @return 星表示のランク
	*/
	public static String blackStar(int star, int max){
		String result = "";
		String blStar = "";

		blStar = rightPad(blStar, true, star, "★");
		result = rightPad(blStar, true, max, "☆");

		return result;
	}
}
