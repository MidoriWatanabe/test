//--------------------------------
//	SearchPlayer.java
//--------------------------------
//　自分が格納されているフォルダ名
package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Player;
import control.PlayerManager;

	//HttpServletを継承することで、このクラスはServletとして、働くことができる
	public class SearchPlayer extends HttpServlet{

	/**
	*
	*/
		private static final long serialVersionUID = 1L;

	//  doGetメソッドは使わないので、doPostメソッドへ転送
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}

	//  requestオブジェクトには、フォームで入力された文字列などが格納されている。
	//  responseオブジェクトを使って、次のページを表示する
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{

		// requestオブジェクトの文字エンコーディングの設定
		request.setCharacterEncoding("UTF-8");

		// requestオブジェクトから登録情報の取り出し
		String player_name = request.getParameter("player_name");

		String player_birthday = null;

		// playerのオブジェクトに情報を格納
		Player player = new Player(player_name, player_birthday);

		//  PlayerManagerオブジェクトの生成
		PlayerManager manager = new PlayerManager();

		//  学生の検索
		player = manager.searchPlayer(player);
		//  requestオブジェクトにオブジェクトを登録
		request.setAttribute("Player", player);
		//  情報表示画面を表示する
		//  forwardはrequestオブジェクトを引数として、次のページに渡すことができる
		getServletContext().getRequestDispatcher("/jsp/showPlayer.jsp").forward(request, response);
		}
}