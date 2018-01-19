package com.akamai.config;

import com.akamai.model.Job;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class JobParser {
    private String fileName;

    public JobParser(String fileName) {
        this.fileName = fileName;
    }

    private List<String> loadCSVFile() throws IOException {
        Optional<URL> urlOptional = Optional
                .ofNullable(JobParser.class.getClassLoader().getResource(fileName));
        Path filePath = Paths.get(urlOptional.orElseThrow(IOException::new).getPath());
        return Files.lines(filePath)
                .collect(toList());
    }

    public List<Job> parseJobsFromResourceFile() throws IOException {
        List<String> fileLines = loadCSVFile();
        return fileLines.stream()
                .map(this::parseSingleLine)
                .collect(toList());
    }
    private Job parseSingleLine(String line){
        int[] vals = Arrays.stream(line.split(","))
                .map(Integer::parseInt)
                .mapToInt(x->x)
                .toArray();
        return new Job(vals);
    }

}
