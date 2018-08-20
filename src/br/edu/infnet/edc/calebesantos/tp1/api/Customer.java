package br.edu.infnet.edc.calebesantos.tp1.api;

import java.util.Enumeration;
import java.util.Vector;

import br.edu.infnet.edc.calebesantos.tp1.models.Rental;

public class Customer {
	private String _name;
	private Vector<Rental> _rentals = new Vector<>();

	public Customer(String name) {
		_name = name;
	}

	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		String result = generateHeader();

		while (rentals.hasMoreElements()) {
			Rental each = (Rental) rentals.nextElement();

			// determine amounts for each line
			double thisAmount = each.calculateAmount();

			// add frequent renter points
			frequentRenterPoints += each.calculateFrequentRenterPoints();

			// show figures for this rental
			result = generateFigureForRental(result, each, thisAmount);
			totalAmount += thisAmount;
		}

		// add footer lines
		result = generateFooter(totalAmount, frequentRenterPoints, result);

		return result;
	}

	private String generateHeader() {
		String result = "Rental Record for " + getName() + "\n";
		return result;
	}

	private String generateFigureForRental(String result, Rental each, double thisAmount) {
		result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
		return result;
	}

	private String generateFooter(double totalAmount, int frequentRenterPoints, String result) {
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
		return result;
	}
}
