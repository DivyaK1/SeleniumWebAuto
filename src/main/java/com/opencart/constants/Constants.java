package com.opencart.constants;


import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public final static String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public final static int DEFAULT_TIME_OUT= 5;
	public final static int DEFAULT_ELEMENT_TIME_OUT= 10;
	public static final int DEFAULT_POLLING_TIME = 2000;
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION = "route=account/account";

	
	public static final List<String> EXPECTED_ACCOUNTS_SECTION_LIST = 
								Arrays.asList("My Account", 
											"My Orders", 
											"My Affiliate Account", 
											"Newsletter");
	public static final String LOGOUT_SUCCESS_MESSG = "Account Logout";
	public static final String CHECK_OUT_PAGE_EMPTY_MSG = "Your shopping cart is empty!";
	public static final String ACCOUNT_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created";
	public static final String REGISTER_SHEET_NAME = "register";
	public static final String PRODUCT_SHEET_DATA = "product";
	public static final String CHECK_OUT_PAGE_TITLE = "Shopping Cart  (5.00kg)";
	

}
