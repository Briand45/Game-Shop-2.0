package tiendajuegosB;

public class Game {
private int id;
private String title;
private Genre genero;
private double precio;
private int stock;
private static int contador=1;

public int getId() {
	return id;
}
public String getTitle() {
	return title;
}
public Genre getGener() {
	return genero;
}
public double getPrecio() {
	return precio;
}
public int getStock() {
	return stock;
}
public void setStock (int stock) {
	this.stock=stock;
}
public void reducirStock(int cantidad) {
	this.stock=stock-cantidad;
}
public boolean comprobarDisponibilidad() {
	boolean disponible=true;
	if(stock<=0) {
		disponible= false;
	}
	return disponible;
}

public String toString() {
	return "nombre "+ title + "genero " + genero + "precio " + precio + "stock " + stock;
}
public Game (String title, Genre genero, double precio,int id) {
	this.title=title;
	this.genero=genero;
	this.precio=precio;
	this.id=contador++;
}

}
