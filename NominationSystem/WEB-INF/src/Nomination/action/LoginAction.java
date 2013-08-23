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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import Nomination.dao.GetNominationListDAO;
import Nomination.form.LoginForm;
import Nomination.model.LoginUser;
import Nomination.model.Trainee;

public final class LoginAction extends Action {

   public ActionForward execute(ActionMapping mapping, ActionForm form,	 HttpServletRequest request,
		   						HttpServletResponse response) throws Exception {
		// フォームBeanから画面よりログインIDとパスワードを取得する
		LoginForm loginForm = (LoginForm)form;
		
		//セッションを取得
		HttpSession session = request.getSession();
		
		String status = null;
		ArrayList<Trainee> usersList = null;
		ActionMessages errors = new ActionMessages();
		
		// LoginFormフォームより値取得
		String id = loginForm.getId();
		String password = loginForm.getPassword();
		 
		try {
			// DAO生成 
			GetNominationListDAO dao = new GetNominationListDAO();
			//ログインユーザ取得
			LoginUser lu = dao.getLoingUser(id);
	
			// ログインチェック
			if(lu != null){
				// ログインユーザが存在する場合、ID、パスワードチェック
				if (! id.equals(lu.getId()) || ! password.equals(lu.getPassword())) {
					// IDまたはパスワードが異なる場合はエラー
					errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.login"));
				}
			}else{
				// ログインユーザが存在しない場合、エラー
				errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.login"));
			}
			
			// 認証に成功した場合、トップ画面に登録者の一覧を表示する
			if (errors.isEmpty()) {
				// 指名回数更新
				lu.setTh(lu.getTh()+1);
				boolean thUpdateflg = dao.updateTh(lu.getTh(), id);
				
				// 研修生情報取得
				usersList = new ArrayList<Trainee>();
				usersList = dao.getTrainee();
				// 研修生情報のシャッフル
				Collections.shuffle(usersList); 
				
				ArrayList<String> picAddress = dao.getPictureAddress();
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
				
				// ログインユーザをsessionスコープで保存
				session.setAttribute("loginUser", lu);
				// 研修生情報をsessionスコープで保存
				session.setAttribute("userslist", usersList);
				// 画像アドレスをsessionスコープで保存
				session.setAttribute("picture", pic);
				session.setAttribute("pictureAd", picAddress);
				status = "success";
			// 認証に失敗した場合、ログイン画面にエラーメッセージを表示する。
			} else {
				status = "error";
				saveErrors(request, errors);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
			throw e;
		}
		// 処理結果により画面遷移
		return (mapping.findForward(status));
   }
}