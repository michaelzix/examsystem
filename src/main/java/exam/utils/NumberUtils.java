package exam.utils;

import java.math.BigDecimal;

public class NumberUtils {

	
	public static BigDecimal defaultValue(BigDecimal bigDecimal){
		return bigDecimal == null?new BigDecimal(0):bigDecimal;
	}
	
	public static BigDecimal IntegerToBigDecimal(Integer value) {
		return value == null?new BigDecimal(0):new BigDecimal(value);
		
	}
	
}
