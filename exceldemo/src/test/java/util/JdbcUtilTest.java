package util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtilTest{


    @Test
    public void getConn() {
        Assert.assertNotNull(JdbcUtil.getConn());
    }

}