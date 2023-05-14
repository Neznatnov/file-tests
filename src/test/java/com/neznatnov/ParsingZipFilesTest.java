package com.neznatnov;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ParsingZipFilesTest {
    private ClassLoader cl = ParsingZipFilesTest.class.getClassLoader();

    @Test
    @DisplayName("Чтение PDF файла")
    void zipParseTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                if (entry.getName().equals("ace.pdf")) {

                    PDF pdf = new PDF(zis);
                    assertEquals(1, pdf.numberOfPages);
                    assertTrue(pdf.text.startsWith("Козырный туз"));

                }
            }
        }
    }

    @Test
    @DisplayName("Чтение XLSX файла")
    void zipXlsTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("us1.xlsx")) {
                    XLS xls = new XLS(zis);
                    assertTrue(xls.excel.getSheetAt(0).getRow(0).getCell(0)
                            .getStringCellValue().startsWith("Вероника"));
                    assertTrue(xls.excel.getSheetAt(0).getRow(1).getCell(0)
                            .getStringCellValue().startsWith("Иван"));

                }
            }
        }
    }

    @Test
    @DisplayName("Чтение CSV файла")
    void zipCsvTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("test.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("us.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    assertArrayEquals(new String[]{"Иван", "27", "Прага"}, content.get(1));

                }
            }
        }
    }
}