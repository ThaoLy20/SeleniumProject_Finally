package email;


public class EmailConfig {


	/**
	 * Data for Sending email after execution
	 */
	

	    //Nhớ tạo mật khẩu app (App Password) cho Gmail mới gửi được nhen
	    //Nếu dùng mail của Hosting thì bình thường
	    //Enable Override Report and Send mail in config file => src/test/resources/config/config.properties
	    //OVERRIDE_REPORTS=yes
	    //send_email_to_users=yes

	    public static final String SERVER = "smtp.gmail.com";
	    public static final String PORT = "587";

	    public static final String FROM = "phamthaoly206@gmail.com";
	    public static final String PASSWORD = "scdcfnqnisbxqprr";

	    public static final String[] TO = {"phamthaoly206+1@gmail.com"};
		
	    public static final String SUBJECT = "TEST RESULT";
	}

