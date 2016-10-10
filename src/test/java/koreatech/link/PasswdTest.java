package koreatech.link;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(
        {
                "file:src/main/resources/common/services.xml",
                "file:src/main/resources/common/security.xml",
                "file:src/main/resources/common/mybatis.xml"
        }
)
public class PasswdTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void AuthorInsertTest() {
        System.out.println(passwordEncoder.encode("fia"));
    }
}