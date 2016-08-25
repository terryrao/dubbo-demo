package chapter2;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author terryrao on 2016/8/8.
 */
public class PrincialCollectionTest extends Chapter6Test {

    @Test
    public void test () {
        login("classpath:shiro.ini","zhang","123");
        Subject subject = getSubject();
        Object principal = subject.getPrincipal();
        PrincipalCollection previousPrincipals = subject.getPreviousPrincipals();
        Object primaryPrincipal = previousPrincipals.getPrimaryPrincipal();
        Assert.assertEquals(principal,primaryPrincipal);
    }
}
