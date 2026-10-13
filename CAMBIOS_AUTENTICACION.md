# Cambios incorporados desde PharmaSoft del docente

Fuente: https://github.com/dreyna/pharmaSoft/tree/master

Se conserva la configuracion local de conexion a Oracle.
No se modifica V1; Flyway aplicara V2 y V3 al iniciar el backend.

- Modificado: `pom.xml`
- Modificado: `src/main/resources/application-prod.yaml`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/config/OpenApiConfig.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/config/SecurityConfig.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/controller/AuthController.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/controller/UsuarioController.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/entity/Empleado.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/entity/Rol.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/entity/Usuario.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/enums/Permiso.java`
- Modificado: `src/main/java/pe/edu/upeu/PharmaBackend/exception/GlobalExceptionHandler.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/mapper/UsuarioMapper.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/repository/EmpleadoRepository.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/repository/RolRepository.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/repository/UsuarioRepository.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/security/JwtAuthenticationFilter.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/security/JwtService.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/security/PermisosPorRol.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/security/RespuestaErrorSeguridad.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/security/RestAccessDeniedHandler.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/security/RestAuthenticationEntryPoint.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/security/UsuarioDetailsService.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/dto/auth/EmpleadoResponseDTO.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/dto/auth/LoginRequestDTO.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/dto/auth/LoginResponseDTO.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/dto/auth/RolResponseDTO.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/dto/auth/UsuarioRequestDTO.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/dto/auth/UsuarioResponseDTO.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/service/impl/AuthServiceImpl.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/service/impl/UsuarioServiceImpl.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/service/service/AuthService.java`
- Agregado: `src/main/java/pe/edu/upeu/PharmaBackend/service/service/UsuarioService.java`
- Agregado: `src/main/resources/db/migration/V2__seguridad_empleados_usuarios.sql`
- Agregado: `src/main/resources/db/migration/V3__datos_iniciales_seguridad.sql`
- Modificado: `src/main/resources/application-dev.yaml (conservando conexion Oracle local)`

## Verificacion
- Compilacion de 80 clases con Java 21: correcta.
- Pruebas: 20 correctas; 1 error al cargar el contexto por Oracle no disponible en localhost:1522 (ORA-12541).
- Login y migraciones pendientes de verificar con Oracle en ejecucion.

