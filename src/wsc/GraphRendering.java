package wsc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class GraphRendering {
	public static void main(String[] args) {
		if (args.length == 0) {
			throw new InvalidParameterException("A filename must be provided as an argument to the main method.");
		}
		String dotFilename = args[0];
		String pdfFilename = dotFilename.replaceAll(".dot", ".pdf");

		// Now generate the dot file
		try {
			Runtime.getRuntime().exec(String.format("dot -Tpdf %s -o %s", dotFilename, pdfFilename));
			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
