package logistica.modelo;

/**
 * Contrato para vehículos con capacidades de comunicación y transferencia de datos.
 * Es la base para el filtrado del Ejercicio 3.
 * @author [José Acosta / Nicolas Olivera]
 * @version 2.0
 */
public interface IConectable {
    
    /**
     * Activa el módulo de comunicación del vehículo.
     * @return true si la conexión se establece.
     */
    boolean conectarAInternet();

    /**
     * Devuelve el estado actual del enlace.
     * @return Cadena informativa del estado.
     */
    String obtenerEstadoConexion();
}
