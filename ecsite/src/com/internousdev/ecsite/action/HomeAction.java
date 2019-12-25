package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	//Map型の変数sessionを宣言。

	public String execute(){
		String result = "login";
		if(session.containsKey("login_user_id")){
			BuyItemDAO buyItemDAO = new BuyItemDAO();
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			session.put("id",buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());
				result = SUCCESS;
		}
		return result;
	}
	//String result="loginでstrutsで定義した"login"にいくが、もしsessionの中に"login_user_id"というKeyを持っていればresult=SUCCESSになる。
	//ホーム画面から直接商品一覧画面に飛んだ場合、LoginActionを通らず、本来LoginActionで取得する値を取得できないことになってしまうため、if文の中でsession.putを使い取得しておく。
	public Map<String, Object> getSession() {
		return this.session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	//Map型の変数sessionを宣言しているため、対応するgetter/setterを生成。
		//SessionAwareインターフェースをここで自由に書いているため(上書き)、@Overrideと注意書きしておくとコンパイラーにわかりやすい。
}
