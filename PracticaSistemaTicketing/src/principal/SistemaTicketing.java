package principal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import modelo.Asiento;
import modelo.Categoria;
import modelo.Concierto;
import modelo.Evento;
import modelo.ModoAforo;
import modelo.Sesion;
import modelo.Teatro;
import pagos.Pago;
import pagos.PagoBizum;
import pagos.PagoPayPal;
import pagos.PagoTarjeta;
import pedidos.Carrito;
import pedidos.Entrada;
import pedidos.Operacion;
import pedidos.Pedido;
import pedidos.TipoOperacion;

import java.time.LocalDateTime;

public class SistemaTicketing {

    private ArrayList<Evento> catalogo = new ArrayList<>();
    private Stack<Operacion> historial = new Stack<>();
    private Queue<Pedido> colaPedidos = new LinkedList<>();
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SistemaTicketing sistema = new SistemaTicketing();
        sistema.inicializarDatos();
        sistema.menu();
    }

    public void inicializarDatos() {
        System.out.println("Cargando catálogo de eventos...");

        Concierto c1 = new Concierto("Festival Rock", "Wizink Center", Categoria.CONCIERTO, true, false);
        Concierto c2 = new Concierto("Concierto Indie", "Sala Riviera", Categoria.CONCIERTO, false, false);

        Sesion s1 = new Sesion(LocalDateTime.now().plusDays(10), 500, 500, ModoAforo.GENERAL);
        Sesion s2 = new Sesion(LocalDateTime.now().plusDays(10), 500, 500, ModoAforo.GENERAL);

        c1.addSesion(s1);
        c2.addSesion(s2);
        
        Teatro t1 = new Teatro("El Rey León", "Teatro Lope de Vega", Categoria.TEATRO, false, true);
        Sesion s3 = new Sesion(LocalDateTime.now().plusDays(5), 100, 100, ModoAforo.NUMERADO); // O como lo llames
        t1.addSesion(s3);
        
        
        this.catalogo.add(c1);
        this.catalogo.add(c2); 
        this.catalogo.add(t1);
    }

    public void menu() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--> SISTEMA DE VENTA DE ENTRADAS <--");
            System.out.println("1. Ver catálogo");
            System.out.println("2. Comprar entradas");
            System.out.println("3. Deshacer última operación");
            System.out.println("4. Procesar cola de pedidos");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            try {
                // Intentamos leer el número
                int seleccion = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer

                if (seleccion == 1) {
                    verCatalogo();
                } else if (seleccion == 2) {
                    iniciarCompra();
                } else if (seleccion == 3) {
                    deshacerUltimaOperacion();
                } else if (seleccion == 4) {
                    procesarColaPedidos();
                } else if (seleccion == 5) {
                    salir = true;
                    System.out.println("Hasta prontooooo");
                } else {
                    System.out.println("Opción incorrecta, elige un número del 1 al 5.");
                }

            } catch (java.util.InputMismatchException e) {
                // Si salta el error de que no es un número, caemos aquí
                System.out.println("Error: Debes introducir un NÚMERO, no letras.\n");
                sc.nextLine();
            }
        }
    }

    public void verCatalogo() {
        System.out.println("\nCATÁLOGO DE EVENTOS:");
        for (Evento e : this.catalogo) {
            System.out.println(e.toString());
            for (Sesion s : e.getSesiones()) {
                System.out.println("  -> Sesión: " + s.getIdSesion() + " | Fecha: " + s.getFechaHora());
            }
        }
    }

    public void iniciarCompra() {
        System.out.println("INICIANDO COMPRA: ");

        verCatalogo();

        System.out.print("\nEscribe el ID del evento que quieres (ej. EV-01): ");
        String idEvento = sc.nextLine();

        Evento eventoElegido = null;
        for (Evento e : this.catalogo) {
            if (e.getId().equals(idEvento)) {
                eventoElegido = e;
                break;
            }
        }

        if (eventoElegido == null) {
            System.out.println("Error: Evento no encontrado.");
            return;
        }

        System.out.print("Escribe el ID de la sesión (ej. SES-01): ");
        String idSesion = sc.nextLine();

        Sesion sesionElegida = eventoElegido.getSesionById(idSesion);

        if (sesionElegida == null) {
            System.out.println("Error: Sesión no encontrada.");
            return;
        }

        System.out.print("¿Cuántas entradas quieres comprar?: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        if (sesionElegida.hayDisponibilidad(cantidad) == false) {
            System.out.println("no hay suficientes asientos disponibles.");
            return;
        }

        System.out.println("Hay disponibilidad, preparando carrito");

        Carrito miCarrito = new Carrito();
        ArrayList<Entrada> entradasCompradas = new ArrayList<>();
        double precioBase = 20.0; // POR EJEMPLO, 20 EUROS

        if (sesionElegida.getModo() == ModoAforo.GENERAL) {

            sesionElegida.reservarGeneral(cantidad);

            for (int i = 0; i < cantidad; i++) {
                double precioFinal = precioBase * eventoElegido.getRecargoBase();
                Entrada e = new Entrada(eventoElegido.getId(), sesionElegida.getIdSesion(), null,
                        precioFinal);
                miCarrito.addEntrada(e);
                entradasCompradas.add(e);
            }

        } else {

            ArrayList<Asiento> asientosReservados = sesionElegida.reservarAsientos(cantidad);

            if (asientosReservados == null) { // se comprueba lo que devuelve el método reservarAsientos de la clase
                                              // Sesion
                System.out.println("Cancelando la compra...");
                return; // Cortamos la ejecución del método aquí mismo
            }

            for (int i = 0; i < asientosReservados.size(); i++) {
                Asiento a = asientosReservados.get(i);
                double precioFinal = precioBase * eventoElegido.getRecargoBase() * a.getMultiplicadorZona();
                Entrada e = new Entrada(eventoElegido.getId(), sesionElegida.getIdSesion(), a,
                        precioFinal);
                miCarrito.addEntrada(e);
                entradasCompradas.add(e);
            }
        }

        System.out.println("Total a pagar: " + miCarrito.calcularTotal() + "EUROS");

        System.out.println("MÉTODO DE PAGO: ");
        System.out.println("1. Bizum");
        System.out.println("2. Tarjeta de Crédito");
        System.out.println("3. PayPal");
        System.out.print("Elige cómo quieres pagar: ");

        int opcionPago = sc.nextInt();
        sc.nextLine();

        Pago pago = null;

        if (opcionPago == 1) {
            System.out.print("Escribe tu número de teléfono: ");
            String telefono = sc.nextLine();
            pago = new PagoBizum("PAGO-1", telefono);

        } else if (opcionPago == 2) {
            System.out.print("Escribe el número de la tarjeta: ");
            String numTarjeta = sc.nextLine();
            System.out.print("Escribe el nombre del titular: ");
            String titular = sc.nextLine();
            pago = new PagoTarjeta("PAGO-1", numTarjeta, titular);

        } else if (opcionPago == 3) {
            System.out.print("Escribe tu email de PayPal: ");
            String email = sc.nextLine();
            pago = new PagoPayPal("PAGO-1", email, 1.50);

        } else {
            System.out.println("Opción no válida. Se cancela la compra");
            return;
        }

        Pedido miPedido = new Pedido(miCarrito, pago);

        Operacion op = new Operacion(TipoOperacion.COMPRA,
                "Compra de " + cantidad + " entradas para " + eventoElegido.getNombre(), entradasCompradas);
        this.historial.push(op);

        this.colaPedidos.add(miPedido);

        System.out.println("COMPRA FINALIZADA");
        System.out.println(miPedido.toString());

    }

    public void deshacerUltimaOperacion() {
        System.out.println("Deshacer la última operación");
        if (this.historial.isEmpty()) {
            System.out.println("No hay ninguna operación en el historial para deshacer");
        } else {
            Operacion ultima = this.historial.pop();
            System.out.println("Deshaciendo: " + ultima.getDetalle());
        }
    }

    public void procesarColaPedidos() {
        System.out.println("Procesando cola de pedidos");

        if (this.colaPedidos.isEmpty()) {
            System.out.println("No hay ningún pedido pendiente en la cola");
        } else {
            Pedido pedidoProcesado = this.colaPedidos.poll();
            System.out.println("Pedido procesado");

        }
    }
}