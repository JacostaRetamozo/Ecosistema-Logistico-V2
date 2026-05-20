package logistica.modelo;

/**
 * Clase abstracta que representa la entidad base para cualquier unidad de transporte
 * dentro del Ecosistema Logístico Inteligente.
 * Proporciona la identidad fundamental y establece el contrato para la movilidad.
 * @author [José Acosta / Nicolas Olivera]
 * @version 2.0
 */
public abstract class Vehiculo {

    /**
     * Identificador único del vehículo.
     * Se define como String para cumplir con los requerimientos de la práctica y
     * como final para asegurar la inmutabilidad de la identidad del objeto.
     */
    private final String id;

    /**
     * Constructor para la inicialización segura de la identidad del vehículo.
     * @param id Identificador alfanumérico único de la unidad.
     */
    public Vehiculo(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del vehículo no puede ser nulo o vacío.");
        }
        this.id = id;
    }

    /**
     * Recupera el identificador único de la unidad de transporte.
     * @return El valor String del ID del vehículo.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Define el comportamiento de desplazamiento específico de la unidad.
     * Cada subclase concreta debe implementar su propia lógica de movimiento.
     */
    public abstract void patronMovimiento();
}
