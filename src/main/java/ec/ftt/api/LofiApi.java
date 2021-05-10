package ec.ftt.api;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ec.ftt.dao.LofiDao;
import ec.ftt.model.Lofi;

/**
 * Servlet implementation class LofiApi
 * 
 * CRUD - 
 * 
 */



@WebServlet("/Lofi")
public class LofiApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LofiApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		setAccessControlHeaders(response);
			    	
	    LofiDao LofiDao = new LofiDao();
	    	
	    List<Lofi> Lofis = LofiDao.getAllLofi();
	        
	    Gson gson = new Gson();

	    response.getWriter().append(gson.toJson(Lofis));
	    /*
	    for (Lofi u : Lofis)
	     
	    response.getWriter().append(u.toString());
	    	*/
	    } //if
		
		 //doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setAccessControlHeaders(response);
		Gson gson = new Gson();
		

        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        
        Lofi resp = gson.fromJson(json, Lofi.class);
        Lofi a = new Lofi();


        a.setId(resp.getId());
        a.setLink(resp.getLink());
        a.setName(resp.getName());
        a.setMood(resp.getMood());
        a.setDimension(resp.isDimension());
        

        LofiDao lofiDao = new LofiDao();
        System.out.println(a);
        LofiDao.addLofi(a);

        response.getWriter().append(a.toString());
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Ajustar errors com try catch
		setAccessControlHeaders(response);
		response.setContentType("application/json"); //mimeType - https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
		Lofi u = new Lofi(request.getParameter("id"));
		LofiDao LofiDao = new LofiDao();
		
		LofiDao.updateLofi(u);
		
		System.out.println(u);
		
		response.getWriter().append(u.toString());
		
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// https://www.tutorialspoint.com/servlets/servlets-http-status-codes.htm
		
		// TODO Verificar se estÃ¡ enviando o parametro
		// TODO Verificar se o parametro Ã© null
		// TODO Se o ID jÃ¡ foi apagado
		// TODO Verificar se o ID nÃ£o existe...
		// TODO Usar try cath para propagar erro appropriadamente...
		// TODO à¤•à¥�à¤¯à¤¾ à¤†à¤ª à¤‡à¤¸ à¤•à¥‹à¤¡ à¤•à¥‹ à¤…à¤ªà¤¨à¥‡ à¤œà¥€à¤µà¤¨ à¤•à¥€ à¤®à¤¹à¤¿à¤²à¤¾ à¤•à¥‹ à¤¦à¤¿à¤–à¤¾à¤¨à¥‡ à¤•à¥€ à¤¹à¤¿à¤®à¥�à¤®à¤¤ à¤•à¤°à¥‡à¤‚à¤—à¥‡ ???
		// TODO à°®à±€ à°œà±€à°µà°¿à°¤à°‚à°²à±‹à°¨à°¿ à°¸à±�à°¤à±�à°°à±€à°•à°¿ à°ˆ à°•à±‹à°¡à±� à°šà±‚à°ªà°¿à°‚à°šà°¡à°¾à°¨à°¿à°•à°¿ à°®à±€à°•à±� à°§à±ˆà°°à±�à°¯à°‚ à°‰à°‚à°¦à°¾ ???
		
		
		// Reference: https://www.tutorialspoint.com/servlets/servlets-http-status-codes.htm
		// 
		setAccessControlHeaders(response);
		response.setStatus(418); //200 - OK - PadrÃ£o (Default)

		if (request.getParameter("id") == null)
			 response.sendError(407, "Informe o ID do usuÃ¡rio a ser retornado!!!" );
		else {
		int LofiId = Integer.valueOf(request.getParameter("id"));
		
		
		
		LofiDao ud = new LofiDao();
		
		ud.deleteLofi(LofiId);
		
		response.getWriter().append(request.getParameter("id") + " Lofi removido");
		}
	}
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

   private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
        resp.setHeader("Access-Control-Allow-Methods", "PUT");
        resp.setHeader("Access-Control-Allow-Methods", "DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

}
