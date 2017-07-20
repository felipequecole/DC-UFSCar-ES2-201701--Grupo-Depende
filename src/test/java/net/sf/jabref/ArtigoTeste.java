package net.sf.jabref;

import net.sf.jabref.model.entry.BibEntry;
import net.sf.jabref.model.entry.BibtexEntryTypes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArtigoTeste {

    private BibEntry entry;

    @Before
    public void setUp() {
        entry = new BibEntry();

        entry.setType(BibtexEntryTypes.ARTICLE);
        entry.setField("year", "2018");
        entry.setField("month", "#jun#");
        entry.setField("url", "https://google.com" );
        entry.setField("doi", "10.1000/182" );
    }

    @Test
    public void testYearField() throws Exception {
        String regex = "[1-9][0-9][0-9][0-9]";
        String year = entry.getField("year").get();
        if (year.matches(regex))
            System.out.println("Está no padrão YYYY");
        else
            System.out.println("Não está no padrão YYYY");

        Assert.assertEquals(true, year.matches(regex));
    }

    @Test
    public void testMonthField() throws Exception {
        String regex1 = "[0-1][1-2]";
        String regex2 = "(#)((jan)|(feb)|(mar)|(apr)|(may)|(jun)|(jul)|(aug)|(sep)|(oct)|(nov)|(dec))(#)";
        String month = entry.getField("month").get();
        if (month.matches(regex1)) {
            System.out.println("Está no padrão MM");
            Assert.assertEquals(true, month.matches(regex1));
        }
        else if (month.matches(regex2)) {
            System.out.println("Está no padrão #mmm#");
            Assert.assertEquals(true, month.matches(regex2));
        }else {
            System.out.println("Não está em um formato aceito");
            Assert.assertEquals(true, month.matches(regex1));
        }
    }

    @Test
    public void testUrlField() throws Exception {
        String regex = "(http)(s)?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        String url = entry.getField("url").get();
        if (url.matches(regex))
            System.out.println("Está no padrão url");
        else
            System.out.println("Não está no padrão url");

        Assert.assertEquals(true, url.matches(regex));
    }
        @Test
        public void testDoiField() throws Exception {
            String regex = "[0-9][0-9][.][0-9][0-9][0-9][0-9][/][0-9][0-9][0-9]";
            String doi = entry.getField("doi").get();
            if (doi.matches(regex))
                System.out.println("Está no padrão doi");
            else
                System.out.println("Não está no padrão doi");

            Assert.assertEquals(true, doi.matches(regex));
        }
}

