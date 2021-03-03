package com.ticketbook.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.ticketbook.exception.RouteNotFoundException;
import com.ticketbook.exception.SeatNotAvailableException;

public class bookticketmainswitch {

	public static void main(String[] args) throws SeatNotAvailableException, RouteNotFoundException {
		Scanner s;
		String select = "y";

		do {

			s = new Scanner(System.in);
			System.out.println("*****************************************************");
			System.out.println("Bus Ticketing System");
			System.out.println("******************************************************");
			System.out.println("[1]book");
			System.out.println("[2]cancel");
			System.out.println("enter your choice[1/2]");
			int option = s.nextInt();
			switch (option) {

			case 1:
				System.out.println("Enter type of bus: normal/volvo");
				String busname = s.next();
				switch (busname) {
				case "normal":
					BookTicketsNormal.Normalbook();
					break;
				case "volvo":
					BookTicketsVolvo.Volvobook();
					break;
				}

				System.out.println("press N to exit/Y to continue");
				select = s.next();

				break;

			case 2:
				System.out.println("cancel");
				BookTicketsNormal.normalcancel();

				System.out.println("press N to exit/Y to continue");
				select = s.next();

				break;
			default:

				System.out.println("invalid option...please enter option");
				System.out.println("press N to exit/Y to continue");
				select = s.next();
			}
		} while (select.equalsIgnoreCase("y"));
		System.out.println("Thank you for your service..");

	}
}
