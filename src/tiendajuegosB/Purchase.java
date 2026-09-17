package tiendajuegosB;

public class Purchase {

    private Customer customer;
    private Game game;
    private int quantity;
    private double totalPrice;

    public Purchase(Customer customer, Game game, int quantity) {
        this.customer = customer;
        this.game = game;
        this.quantity = quantity;
        this.totalPrice = game.getPrecio() * quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Game getGame() {
        return game;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

/*Cree la clase Purchase, con las variables y cree un constructor, con el getPrecio de la
 * clase game y luego lo multiplico por la cantidad que es la variable quantity */


