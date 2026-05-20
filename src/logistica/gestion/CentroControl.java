package logistica.vista;

import logistica.controlador.GestorLogistico;
import logistica.modelo.CamionAutonomo;
import logistica.modelo.DronTransporte;
import logistica.modelo.Vehiculo;
import logistica.modelo.IConectable;

import java.util.List;

/**
 * Clase principal del sistema que actúa como el orquestador de operaciones.
 * Ejecuta la integración de datos, pruebas CRUD, Lambdas, Streams y Reportes.
 * @author [José Acosta / Nicolas Olivera]
 * @version 2.0
 */
public class CentroControl {

    public static void main(String[] args) {
        GestorLogistico gestor = new GestorLogistico();

        imprimirCabecera("EJERCICIO 5: INTEGRACIÓN - CREACIÓN DE VEHÍCULOS");
        
        // Instanciación de objetos de prueba
        CamionAutonomo camion1 = new CamionAutonomo("CAM-001", 25.5);
        CamionAutonomo camion2 = new CamionAutonomo("CAM-002", 12.0);
        DronTransporte dron1 = new DronTransporte("DRN-501", 45.0);
        DronTransporte dron2 = new DronTransporte("DRN-502", 30.0);

        // Establecer conexiones en algunas unidades para validar los Streams de filtrado
        camion1.conectarAInternet();
        dron1.conectarAInternet();

        // Inserción en el CRUD
        gestor.crearVehiculo(camion1);
        gestor.crearVehiculo(camion2);
        gestor.crearVehiculo(dron1);
        gestor.crearVehiculo(dron2);
        System.out.println("Vehículos iniciales registrados con éxito.");

        imprimirCabecera("EJERCICIO 2: RECORRIDO DE COLECCIÓN (EXPRESIONES LAMBDA)");
        gestor.mostrarVehiculos();

        imprimirCabecera("EJERCICIO 1: PRUEBA DE OPERACIONES CRUD");
        // Buscar
        System.out.println("Buscando CAM-001... ");
        gestor.buscarVehiculo("CAM-001").ifPresent(v -> System.out.println("Encontrado: " + v));

        // Modificar
        System.out.println("\nModificando capacidad de CAM-002...");
        CamionAutonomo camionModificado = new CamionAutonomo("CAM-002", 18.5);
        if (gestor.modificarVehiculo("CAM-002", camionModificado)) {
            System.out.println("Vehículo modificado con éxito. Nueva flota:");
            gestor.mostrarVehiculos();
        }

        // Eliminar
        System.out.println("\nEliminando DRN-502...");
        if (gestor.eliminarVehiculo("DRN-502")) {
            System.out.println("Vehículo eliminado. Flota actualizada:");
            gestor.mostrarVehiculos();
        }

        imprimirCabecera("EJERCICIO 3: PROCESAMIENTO AVANZADO CON STREAMS");
        
        System.out.println("1. Vehículos Conectables (Filtrados por interfaz):");
        gestor.obtenerVehiculosConectables().forEach(System.out.println);

        System.out.println("\n2. Lista de IDs únicos extraídos de la flota:");
        List<String> ids = gestor.obtenerListaDeIds();
        System.out.println(ids);

        System.out.println("\n3. Cantidad total de vehículos activos: " + gestor.contarVehiculos());

        System.out.println("\n4. Búsqueda específica por Tipo (DronTransporte):");
        gestor.buscarVehiculosPorTipo(DronTransporte.class).forEach(System.out.println);

        imprimirCabecera("EJERCICIO 4: PRUEBAS DE ORDENAMIENTO (STREAMS / COMPARATORS)");
        
        System.out.println("Ordenando flota por ID (Ascendente):");
        gestor.ordenarPorId();
        gestor.mostrarVehiculos();

        System.out.println("\nOrdenando flota por Tipo de Vehículo:");
        gestor.ordenarPorTipo();
        gestor.mostrarVehiculos();

        imprimirCabecera("EJERCICIO 6: MEJORA PROPUESTA - ESTADÍSTICAS Y REPORTES");
        ejecutarReportesAvanzados(gestor);
    }

    /**
     * Implementación completa del Ejercicio 6. Procesa métricas de negocio,
     * clasificación por tipo y búsquedas avanzadas en la flota.
     */
    private static void ejecutarReportesAvanzados(GestorLogistico gestor) {
        System.out.println("=== 1. ESTADÍSTICAS Y RESUMEN OPERATIVO ===");
        long conectados = gestor.buscarConectablesActivos().size();
        long total = gestor.contarVehiculos();
        
        System.out.printf("Total Unidades en Flota : %d%n", total);
        System.out.printf("Unidades en Línea       : %d%n", conectados);
        System.out.printf("Tasa de Conectividad    : %.2f%%%n", (total > 0) ? ((double) conectados / total) * 100 : 0.0);

        System.out.println("\n=== 2. CLASIFICACIÓN DE LA FLOTA (groupingBy) ===");
        gestor.clasificarVehiculosPorTipo().forEach((tipo, lista) -> {
            System.out.printf("Tipo: [%s] -> Cantidad: %d unidades%n", tipo, lista.size());
            lista.forEach(v -> System.out.println("   > " + v));
        });

        System.out.println("\n=== 3. BÚSQUEDA AVANZADA (Unidades Críticas en Línea) ===");
        List<Vehiculo> activos = gestor.buscarConectablesActivos();
        if (activos.isEmpty()) {
            System.out.println("No se detectan unidades con transmisión activa de telemetría.");
        } else {
            activos.forEach(v -> System.out.println("   [ALERTA] Unidad Operativa: " + v.getId()));
        }
        System.out.println("=======================================================================");
    }

    /**
     * Utilidad estética para mantener el orden y limpieza en la consola de salida.
     */
    private static void imprimirCabecera(String titulo) {
        System.out.println("\n=======================================================================");
        System.out.println("  " + titulo);
        System.out.println("=======================================================================");
    }
}
