package java_loewis;

import de.beuth_hochschule.ti.in3.*;

public class messure_graph {

	public static void main(String[] args) {

		double[] messwerte = readValues();// new double[100];

		// Diagram diagramOriginal = new Diagram(0, 100, 0, 7);
		// Diagram diagramMiddleThree = new Diagram(0, 100, 0, 7);
		// Diagram diagramMiddleTen = new Diagram(0, 100, 0, 7);

		// drawDiagramm(messwerte, diagramOriginal, 1, 0);
		// drawDiagramm(messwerte, diagramMiddleThree, 2, 3);
		// drawDiagramm(messwerte, diagramMiddleTen, 3, 10);

		// Kurzform, wenn weitere Aktionen mit den Diagrammobjekten durchgeführt
		// werden sollen dann
		// sind die oberen beiden Codeblöcke zu nutzen

		// prüft ob die Datei überhaupt Werte enthält
		if (messwerte.length > 0) {
			drawDiagramm(messwerte, new Diagram(0, 100, 0, 7), 0);
			drawDiagramm(messwerte, new Diagram(0, 100, 0, 7), 3);
			drawDiagramm(messwerte, new Diagram(0, 100, 0, 7), 10);
		} else {
			System.out.println("Es liegen Keine Werte zum Zeichnen vor.");
		}

		System.out.println("Fertig");
	}

	// Gibt Werte in einem Diagramm aus
	// Parameter: Werte die im Diagramm ausgegbenen werden sollen,
	// das Diagramm in dem die Werte ausgegeben werden sollen
	// Werte zur Berechnung des Mittelwertes
	private static void drawDiagramm(double[] messwerte, Diagram diagram,
			int middleCount) {
		double mittelwert = 0;
		int div = 1;

		// Mittelwerte berechnen und ausgeben
		for (int i = 0; i < 100; i++) {
			if (middleCount == 0) {
				diagram.addPoint(i, messwerte[i]);
			} else {
				if (i == 0) {
					mittelwert = messwerte[0];
				} else if (i < middleCount - 1 && i > 0) { // Werte unter 10,
															// müssen gesondert
					// behandelt werden da sie keine
					// 10
					// vorhergehenden Werte haben
					div = i + 1;
					for (int k = i; k >= 0; k--) {
						mittelwert += messwerte[i - k];
					}
				} else { // Werte über 10
					div = middleCount;
					for (int k = middleCount - 1; k >= 0; k--) {
						mittelwert += messwerte[i - k];
					}
				}
				// System.out.println(i + " --- " + mittelwert);
				diagram.addPoint(i, mittelwert / div);
				mittelwert = 0;
			}
		}
	}

	// ließt eine Datei ein und speichert die Werte in einem Array
	// Rückgabewert ist der Array
	private static double[] readValues() {
		double[] messwerte = new double[100];
		// Werte in Array packen
		if (SimpleFile.open("data.txt")) {
			System.out.println("Datei geöffnet.");
			for (int i = 0; i < 100; i++) {
				messwerte[i] = SimpleFile.readDouble();
			}
		} else {
			System.out.println("Datei konnte nicht geöffnet werden");
		}
		SimpleFile.close();
		return messwerte;
	}
}
