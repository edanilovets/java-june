package com.example.responses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Embedded{

	@JsonProperty("customer")
	private List<CustomerItem> customer;

	public void setCustomer(List<CustomerItem> customer){
		this.customer = customer;
	}

	public List<CustomerItem> getCustomers(){
		return customer;
	}

	@Override
 	public String toString(){
		return 
			"Embedded{" + 
			"customer = '" + customer + '\'' + 
			"}";
		}
}