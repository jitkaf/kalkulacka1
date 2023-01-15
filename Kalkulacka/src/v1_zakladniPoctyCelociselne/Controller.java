package v1_zakladniPoctyCelociselne;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
	@FXML
	private Button tlacitko;
	@FXML
	private TextField vysledek;
	@FXML
	private Label vypocet;

	private int cislo1 = 0;
	private int cislo2 = 0;
	private String operace = "";
	private boolean zadavasePrvniCislo = true;
	private int cisloVPameti;

	public void vypisTextNaTlacitku(ActionEvent event) {
		String textNaTlacitku = ((Button) event.getSource()).getText();
		System.out.println(textNaTlacitku);
	}

	public void ulozOperaci(ActionEvent event) {

		if (cislo2 != 0) {
			switch (operace) {
			case "+":
				cislo1 = cislo1 + cislo2;
				break;
			case "-":
				cislo1 = cislo1 - cislo2;
				break;
			case "*":
				cislo1 = cislo1 * cislo2;
				break;
			case "/":
				cislo1 = cislo1 / cislo2;
				break;
			}
			vysledek.setText("" + cislo1);

			cislo2 = 0;
		}
		operace = ((Button) event.getSource()).getText();
		vypocet.setText("" + cislo1);
		if (vypocet.getText().isEmpty()) {
			vypocet.setText("0" + operace);
		} else {
			vypocet.setText(vypocet.getText() + operace);
		}

		zadavasePrvniCislo = false;

	}

	public void spocti(ActionEvent event) {
		if (operace == null) {
			vypocet.setText(cislo1 + "=");
			vysledek.setText("" + cislo1);
			return;
		}
		vypocet.setText(cislo1 + operace + cislo2 + "=");
		System.out.println("pocitam: " + cislo1 + " " + operace + " " + cislo2);
		switch (operace) {

		case "+":
			cislo1 = cislo1 + cislo2;
			break;
		case "-":
			cislo1 = cislo1 - cislo2;
			break;
		case "x":
			cislo1 = cislo1 * cislo2;
			break;
		case "/":
			if (cislo2 == 0) {
				vysledek.setText("Nedefinovany vysledek");
				return;
			}
			cislo1 = cislo1 / cislo2;
			break;
		}

		vysledek.setText("" + cislo1);

	}

	public void ulozCislo(ActionEvent event) {
		String cislo = ((Button) event.getSource()).getText();
		System.out.println(cislo + " -");
		if (zadavasePrvniCislo) {
			cislo1 = cislo1 * 10 + Integer.parseInt(cislo);

			System.out.println("+ " + cislo1);
			vysledek.setText("" + cislo1);
		} else {
			cislo2 = cislo2 * 10 + Integer.parseInt(cislo);

			vysledek.setText("" + cislo2);
		}
	}

	public void zpracujC(ActionEvent event) {
		System.out.println("kuk");
		operace = "";
		zadavasePrvniCislo = true;
		cislo1 = 0;
		cislo2 = 0;
		vypocet.setText("");
		vysledek.setText("");
	}

	public void zpracujCE(ActionEvent event) {
		if (zadavasePrvniCislo) {
			cislo1 = 0;
			operace = "";
			vypocet.setText("");
			vysledek.setText("0");
		} else {
			cislo2 = 0;
			vypocet.setText(cislo1 + operace);
			vysledek.setText("0");
		}
	}

	/**
	 * Vymaûe celou pamÏù
	 * 
	 * @param event
	 */
	public void zpracujMC(ActionEvent event) {
		cisloVPameti = 0;
	}

	/**
	 * Vyvol· pamÏù
	 * 
	 * @param event
	 */
	public void zpracujMR(ActionEvent event) {
		vysledek.setText("" + cisloVPameti);
		System.out.println(cisloVPameti + " -");
		if (zadavasePrvniCislo) {
			cislo1 = cisloVPameti;
		} else {
			cislo2 = cisloVPameti;
		}
	}

	/**
	 * P¯iËte do pamÏti
	 * 
	 * @param event
	 */
	public void zpracujMPlus(ActionEvent event) {
		cisloVPameti = cisloVPameti + Integer.parseInt(vysledek.getText());
	}

	/**
	 * Odecte od pamÏti
	 * 
	 * @param event
	 */
	public void zpracujMMinus(ActionEvent event) {
		cisloVPameti = cisloVPameti - Integer.parseInt(vysledek.getText());
	}

	/**
	 * Ulozi do pamÏti
	 * 
	 * @param event
	 */
	public void zpracujMS(ActionEvent event) {
		cisloVPameti = Integer.parseInt(vysledek.getText());
	}

	/**
	 * Zonrazi pamet
	 * 
	 * @param event
	 */
	public void zpracujM(ActionEvent event) {

	}
}
