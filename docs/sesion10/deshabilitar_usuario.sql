-- [Sesion 10 Autonoma | Escenario 6] Ejecutar SOLO en la base local de desarrollo con sesion abierta de almacen.
-- Despues de confirmar el cambio, pulsar Usuario deshabilitado en PharmaMobil debug.
UPDATE usuarios SET estado = false WHERE LOWER(username) = 'almacen';
COMMIT;
SELECT username, estado FROM usuarios WHERE LOWER(username) = 'almacen';
