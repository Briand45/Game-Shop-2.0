package tiendajuegosB;

public class Customer {
	private int id;
	private String name;
	private double balance;
	private static int contador = 1;

	public int getId() {
		return id;
	}

	public String getNombre() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	public Customer(String name, double balance) {
		this.id = contador++;
		this.name = name;
		this.balance = balance;
	}

	public boolean añadirBalance(double balanceMas) {
		if (balanceMas > 0) {
			this.balance += balanceMas;
			return true;
		}
		return false;
	}

	public boolean retirarBalance(double balanceMenos) {
		if (balanceMenos > 0 && this.balance >= balanceMenos) {
			this.balance -= balanceMenos;
			return true;
		}
		return false;
	}

	public boolean saldoDisponible(double cantidad) {
		return this.balance >= cantidad;
	}

	public String toString() {
		return "Customer [ID=" + id + ", Nombre=" + name + ", Saldo=" + balance + "]";
	}
}
