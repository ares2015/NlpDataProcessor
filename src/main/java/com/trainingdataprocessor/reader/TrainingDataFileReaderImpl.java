package com.trainingdataprocessor.reader;

import com.trainingdataprocessor.validator.TrainingDataValidator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Oliver on 8/5/2016.
 */
public class TrainingDataFileReaderImpl implements TrainingDataReader {

    private final static Logger LOGGER = Logger.getLogger(TrainingDataFileReaderImpl.class.getName());

    private TrainingDataValidator trainingDataValidator;

    public TrainingDataFileReaderImpl(TrainingDataValidator trainingDataValidator) {
        this.trainingDataValidator = trainingDataValidator;
    }

    @Override
    public List<String> read() {
        LOGGER.info("ENTERING read method of TrainingDataFileReaderImpl... ");
        LOGGER.info("*********************************************************************");
        List<String> testDataRowList = new ArrayList<>();
        BufferedReader br = null;
        int lineNumber = 0;
        try {
            br = new BufferedReader(new FileReader("c:\\Users\\Oliver\\Documents\\NlpTrainingData\\NlpTrainingData.txt"));
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String testDataRow = br.readLine();
            while (testDataRow != null) {
                lineNumber++;
                if (!"".equals(testDataRow)) {
                    trainingDataValidator.validate(testDataRow, lineNumber);
                    testDataRowList.add(testDataRow);
                }
                testDataRow = br.readLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("LEAVING read method of TrainingDataFileReaderImpl with  " + testDataRowList.size() + " lines read.");
        LOGGER.info("*********************************************************************");
        return testDataRowList;
    }
}