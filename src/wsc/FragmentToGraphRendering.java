package wsc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

/** A class for debugging the structure created
 * using the fragmented representation. */
public class FragmentToGraphRendering {

	public static void main(String[] args) {
		if (args.length == 0) {
			throw new InvalidParameterException("A filename must be provided as an argument to the main method.");
		}

		String txtFilename = args[0];
		String dotFilename = txtFilename.replaceAll(".txt", ".dot");
		String pdfFilename = txtFilename.replaceAll(".txt", ".pdf");

		try {
			// Create a string builder for the string representation of our graph
			StringBuilder builder = new StringBuilder();
			builder.append("digraph g { ");

			String latestRoot = "";
			String successor;
			Scanner scan = new Scanner(new File(txtFilename));
			scan.useDelimiter("\"| |,|\\[|\\]|\\)");
			while(scan.hasNext()) {
				String token = scan.next();
				// If it starts with a parenthesis, then we have the fragment root
				if (token.contains("(")) {
					// The first part is the new root
					latestRoot = token.replace("(", "");
				}
				else if (!token.isEmpty())
					builder.append(String.format("%s -> %s; ", token, latestRoot));
			}
			builder.append("}");

			// Now write the graph to a file
			FileWriter writer = new FileWriter(new File(dotFilename));
			writer.append(builder.toString());
			writer.close();

			// Now generate the pdf file
			Runtime.getRuntime().exec(String.format("dot -Tpdf %s -o %s", dotFilename, pdfFilename));
			System.out.println("Done!");
		} catch (FileNotFoundException e) {
			System.err.println("Fragment file could not be found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Translated dot file could not be written out.");
			e.printStackTrace();
		}
	}
}


