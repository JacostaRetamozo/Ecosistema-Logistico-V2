package logistica.modelo;

/**
 * Representa un camión pesado con capacidades de conducción autónoma y conectividad.
 */
public class CamionAutonomo extends Vehiculo implements Conectable {

    private final double capacidadCarga;
    private boolean enLinea;

    public CamionAutonomo(String id, double capacidadCarga) {
        super(id);
        if (capacidadCarga <= 0) {
            throw new IllegalArgumentException("La capacidad de carga debe ser mayor a cero.");
        }
        this.capacidadCarga = capacidadCarga;
        this.enLinea = false;
    }

    @Override
    public void patronMovimiento() {
        // Lógica de navegación mediante rutas preestablecidas por GPS
        System.out.println("Camión " + getId() + ": Desplazamiento por autopista en modo autónomo.");
    }

    @Override
    public boolean conectarAInternet() {
        this.enLinea = true;
        return this.enLinea;
    }

    @Override
    public String obtenerEstadoConexion() {
        return this.enLinea ? "Conectado - Transmitiendo Telemetría" : "Desconectado";
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    @Override
    public String toString() {
        return String.format("CamionAutonomo [ID: %s | Carga: %.2f t | Conexión: %s]", 
                getId(), capacidadCarga, obtenerEstadoConexion());
    }
}
