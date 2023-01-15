/**
 * 
 */
/**
 * @author jitka
 *
 */
module Kalkulacka {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	opens v1_zakladniPoctyCelociselne to javafx.graphics, javafx.fxml;
	opens v2_zakladniPoctyCelociselneADalsiKlavesy to javafx.graphics, javafx.fxml;
}