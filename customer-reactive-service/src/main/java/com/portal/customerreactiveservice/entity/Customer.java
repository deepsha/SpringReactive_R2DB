package com.portal.customerreactiveservice.entity;
import java.util.Objects;
import org.springframework.data.annotation.Id;

//record can be used for java 17
public record  Customer (@Id Long id,String firstname,String lastname)
{

	@Override
	public int hashCode() {
		return Objects.hash(firstname, lastname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname);
	}

	
	
	

}
