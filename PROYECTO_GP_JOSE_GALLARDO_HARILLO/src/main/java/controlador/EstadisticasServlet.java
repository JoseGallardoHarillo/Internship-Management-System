package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.EstadisticaDAO;
import modelo.Estadistica;

@WebServlet("/estadisticas")
public class EstadisticasServlet extends HttpServlet {

    // DAO privado
    private EstadisticaDAO estadisticaDAO = new EstadisticaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        try {
            // Usuarios por rol
            List<Estadistica> usuariosPorRol = estadisticaDAO.getUsuariosPorRol();
            req.setAttribute("usuariosPorRol", usuariosPorRol);

            // Alumnos por curso
            List<Estadistica> alumnosPorCurso = estadisticaDAO.getAlumnosPorCurso();
            req.setAttribute("alumnosPorCurso", alumnosPorCurso);

            // Promedio de evaluaciones por capacidad
            List<Estadistica> promedioEvaluacionesTutorC = estadisticaDAO.getPromedioEvaluacionesTutorC();
            req.setAttribute("promedioEvaluacionesTutorC", promedioEvaluacionesTutorC);

            List<Estadistica> promedioEvaluacionesTutorP = estadisticaDAO.getPromedioEvaluacionesTutorP();
            req.setAttribute("promedioEvaluacionesTutorP", promedioEvaluacionesTutorP);

            // Cantidad de evaluaciones por alumno (separadas por tipo)
            List<Estadistica> evaluacionesCurso = estadisticaDAO.getEvaluacionesTutorCursoPorAlumno();
            List<Estadistica> evaluacionesPracticas = estadisticaDAO.getEvaluacionesTutorPracticasPorAlumno();

            // Enviamos ambas listas al JSP
            req.setAttribute("evaluacionesCurso", evaluacionesCurso);
            req.setAttribute("evaluacionesPracticas", evaluacionesPracticas);

            // Distribución de alumnos por tutor
            List<Estadistica> alumnosPorTutorCurso = estadisticaDAO.getAlumnosPorTutorCurso();
            req.setAttribute("alumnosPorTutorCurso", alumnosPorTutorCurso);

            List<Estadistica> alumnosPorTutorPracticas = estadisticaDAO.getAlumnosPorTutorPracticas();
            req.setAttribute("alumnosPorTutorPracticas", alumnosPorTutorPracticas);

            // Forward al JSP
            req.getRequestDispatcher("/WEB-INF/vistas/estadisticas/estadistica.jsp")
               .forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Error al cargar estadísticas", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Redirige al GET
        resp.sendRedirect(req.getContextPath() + "/estadisticas");
    }
}