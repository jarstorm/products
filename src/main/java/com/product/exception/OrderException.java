package com.product.exception;

/**
 * Exception for order module.
 */
public class OrderException extends Exception {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -5693405796710942949L;

	/**
	 * Public constructor.
	 * @param message exception message
	 */
	public OrderException(String message) {
		super(message);
	}

}
