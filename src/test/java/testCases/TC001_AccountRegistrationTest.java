package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() 
	{
		logger.info("**** Starting_TC001_AccountRegistrationTest ****");
		try 
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage repage= new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details...");
		repage.setFirstName(randomeString().toUpperCase());
		repage.setLastName(randomeString().toUpperCase());
		repage.setEmail(randomeString()+"@gmail.com"); //randomly generated the email
		repage.setTelephone(randomeNumber());
		
		
		String password=(randomeAlphaNumeric());
		
		repage.setPassword(password);
		repage.setConfirmPassword(password);
		
		
		repage.setPrivacyPolicy();
		repage.clickContinue();
		
		logger.info("Validating expected message");
		String conmsg=repage.getConfirmationMsg();
		if(conmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}
		
		
		//Assert.assertEquals(conmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) 
		{
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
		
		logger.info("**** Finished_TC001_AccountRegistrationTest ****");

	}
	
	

}
