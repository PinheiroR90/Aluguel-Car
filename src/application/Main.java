package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    System.out.println("Entre com os dados do aluguel");
    System.out.print("Modelo do carro: ");
    String carModel = sc.nextLine();
    System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
    LocalDateTime start = LocalDateTime.parse(sc.nextLine(),format);
    System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
    LocalDateTime finish = LocalDateTime.parse(sc.nextLine(),format);

    CarRental cr = new CarRental(start,finish,new Vehicle(carModel));

    System.out.print("Preço por hora: ");
    Double priceHour = sc.nextDouble();
    System.out.print("Preço por dia: ");
    Double priceDay = sc.nextDouble();

    RentalService rentalService = new RentalService(priceHour,priceDay,new BrazilTaxService());
    rentalService.processInvoice(cr);

    System.out.println("Frtura:");
    System.out.println("Pagamento basico: "+ cr.getInvoice().getBasicPayment());
    System.out.println("Imposto: "+ cr.getInvoice().getTax());
    System.out.println("Pagamento total: "+ cr.getInvoice().getTotalPayment());

    sc.close();
  }
}
