package v2_zakladniPoctyCelociselneADalsiKlavesy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Controller {
	@FXML
	private Button tlacitko;	
	@FXML
	private Label vysledek;
	@FXML
	private Label vypocet;
	@FXML
	private Button procento;
	@FXML
	private Button b;
	@FXML
	private Button prevracena;
	@FXML
	private Button mocnina;
	@FXML
	private Button odmocnina;
	@FXML
	private Button plusMinus;
	@FXML
	private Button MR;
	@FXML
	private Button MC;

	private double cislo1 = 0;
	private double cislo2 = 0;
	private String operace = "";
	private boolean zadavasePrvniCislo = true;
	private boolean zadavaseDalsiOperator = false;
	private double cisloVPameti;
	private boolean desetinnaCarka = false;
	
	public void initialize() {
		plusMinus.setDisable(true);
		odmocnina.setDisable(true);
		prevracena.setDisable(true);
		b.setDisable(true);
		procento.setDisable(true);
		MR.setDisable(true);
		MC.setDisable(true);
	
	}

	public void vypisTextNaTlacitku(ActionEvent event) {
		String textNaTlacitku = ((Button) event.getSource()).getText();
		System.out.println(textNaTlacitku);
	}

	public void ulozOperaci(ActionEvent event) {
		ulozOperaci(((Button)(event.getSource())).getText());
	}

	public void spocti(ActionEvent event) {
		if (operace == null || operace.isEmpty()) {
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
		desetinnaCarka = false;
		zadavaseDalsiOperator = false;
		cislo2 = 0;

	}

	public void stiskCiselnehoTlacitka(ActionEvent event) {
		String cislo = ((Button) event.getSource()).getText();
		ulozCislo(cislo);
	}

	/**
	 * P¯ijme desetinnou Ë·rku
	 * 
	 * @param event
	 */
	public void desetinneCislo(ActionEvent event) {
		desetinnaCarka = true;
	}

	public void zpracujC(ActionEvent event) {
		operace = "";
		zadavasePrvniCislo = true;
		cislo1 = 0;
		cislo2 = 0;
		vypocet.setText("");
		vysledek.setText("");
		desetinnaCarka = false;
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
		desetinnaCarka = false;
	}

	/**
	 * Vymaûe celou pamÏù
	 * 
	 * @param event
	 */
	public void zpracujMC(ActionEvent event) {
		cisloVPameti = 0;
		MR.setDisable(true);
		MC.setDisable(true);
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
		cisloVPameti = cisloVPameti + Double.parseDouble(vysledek.getText());
		MR.setDisable(false);
		MC.setDisable(false);
	}

	/**
	 * Odecte od pamÏti
	 * 
	 * @param event
	 */
	public void zpracujMMinus(ActionEvent event) {
		cisloVPameti = cisloVPameti - Double.parseDouble(vysledek.getText());
		MR.setDisable(false);
		MC.setDisable(false);
	}

	/**
	 * Ulozi do pamÏti
	 * 
	 * @param event
	 */
	public void zpracujMS(ActionEvent event) {
		cisloVPameti = Double.parseDouble(vysledek.getText());
		MR.setDisable(false);
		MC.setDisable(false);
	}

	/**
	 * Zonrazi pamet
	 * 
	 * @param event
	 */
	public void zpracujM(ActionEvent event) {

	}

	public void stiskKlavesy(KeyEvent event) {
		String stisknutaKlavesa = event.getText();
		System.out.println("Byla stiötÏna kl·vesa: " + stisknutaKlavesa);
		switch (stisknutaKlavesa) {
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
				ulozCislo(stisknutaKlavesa);
				break;
			case ".":
			case ",":
				desetinnaCarka = true;
				break;
			case "-":
			case "+":
			case "*":
			case "/":
				ulozOperaci(stisknutaKlavesa);
				break;

		}
	}
	
	
	
	private void ulozOperaci(String oper) {
		if (zadavaseDalsiOperator) {
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
		}
		operace = oper;
		vypocet.setText("" + cislo1);
		if (vypocet.getText().isEmpty()) {
			vypocet.setText("0" + operace);
		} else {
			vypocet.setText(vypocet.getText() + operace);
		}

		zadavasePrvniCislo = false;
		desetinnaCarka = false;
		zadavaseDalsiOperator = true;
	}
	
	
	private void ulozCislo(String cislo) {
		System.out.println(cislo + " -");
		if (zadavasePrvniCislo) {
			if (!desetinnaCarka) {
				cislo1 = cislo1 * 10 + Integer.parseInt(cislo);
			} else {

				String cisloText = "" + cislo1;
				if (cisloText.endsWith(".0")) {
					cisloText = cisloText.replace(".0", ".");
				}
				cisloText = cisloText + cislo;
				cislo1 = Double.parseDouble(cisloText);
			}

			System.out.println("+ " + cislo1);
			vysledek.setText("" + cislo1);
		} else {
			if (!desetinnaCarka) {
				cislo2 = cislo2 * 10 + Integer.parseInt(cislo);
			} else {
				String cisloText = "" + cislo2;
				if (cisloText.endsWith(".0")) {
					cisloText = cisloText.replace(".0", ".");
				}
				cisloText = cisloText + cislo;
				cislo2 = Double.parseDouble(cisloText);
			}
			vysledek.setText("" + cislo2);
		}
	}
}
