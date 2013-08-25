package Nomination.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import Nomination.dao.GetNominationListDAO;
import Nomination.form.TopForm;
import Nomination.model.LoginUser;
import Nomination.model.Trainee;

public final class TopAction extends Action {
	public ActionForward execute(ActionMapping mapping,	ActionForm form, HttpServletRequest request,
								HttpServletResponse response) throws Exception{
		TopForm topForm = (TopForm)form;
		
		//RANK算定基準
		//対象研修生の指名回数 / 現在の指名回数 = RANK値（今までの指名率）
		
		// セッションを取得
		HttpSession session = request.getSession();
		// ログインユーザ取得
		LoginUser lu  = (LoginUser)session.getAttribute("loginUser");
		
		// 研修生情報取得
		ArrayList<Trainee> tr = (ArrayList<Trainee>)session.getAttribute("userslist"); 
		
		// 研修生シャッフル
		Collections.shuffle(tr); 
		try{
			// DAO生成 
			GetNominationListDAO dao = new GetNominationListDAO();
			// 指名回数更新
			boolean thUpdateflg = dao.updateTh((lu.getTh()+1), lu.getId());
			// ログインユーザ取得
			LoginUser luResult = dao.getLoingUser(lu.getId());
			
			// rank算出
			int[] chkBox = topForm.getcbox();
			if(chkBox != null){
				int cnt = chkBox.length;
				
				for(int i=0;i<tr.size();i++){
					int chk = tr.get(i).getId();
					int replies = dao.getReplies(chk);
					
					for(int ix=0;ix<cnt;ix++){
						//if(chkBox[ix].equals(String.valueOf(chk))){
						if(chkBox[ix] == chk){
							Trainee trMod = tr.get(i);
							trMod.setRank(dao.blackStar((replies / lu.getTh()), 10));
							tr.set(i, trMod);
						}
					}
				}
			}
			
			// ログインユーザ取得
			ArrayList<String> picAddress  = (ArrayList<String>)session.getAttribute("pictureAd");
			Collections.shuffle(picAddress);
			String[] pic = new String[3];
			int i=0;
			
			for(String wk : picAddress){
				if(i<3){
					pic[i] = wk;
					i++;
				}else{
					break;
				}
			}
			// 画像アドレスをsessionスコープで保存
			session.setAttribute("picture", pic);
			session.setAttribute("pictureAd", picAddress);
			// 更新したログインユーザをsessionスコープで保存
			session.setAttribute("loginUser", luResult);
			// 更新した研修生情報をsessionスコープで保存
			session.setAttribute("userslist", tr);
		}catch(SQLException e){
			System.out.println(e.getMessage());
			throw e;
		}
		return (mapping.findForward("success"));
	}
}
