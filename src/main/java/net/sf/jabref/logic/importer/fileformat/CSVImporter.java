package net.sf.jabref.logic.importer.fileformat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jabref.logic.importer.Importer;
import net.sf.jabref.logic.importer.ParserResult;
import net.sf.jabref.logic.util.FileExtensions;
import net.sf.jabref.model.entry.BibEntry;
import net.sf.jabref.model.entry.BibtexEntryTypes;

public class CSVImporter extends Importer {

    @Override
    public String getName() {
        return "CSV Importer";
    }

    @Override
    public FileExtensions getExtensions() {
        return FileExtensions.CSV;
    }

    @Override
    public String getDescription() {
        return "Imports CSV files, where every field is separated by a semicolon.";
    }

    @Override
    public boolean isRecognizedFormat(BufferedReader reader) {
        return true; // this is discouraged except for demonstration purposes
    }

    @Override
    public ParserResult importDatabase(BufferedReader input) throws IOException {
        List<BibEntry> bibitems = new ArrayList<>();

        String line = input.readLine();
        int cont = 0;
        while (line != null) {
            System.out.println(line);
            while (cont < 2) {
                line = input.readLine();
                cont++;
            }
            if (!line.trim().isEmpty()) {
                int contador = 0, apartir=0;
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                BibEntry be = new BibEntry();
                be.setType(BibtexEntryTypes.TECHREPORT);
                be.setField("year", fields[5]);
                be.setField("author", fields[1]);
                be.setField("title", fields[0]);
                bibitems.add(be);
                line = input.readLine();
            }
        }
        return new ParserResult(bibitems);
    }
}
