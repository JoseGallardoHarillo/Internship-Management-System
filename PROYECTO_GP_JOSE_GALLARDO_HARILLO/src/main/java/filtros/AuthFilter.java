package filtros;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);

        // 🟢 Recursos públicos
        boolean esPublico =
                uri.endsWith("index.html") ||
                uri.endsWith("/") ||                // Raíz del proyecto
                uri.endsWith("login") ||
                uri.endsWith("login.jsp") ||
                uri.contains("css") ||
                uri.contains("js") ||
                uri.contains("images") ||
                uri.contains("png") ||
                uri.contains("jpg");

        // 🔐 Usuario autenticado
        boolean autenticado = (session != null && session.getAttribute("usuarioLogueado") != null);

        if (esPublico) {
            // 🚪 Acceso libre
            chain.doFilter(request, response);
            return;
        }

        if (autenticado) {
            // ✅ Usuario logueado: permitir acceso
            chain.doFilter(request, response);
            System.out.println("✅ Usuario autenticado: " + uri);
        } else {
            // 🚫 No autenticado: solo redirige al login SI intenta acceder a algo privado
            if (!uri.endsWith("index.html") && !uri.endsWith("/")) {
                System.out.println("🚫 No autenticado. Redirigiendo a /login");
                resp.sendRedirect(req.getContextPath() + "/login");
            } else {
                // 👀 Si intenta acceder directamente a raíz sin sesión → mostrar index
                resp.sendRedirect(req.getContextPath() + "/index.html");
            }
        }
    }
}