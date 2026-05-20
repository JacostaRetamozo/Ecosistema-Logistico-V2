package logistica.modelo;

/**
 * Representa una unidad de transporte aérea no tripulada (Drone).
 * Implementa IConectable para integrarse al monitoreo inteligente.
 * @author [José Acosta / Nicolas Olivera]
 * @version 2.0
 */
public class DronTransporte extends Vehiculo implements IConectable {

    private final double autonomiaBateria;
    private boolean enLinea;

    /**
     * Constructor validado para el Dron de transporte.
     * @param id Identificador alfanumérico único.
     * @param autonomiaBateria Tiempo de vuelo estimado en minutos.
     */
    public DronTransporte(String id, double autonomiaBateria) {
        super(id);
        if (autonomiaBateria <= 0) {
            throw new IllegalArgumentException("La autonomía de la batería debe ser mayor a cero.");
        }
        this.autonomiaBateria = autonomiaBateria;
        this.enLinea = false;
    }

    @Override
    public void patronMovimiento() {
        System.out.println("Dron " + getId() + ": Despegue vertical y navegación por corredores aéreos automáticos.");
    }

    @Override
    public boolean conectarAInternet() {
        this.enLinea = true;
        return this.enLinea;
    }

    @Override
    public String obtenerEstadoConexion() {
        return this.enLinea ? "Conectado - Enlace Satelital Activo" : "Desconectado";
    }

    public double getAutonomiaBateria() {
        return this.autonomiaBateria;
    }

    @Override
    public String toString() {
        return String.format("DronTransporte [ID: %s | Batería: %.1f min | Conexión: %s]", 
                getId(), autonomiaBateria, obtenerEstadoConexion());
    }
}
