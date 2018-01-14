import com.kazakevich.Dictionary;
import com.kazakevich.SentenceNormalizer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by kazakevich_d on 14.01.2018.
 */
public class SentenceNormalizerTest {

    @Test
    public void sentenceNormalizer() {
        Set<String> dictionary = Dictionary.getInstance();

        //valid sentences
        Assert.assertTrue(SentenceNormalizer.isValid("Ihaveacar", dictionary));
        Assert.assertTrue(SentenceNormalizer.isValid("Ihaveacra", dictionary));
        Assert.assertTrue(SentenceNormalizer.isValid("ihaveacra,buthento.", dictionary));

        //not valid sentences
        Assert.assertTrue(!SentenceNormalizer.isValid("Ihveacr", dictionary));
        Assert.assertTrue(!SentenceNormalizer.isValid("Imgv", dictionary));
    }
}
