package tiendajuegosB;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<Game> games;
    private List<Customer> customers;
    private List<Purchase> purchases;

    public Store() {
        this.games = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.purchases = new ArrayList<>();
    }
    public List<Game> getGames() { return games; }
    public List<Customer> getCustomers() { return customers; }
    public List<Purchase> getPurchases() { return purchases; }
    // 8.1. AÑADIR JUEGO
    public boolean añadirJuego(Game game) {
        if (game == null) 
            return false;       
        for (int i = 0; i < games.size(); i++) {           
            if (games.get(i).getId() == game.getId()) {
                return false; 
            }
        }
        return games.add(game);
    }
    // 8.2. BUSCAR JUEGO POR ID 
    public Game buscarJuegos(int id) throws Exception {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == id) {
                return games.get(i);
            }
        }
        throw new Exception("El videojuego con ID '" + id + "' no existe en el catálogo.");
    }
   
    // 8.3. BUSCAR CLIENTES POR ID
    public Customer buscarClientes(int id) throws Exception {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                return customers.get(i);
            }
        }
        throw new Exception("El cliente con ID '" + id + "' no existe.");
    }
    // 8.4. FILTRAR JUEGOS POR TEXTO 
    public Game buscarJuegosPorLetra(String nombre) throws Exception {
        Game juegoObtenido = null;
        if (nombre != null) {
            for (int i = 0; i < games.size(); i++) {
                if (games.get(i).getTitle().toUpperCase().contains(nombre.toUpperCase())) {
                    juegoObtenido = games.get(i);
                    break; // Detenemos la búsqueda al encontrar la primera coincidencia
                }
            }
        }  
        if (juegoObtenido == null) {
            throw new Exception("No hay ningún juego con ese título.");
        }
        return juegoObtenido;
    }
    // 8.5. FILTRAR JUEGOS POR GÉNERO
    public List<Game> filtro(Genre genre) {
        List<Game> filtroJuegos = new ArrayList<>();
        if (genre != null) {
            for (int i = 0; i < games.size(); i++) {
                if (games.get(i).getGener() == genre) {
                    filtroJuegos.add(games.get(i));
                }
            }
        }
        return filtroJuegos;
    }
    // 8.6. COMPRAR VIDEOJUEGOS
    public Purchase comprarJuegos(int customerId, int gameId, int quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("No es posible realizar una compra con una cantidad de " + quantity + ". Debe ser un número positivo.");
        }
        Customer customer = buscarClientes(customerId);
        Game game = buscarJuegos(gameId);    
        if (!game.comprobarDisponibilidad()) {
            throw new Exception("No hay stock disponible para el videojuego '" + game.getTitle() + "'.");
        }
        if (game.getStock() < quantity) {
            throw new Exception("Stock insuficiente. Unidades disponibles: " + game.getStock());
        }
        double totalPrice = game.getPrecio() * quantity;
        if (!customer.saldoDisponible(totalPrice)) {
            throw new Exception("Saldo insuficiente. Saldo actual: " + customer.getBalance() + "€, Requerido: " + totalPrice + "€.");
        }
        // Actualizamos inventario y costes
        game.reducirStock(quantity);
        customer.retirarBalance(totalPrice);        
        // Guardar y devolver la compra
        Purchase purchase = new Purchase(customer, game, quantity);
        purchases.add(purchase);
        return purchase;
    }
}