package com.app.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import com.app.model.SaleOrder;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

//write class that extends AbstractVpdf View class
public class SaleOrderPdfView extends AbstractPdfView{
	//override build Pdf Document
	@Override
	protected void buildPdfDocument(
			Map<String, Object> model, 
			Document document, PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws Exception {
		//download document
		response.addHeader("Content-Disposition", "attachment;filename=SaleOrder.pdf");
		//provide custom font Details object
		Font font = new Font(Font.HELVETICA, 20, Font.BOLD, Color.BLUE);
		//create element paragraph with Font
		Paragraph p = new Paragraph("WELCOME TO PART",font);
		p.setAlignment(Element.ALIGN_CENTER);
		//add element to document else  it will not display at PDF
		document.add(p);

		//reading data from Controller using Model
		@SuppressWarnings("unchecked")
		List<SaleOrder> list = (List<SaleOrder>) model.get("list");
		//Create one table with no.of columns to be display
		PdfPTable table = new PdfPTable(6); //no of columns
		table.setSpacingBefore(4.0f);
		table.setTotalWidth(new float[] {1.0f,1.0f,1.5f,1.0f,1.0f,1.5f});
		//add data to columns using addCell method
		//once 6 cells done then it will chnage row automatically
		table.addCell("ID");
		table.addCell("CODE");
		table.addCell("NUMBER");
		table.addCell("MODE");
		table.addCell("SOURCE");
		table.addCell("STATUS");
		table.addCell("DESCRIPTION");
		//ADD LIST DATA TO TABLE
		for(SaleOrder so:list) {
			table.addCell(so.getId().toString());
			table.addCell(so.getOrderCode().toString());
			table.addCell(so.getReferenceNumber().toString());
			table.addCell(so.getStockMode().toString());
			table.addCell(so.getStockSource().toString());
			table.addCell(so.getStatus().toString());
			table.addCell(so.getDescription().toString());
		}
		//add table to document
		document.add(table);
	}

}
