package project1.createpdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class App {
	public static void main(String[] args) throws FileNotFoundException, DocumentException, IOException {
		// System.out.println( "Hello World!" );
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Lotus_flower.pdf"));

		File file = new File("/Users/mateiupatricia/Desktop/lotus.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("Lotus Flower", font);
		document.add(chunk);
		font = FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK);
		while ((st = br.readLine()) != null) {
			Chunk ch = new Chunk(st, font);
			document.add(new Paragraph(" "));
			document.add(ch);
		}

		Image img = Image.getInstance("/Users/mateiupatricia/Desktop/lotus.jpg");
		img.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
		img.scaleToFit(400, 400);
		float x = (PageSize.A4.getWidth() - img.getScaledWidth()) / 2;
		float y = (PageSize.A4.getHeight() - img.getScaledHeight()) / 2;
		img.setAbsolutePosition(x, y);
		//img.setAbsolutePosition(0, 50);
		document.add(img);

		document.close();

		PdfReader pdfReader = new PdfReader("Lotus_flower.pdf");
		PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("encryptedPdf.pdf"));

		pdfStamper.setEncryption("userpass".getBytes(), "ownerpass".getBytes(),
				PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_COPY, PdfWriter.ENCRYPTION_AES_256

		);

		pdfStamper.close();
	}
}
