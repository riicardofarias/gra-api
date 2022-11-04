package com.movielist.api.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class FileReaderUtil {
    @SneakyThrows
    public static List<String[]> getFile(File file) {
        try(FileReader reader = new FileReader(file)) {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            try (CSVReader csv = new CSVReaderBuilder(reader).withCSVParser(parser).withSkipLines(1).build()) {
                return csv.readAll();
            }
        }
    }
}
