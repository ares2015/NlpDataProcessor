package database;

import com.trainingdataprocessor.data.semantics.SemanticExtractionData;
import com.trainingdataprocessor.data.syntax.BigramData;
import com.trainingdataprocessor.data.syntax.SubPathData;
import com.trainingdataprocessor.data.token.TokenDatabaseData;
import com.trainingdataprocessor.data.token.TokenTagData;
import com.trainingdataprocessor.database.TrainingDataDatabaseAccessor;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Oliver on 11/9/2016.
 */
public class TrainingDataDatabaseAccessorTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
    TrainingDataDatabaseAccessor trainingDataDatabaseAccessor = (TrainingDataDatabaseAccessor) context.getBean("trainingDataDatabaseAccessor");

    @Test
    public void testInsertSemanticData() {
        SemanticExtractionData semanticExtractionData = new SemanticExtractionData();
        semanticExtractionData.setAtomicSubject("Gretzky");
        semanticExtractionData.setExtendedSubject("Wayne Gretzky");
        semanticExtractionData.setAtomicVerbPredicate("was");
        semanticExtractionData.setAtomicNounPredicate("player");
        semanticExtractionData.setExtendedNounPredicate("ice hockey player");
        trainingDataDatabaseAccessor.insertSemanticData(semanticExtractionData);

        String sql = "select max(id) from jos_nlp_semantic_data";
        int id = jdbcTemplate.queryForInt(sql);

        sql = "delete from jos_nlp_semantic_data where id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    @Test
//    @Ignore
    public void testInsertBigramData() {
        BigramData bigramData = new BigramData("N", "V", false, false);
        trainingDataDatabaseAccessor.insertBigramData(bigramData);
    }

    @Test
    @Ignore
    public void testUpdateBigramData() {
        BigramData bigramData = new BigramData("V", "N", false, false);
        trainingDataDatabaseAccessor.insertBigramData(bigramData);
    }

    @Test
    @Ignore
    public void testInsertSubPathData() {
        SubPathData subPathData = new SubPathData("N", "AJ", "N AJ AJ N", 4, 3, 7, false);
        trainingDataDatabaseAccessor.insertSubPathData(subPathData);
    }

    @Test
    @Ignore
    public void testUpdateSubPathData() {
        SubPathData subPathData = new SubPathData("V", "N", "V AJ AJ N", 4, 3, 7, false);
        trainingDataDatabaseAccessor.insertSubPathData(subPathData);
    }

    @Test
    @Ignore
    public void testGetTokenDatabaseDataTokenExists() {
        String token = "dog";
        Optional<TokenDatabaseData> tokenDatabaseData = trainingDataDatabaseAccessor.getTokenDatabaseData(token);
        assertEquals(56, tokenDatabaseData.get().getIsNounFrequency());
        assertTrue(tokenDatabaseData.get().isNoun());

    }

    @Test
    @Ignore
    public void testGetTokenDatabaseDataTokenDoesNotExist() {
        String token = "cat";
        Optional<TokenDatabaseData> tokenDatabaseData = trainingDataDatabaseAccessor.getTokenDatabaseData(token);
        assertFalse(tokenDatabaseData.isPresent());
    }

    @Test
    @Ignore
    public void insertTokenTagData() {
        TokenTagData tokenTagData = new TokenTagData();
        tokenTagData.setToken("father");
        tokenTagData.setIsNounFrequency(58);
        tokenTagData.setTotalFrequency(58);
        trainingDataDatabaseAccessor.insertTokenTagData(tokenTagData);
    }

    @Test
    @Ignore
    public void updateTokenTagData() {
        TokenTagData tokenTagData = new TokenTagData();
        tokenTagData.setToken("father");
        tokenTagData.setTokenExistsInDB(true);
        tokenTagData.setIsNounFrequency(59);
        tokenTagData.setTotalFrequency(59);
        trainingDataDatabaseAccessor.insertTokenTagData(tokenTagData);
    }

    @Test
    @Ignore
    public void insertEncodedPath() {
        trainingDataDatabaseAccessor.insertEncodedPath("NVN");
    }

}
