package semantics.modal;

import com.trainingdataprocessor.cache.ConstantWordsCache;
import com.trainingdataprocessor.data.RegexPatternIndexData;
import com.trainingdataprocessor.data.semantics.SemanticRelationData;
import com.trainingdataprocessor.semantics.*;
import com.trainingdataprocessor.tags.EncodedTags;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Oliver on 10/11/2016.
 */
public class SemanticRelationsExtractorImplForModalTest {

    private ConstantWordsCache constantWordsCache = new ConstantWordsCache();

    private SemanticConstantTagAnalyser semanticConstantTagAnalyser = new SemanticConstantTagAnalyserImpl(constantWordsCache);

    private SubjectExtractor subjectExtractor = new SubjectExtractorImpl();

    private PredicateExtractor predicateExtractor = new PredicateExtractorImpl();

    private VerbExtractor verbExtractor = new VerbExtractorImpl();


    @Test
    public void testModalVerbNotRelationship(){
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor,
                verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);


        String sentence = "brave firemen can not fight forest fire";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("JNMLVNN", 0, "JNMLVNN".length() - 1);

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.MODAL_VERB,
                regexPatternIndexData, tokens, encodedTags, SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL);

        assertFalse(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("firemen", semanticRelationData.getAtomicSubject());
        assertEquals("brave firemen", semanticRelationData.getExtendedSubject());
        assertEquals("fire", semanticRelationData.getAtomicPredicate());
        assertEquals("forest fire", semanticRelationData.getExtendedPredicate());
        assertEquals("can not fight", semanticRelationData.getExtendedVerb());
        assertEquals("fight" , semanticRelationData.getAtomicVerb());
        assertEquals("can" , semanticRelationData.getAtomicModalVerb());
    }

    @Test
    public void testModalVerbNotRelationshipWithPreposition(){
        SemanticRelationsExtractorImpl relationshipsExtractorImpl = new SemanticRelationsExtractorImpl(semanticConstantTagAnalyser, subjectExtractor, predicateExtractor, verbExtractor);

        List<String> encodedTags = new ArrayList<String>();
        encodedTags.add(EncodedTags.ADJECTIVE);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.MODAL_VERB);
        encodedTags.add(EncodedTags.NOT);
        encodedTags.add(EncodedTags.VERB);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.PREPOSITION);
        encodedTags.add(EncodedTags.NOUN);
        encodedTags.add(EncodedTags.NOUN);

        String sentence = "brave firemen can not fight forest fire in California mountains";
        List<String> tokens = Arrays.asList(sentence.split("\\ "));

        RegexPatternIndexData regexPatternIndexData = new RegexPatternIndexData("JNMLVNNPNN", 0, "JNMLVNNPNN".length() - 1);

        SemanticRelationData semanticRelationData = relationshipsExtractorImpl.extract(EncodedTags.MODAL_VERB, regexPatternIndexData, tokens, encodedTags,
                SemanticRelationConstantType.MODAL_VERB_NOT_3_LEVEL);

        assertFalse(semanticRelationData.isPositiveVerb());
        assertTrue(semanticRelationData.isPresentTense());
        assertEquals("firemen", semanticRelationData.getAtomicSubject());
        assertEquals("brave firemen", semanticRelationData.getExtendedSubject());
        assertEquals("fire", semanticRelationData.getAtomicPredicate());
        assertEquals("forest fire ", semanticRelationData.getExtendedPredicate());
        assertEquals("forest fire in California mountains", semanticRelationData.getPrepositionPredicate());
        assertEquals("can not fight", semanticRelationData.getExtendedVerb());
        assertEquals("fight" , semanticRelationData.getAtomicVerb());
        assertEquals("can" , semanticRelationData.getAtomicModalVerb());
    }

}
