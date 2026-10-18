package pe.edu.upeu.PharmaBackend.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;

// [Sesion 10 Autonoma | Pruebas] Ejecuta el filtro y entry point reales sin modificar Oracle.
class JwtEscenariosSesionTest {
    private static final String SECRET = Base64.getEncoder().encodeToString("01234567890123456789012345678901".getBytes());
    private final UserDetails activo = User.withUsername("admin").password("no-utilizada").authorities("CATALOGO_LEER").build();
    private final JwtService jwt = new JwtService(SECRET, 60);
    @AfterEach void limpiar() { SecurityContextHolder.clearContext(); }
    private MockHttpServletResponse ejecutar(String encabezado, UserDetails usuario) throws Exception {
        UserDetailsService usuarios = nombre -> usuario;
        var filtro = new JwtAuthenticationFilter(jwt, usuarios);
        var request = new MockHttpServletRequest("GET", "/api/v1/productos");
        if(encabezado != null) request.addHeader("Authorization", encabezado);
        var response = new MockHttpServletResponse();
        filtro.doFilter(request, response, (req, res) -> {
            if(SecurityContextHolder.getContext().getAuthentication() == null) {
                new RestAuthenticationEntryPoint().commence(request, response, new InsufficientAuthenticationException("anonimo"));
            } else response.setStatus(200);
        });
        return response;
    }
    @Test void tokenValido() throws Exception {
        assertEquals(200, ejecutar("Bearer " + jwt.generarToken(activo), activo).getStatus());
    }
    @Test void tokenExpirado() throws Exception {
        var respuesta = ejecutar("Bearer " + new JwtService(SECRET, -1).generarToken(activo), activo);
        assertEquals(401, respuesta.getStatus());
        assertTrue(respuesta.getContentAsString().contains("El token ha expirado. Inicie sesión nuevamente"));
    }
    @Test void tokenAlterado() throws Exception {
        var partes = jwt.generarToken(activo).split("\\.");
        partes[1] = (partes[1].charAt(0) == 'A' ? "B" : "A") + partes[1].substring(1);
        var respuesta = ejecutar("Bearer " + String.join(".", partes), activo);
        assertEquals(401, respuesta.getStatus());
        assertTrue(respuesta.getContentAsString().contains("El token no es válido"));
    }
    @Test void sinCabecera() throws Exception {
        var respuesta = ejecutar(null, activo);
        assertEquals(401, respuesta.getStatus());
        assertTrue(respuesta.getContentAsString().contains("Debe iniciar sesión para acceder a este recurso"));
    }
    @Test void prefijoIncorrecto() throws Exception {
        var respuesta = ejecutar("bearer " + jwt.generarToken(activo), activo);
        assertEquals(401, respuesta.getStatus());
        assertTrue(respuesta.getContentAsString().contains("Debe iniciar sesión para acceder a este recurso"));
    }
    @Test void usuarioDeshabilitadoConTokenTodaviaVigente() throws Exception {
        var deshabilitado = User.withUserDetails(activo).disabled(true).build();
        var respuesta = ejecutar("Bearer " + jwt.generarToken(activo), deshabilitado);
        assertEquals(401, respuesta.getStatus());
        assertTrue(respuesta.getContentAsString().contains("El usuario está deshabilitado"));
    }
}
