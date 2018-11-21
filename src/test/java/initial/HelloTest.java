package initial;

import org.junit.Test;

import hello.Hello;
import junit.framework.Assert;

public class HelloTest {

	@Test
	public void test2plus3()
	{
		if(Hello.add(2, 3)==5)System.out.println("答對了！");
		else	System.out.println("正確答案應該是5");
	}

}
