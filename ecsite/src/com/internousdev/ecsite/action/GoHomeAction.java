package com.internousdev.ecsite.action;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class GoHomeAction extends ActionSupport implements SessionAware{

	private Map<String,Object> session;
	//Map型の変数sessionを宣言。基本的には前のjspから受け取ったものを宣言。(ここでは前のjspがないのでこの宣言は実はいらない。あとから何か加えた際に便利。

	public String execute(){
		return SUCCESS;
	}
	//struts.xmlからの命令でこのクラスが動いたらまずexecuteメソッドを実行。無条件に成功。

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
