package com.gbz.yaps.petstore.entity.order;

import java.io.Serializable;
import javax.persistence.*;

import com.gbz.yaps.petstore.exception.ValidationException;

/**
 * Entity implementation class for Entity: CreditCard
 *
 */

@Embeddable
public class CreditCard implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1240569727222213131L;
	@Column(name = "credit_card_number", length = 30)
	private String creditCardNumber;
	@Column(name = "credit_card_type")
	private String creditCardType;
	@Column(name = "credit_card_expiry_date", length = 5)
	private String creditCardExpDate;

	@PrePersist
	@PreUpdate
	private void validateData() {
		if (creditCardNumber == null || "".equals(creditCardNumber))
			 throw new ValidationException("Invalid number");
			//System.out.println("Invalid Number");
		if (creditCardType == null || "".equals(creditCardType))
			 throw new ValidationException("Invalid type");
			//System.out.println("Invalid type");
		if (creditCardExpDate == null || "".equals(creditCardExpDate))
			 throw new ValidationException("Invalid expiry date");
			//System.out.println("Invalid expiry date");
	}
	// constructeurs, accesseurs
	// méthodes hashcode, equals et toString

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	public String getCreditCardExpDate() {
		return creditCardExpDate;
	}

	public void setCreditCardExpDate(String creditCardExpDate) {
		this.creditCardExpDate = creditCardExpDate;
	}
}
