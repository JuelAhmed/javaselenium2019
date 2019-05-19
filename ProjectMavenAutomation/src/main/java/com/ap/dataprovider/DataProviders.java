package com.ap.dataprovider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ap.datamodel.ProductData;
import com.ap.ui.util.DataBuilder;

public class DataProviders {

	
	@DataProvider(name="ProductData")
	public Iterator<Object[]> loginDataProvider(){
		
		Collection<Object[]> customerData = new ArrayList<Object[]>();
		{
			{
				DataBuilder dataBuilder = new DataBuilder();
				List<ProductData> data= dataBuilder.prepareProductData();
				for(ProductData customerModel : data) {
					customerData.add(new Object[] {customerModel});
			}
		}
	}
	return customerData.iterator();
}
@Test(dataProvider="ProductData")
public void testData(ProductData data) {
	System.out.println(data.getProductName());
	System.out.println(data.getQuantity());
	System.out.println(data.getColor());
	System.out.println(data.getSize());	
	
}
}
	

