package controlador;

import dao.UsuarioDAO;
import modelo.Usuario;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	//Servicios DAO
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html; charset=UTF-8");

        // Lectura de la acción de la URL (ej: list, edit, delete)
        String action = req.getParameter("action");
        
        if (action == null) action = "login"; 
        
try {
            
        	switch (action) {
            
            case "login":
		        req.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(req, resp);
				break;

            case "logout":
                    
            	HttpSession session = req.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                
                // Redirigir al index.jsp después de cerrar sesión
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            Usuario usuario = usuarioDAO.getUsuarioByEmail(email);

            if (usuario != null && usuario.isActivo() && usuario.getPassword().equals(password)) {
                // Inicio de sesión correcto
                
            	HttpSession session = req.getSession();
                session.setAttribute("usuarioLogueado", usuario);

                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                
            } else {
                req.setAttribute("error", "Credenciales incorrectas o usuario inactivo.");
                req.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}