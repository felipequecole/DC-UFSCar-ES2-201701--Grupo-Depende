package net.sf.jabref.logic.importer.fileformat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jabref.logic.importer.Importer;
import net.sf.jabref.logic.importer.ParserResult;
import net.sf.jabref.logic.util.FileExtensions;
import net.sf.jabref.model.entry.BibEntry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;



public class XLSImporter extends Importer {

    @Override
    public String getName() {
        return "XLS Importer";
    }

    @Override
    public FileExtensions getExtensions() {
        return FileExtensions.XLS;
    }

    @Override
    public String getDescription() {
        return "Imports XLS files";
    }

    @Override
    public boolean isRecognizedFormat(BufferedReader reader) {
        return true; // this is discouraged except for demonstration purposes
    }

    @Override
    public ParserResult importDatabase(BufferedReader input) throws IOException {
        System.out.println("entrou no errado se fodeu seu babaca otario");
        return null;
    }



    @Override
    public ParserResult importDatabase(Path filePath, Charset encoding) throws IOException {
        System.out.println("entrou no certo essa merda");
        List<BibEntry> bibitems = new ArrayList<>();
        FileInputStream excelFile = new FileInputStream(new File(filePath.toString()));
        Workbook workbook = new HSSFWorkbook(excelFile);
        Sheet pau = workbook.getSheetAt(0);
        Iterator<Row> iterator = pau.iterator();
        while (iterator.hasNext()){
            Row bobEsponja = iterator.next();
            Iterator<Cell> cellIterator = bobEsponja.iterator();
            int cont = 0;
            BibEntry bibEntry = new BibEntry();
            while (cellIterator.hasNext()){
                Cell pdbeaq = cellIterator.next();
                if(pdbeaq.getCellTypeEnum() == CellType.STRING){
                    if(cont == 0) {
                        bibEntry.setField("author", pdbeaq.getStringCellValue());
                    } else if (cont == 1){
                        bibEntry.setField("title", pdbeaq.getStringCellValue());
                    }
                } else if (pdbeaq.getCellTypeEnum() == CellType.NUMERIC) {
                    if(cont == 2){
                        Double d = pdbeaq.getNumericCellValue();
                        bibEntry.setField("year", String.valueOf(d.intValue()));
                    }
                }
                cont ++;
            }
            bibitems.add(bibEntry);
        }
        excelFile.close();
        return new ParserResult(bibitems);
    }

}
