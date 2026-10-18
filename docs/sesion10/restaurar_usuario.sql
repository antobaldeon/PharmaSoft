-- [Sesion 10 Autonoma | Restauracion] Reactivar el usuario al terminar, incluso si la prueba falla.
UPDATE usuarios SET estado = true WHERE LOWER(username) = 'almacen';
COMMIT;
SELECT username, estado FROM usuarios WHERE LOWER(username) = 'almacen';
