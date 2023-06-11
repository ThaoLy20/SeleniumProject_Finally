package email;
import email.EmailAttachmentsSender;
import email.EmailConfig;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


public class EmailSendUtils {
	

//	    private EmailSendUtils() {
//	        super();
//	    }

	    public static void sendEmail(int count_totalTCs, int count_passedTCs, int count_failedTCs, int count_skippedTCs) throws Exception, MessagingException {
	    	//public static void sendEmail() throws AddressException, MessagingException {
	        
	            String messageBody = getTestCasesCountInFormat(count_totalTCs, count_passedTCs, count_failedTCs,count_skippedTCs);

	            String attachmentFile_ExtentReport = System.getProperty("user.dir")+"//ExtentReports//ExtentReport.html";
	            EmailAttachmentsSender.sendEmailWithAttachments(EmailConfig.SERVER, EmailConfig.PORT, EmailConfig.FROM, EmailConfig.PASSWORD, EmailConfig.TO, EmailConfig.SUBJECT, messageBody, attachmentFile_ExtentReport);
	            System.out.println("Send email success");
	        }

	    

	    private static String getTestCasesCountInFormat(int count_totalTCs, int count_passedTCs, int count_failedTCs,
	                                                    int count_skippedTCs) {
	        System.out.println("count_totalTCs: " + count_totalTCs);
	        System.out.println("count_passedTCs: " + count_passedTCs);
	        System.out.println("count_failedTCs: " + count_failedTCs);
	        System.out.println("count_skippedTCs: " + count_skippedTCs);

	        return "<html>\r\n" + "\r\n" + " \r\n" + "\r\n"
	                + "        <body> \r\n<table class=\"container\" align=\"center\" style=\"padding-top:20px\">\r\n<tr align=\"center\"><td colspan=\"4\"><h2>"
	                + EmailConfig.SUBJECT + "</h2></td></tr>\r\n<tr><td>\r\n\r\n"
	                + "       <table style=\"background:#67c2ef;width:120px\" >\r\n"
	                + "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
	                + count_totalTCs + "</td></tr>\r\n"
	                + "                     <tr><td align=\"center\">Total</td></tr>\r\n" + "       \r\n"
	                + "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
	                + "               \r\n" + "                 <table style=\"background:#79c447;width:120px\">\r\n"
	                + "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
	                + count_passedTCs + "</td></tr>\r\n"
	                + "                     <tr><td align=\"center\">Passed</td></tr>\r\n" + "       \r\n"
	                + "                </table>\r\n" + "                </td>\r\n" + "                <td>\r\n"
	                + "                <table style=\"background:#ff5454;width:120px\">\r\n"
	                + "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
	                + count_failedTCs + "</td></tr>\r\n"
	                + "                     <tr><td align=\"center\">Failed</td></tr>\r\n" + "       \r\n"
	                + "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
	                + "                <td>\r\n" + "                <table style=\"background:#fabb3d;width:120px\">\r\n"
	                + "                     <tr><td style=\"font-size: 36px\" class=\"value\" align=\"center\">"
	                + count_skippedTCs + "</td></tr>\r\n"
	                + "                     <tr><td align=\"center\">Skipped</td></tr>\r\n" + "       \r\n"
	                + "                </table>\r\n" + "                \r\n" + "                </td>\r\n"
	                + "                </tr>\r\n" + "               \r\n" + "                \r\n"
	                + "            </table>\r\n" + "       \r\n" + "    </body>\r\n" + "</html>";
	    }

	}


