package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dao.LoginDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{

	private String loginUserId;
	private String loginPassword;
	private Map<String,Object> session;
	private LoginDAO loginDAO = new LoginDAO();
	private LoginDTO loginDTO = new LoginDTO();
	private BuyItemDAO buyItemDAO = new BuyItemDAO();
	//login.jspから受け取ったloginUserIDとloginPasswordとsessionを宣言。
	//BuyItemDAOでBuyItemDTOをインスタンス化しているためこのクラスではしなくてもいい。

	public String execute(){
	//struts.xmlの命令でexecuteメソッドを実行。
		String result = ERROR;
	//基本的にはエラーで返す。
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);
		//このクラスが(loginUserIdとloginPassword)をjspから受け取ったので、
		//その値がDBと一致しているか照合してくださいと命令し、loginDAOクラスのLoginUserInfoメソッドが動く。
		//そして持ってきた値をloginDTOに格納。
		session.put("loginUser", loginDTO);
		//ページをまたいでもKeyがわかれば値を取得できる。

//		Object o = session.get("loginUser");
//		LoginDTO dto = (LoginDTO) o;
//		boolean b = dto.getLoginFlg();
//
//		if((b)){
		if(((LoginDTO)session.get("loginUser")).getLoginFlg()){
			result = SUCCESS;
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();

			session.put("login_user_id", loginDTO.getLoginId());
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());
			//認証が成功した場合、次の画面に商品情報が必要なため商品情報を取得。
			return result;
		}
		return result;

	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}