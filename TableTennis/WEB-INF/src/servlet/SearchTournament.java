//--------------------------------
//	SearchTournament.java
//--------------------------------
//　自分が格納されているフォルダ名
package servlet;

//自分が格納されているフォルダの外にある必要なクラス
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Tournament;
import control.TournamentManager;

	//HttpServletを継承することで、このクラスはServletとして、働くことができる
	public class SearchTournament extends HttpServlet{

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
		String tournament_name = request.getParameter("tournament_name");

		String start_date = null;
		String end_date = null;
		String tournament_place = null;

		// Tournamentのオブジェクトに情報を格納
		Tournament tournament = new Tournament( tournament_name, start_date,end_date,tournament_place);

		//  TournamentManagerオブジェクトの生成
		TournamentManager manager = new TournamentManager();

		//  学生の検索
		tournament = manager.searchTournament(tournament);
		//  requestオブジェクトにオブジェクトを登録
		request.setAttribute("Tournament", tournament);
		//  情報表示画面を表示する
		//  forwardはrequestオブジェクトを引数として、次のページに渡すことができる
		getServletContext().getRequestDispatcher("/jsp/showTournament.jsp").forward(request, response);
		}
}
