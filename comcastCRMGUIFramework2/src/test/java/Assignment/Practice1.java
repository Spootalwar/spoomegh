package Assignment;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

public class Practice1 extends BaseClass{
	@Test(groups = "regressionTest")
	public void testcase1()
	{
		System.out.println("=======testcase1 pass=========");
	}
	@Test(groups = "smokeTest")
	public void testcase2()
	{
		Assert.fail();
		System.out.println("=======testcase2 pass=========");
	}
	@Test(groups = "regressionTest")
	public void testcase3()
	{
		System.out.println("=======testcase3 pass=========");
	}

}
