package com.trainingdataprocessor.semantics.extraction;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.semantics.SemanticPreprocessingData;

/**
 * Created by Oliver on 10/21/2016.
 */
public interface SemanticExtractor {

    SemanticExtractionData extract(SemanticPreprocessingData semanticPreprocessingData);

}