package com.ticketbook.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.ticketbook.exception.RouteNotFoundException;
import com.ticketbook.exception.SeatNotAvailableException;

public class BookTicketsNormal {
	/*
	 * enum places{ Andhra,Bangalore,chennai; }
	 */
	static List<Integer> u = new LinkedList<>();
	static List<Integer> l = new LinkedList<>();

	static {
		for (int i = 1; i <= 10; i++) {
			u.add(i);
		}
		for (int i = 1; i <= 10; i++) {
			l.add(i);
		}

	}
	static Map<Integer, Integer> userSeats = new HashMap<>();

	static Map<String, Integer> hashMap = new HashMap<>();

	static {
		hashMap.put("AndhraBangalore", 100);
		hashMap.put("AndhraChennai", 200);
		hashMap.put("BangaloreChennai", 100);
	}

	public static void Normalbook() throws SeatNotAvailableException, RouteNotFoundException {

		Scanner s = new Scanner(System.in);
//
		System.out.println("please enter source");
		String source = s.nextLine();

		System.out.println("please enter destination");
		String destination = s.nextLine();
		BookTicketsNormal booktickets1 = new BookTicketsNormal();
		booktickets1.AvailableSeatsforNormal();

// booktickets2.AvailableSeatsforVolvo();

		System.out.println();
		System.out.println("please enter seat type:For lower seats L ,For upper seats U");
		String seatType = s.nextLine();

		System.out.println("please enter seat number");
		Integer seatNumber = s.nextInt();

		System.out.println("please enter age");
		int age = s.nextInt();

		System.out.println("please choose food option:[true/false] ");
		Boolean food = s.nextBoolean();

		Response response = booktickets1.BookTicket(seatType, seatNumber, age, source, destination, food);

		System.out.println(response.getTicketStatus());

		System.out.println(response.getTicketCost());

		booktickets1.AvailableSeatsAfterBooking();
	}

	public void AvailableSeatsforNormal() {
		System.out.println();
		System.out.println("Availble upper seats for normal bus:"); //
// u.forEach(System.out::print);
		u.forEach(i -> System.out.print("U" + i + " "));

		System.out.println();
		System.out.println("Available lower seats for normal bus:");

		l.forEach(i -> System.out.print("L" + i + " "));
		System.out.println();
	}

	public void AvailableSeatsAfterBooking() {
		System.out.println();
		System.out.println("After booking available upper seats for normal bus");
		u.forEach(i -> System.out.print("U" + i + " "));

		System.out.println();
		System.out.println("After booking available lower seats for normal bus");

		l.forEach(i -> System.out.print("L" + i + " "));
		System.out.println();

	}

	public Response BookTicket(String seatType, Integer seatNumber, Integer age, String source, String destination,
			boolean foodOrder) throws SeatNotAvailableException, RouteNotFoundException {
		Response response = new Response();

		if (seatType.equalsIgnoreCase("l")) {
			if (l.contains(seatNumber)) {
// for cancel tickets
				Integer seatIndex = l.indexOf(seatNumber);
				userSeats.put(seatNumber, seatIndex);
				l.remove(seatNumber);
				response.setTicketCost(ticketCost(age, source, destination, foodOrder));
				response.setTicketStatus("you are successfully booked a ticket for normal bus..your seat no. is "
						+ seatType + " " + seatNumber + "...Happy jouney.. Visit again");
			} else {
				throw new SeatNotAvailableException("Seats are not available please try other seats");
			}

		} else {
			if (u.contains(seatNumber)) {
				Integer seatIndex = u.indexOf(seatNumber);

				userSeats.put(seatNumber, seatIndex);
				u.remove(seatNumber);
				response.setTicketCost(ticketCost(age, source, destination, foodOrder));
				response.setTicketStatus("you are successfully booked a ticket for normal bus..your seat no. is "
						+ seatType + " " + seatNumber + "...Happy jouney.. Visit again");
			} else {
				throw new SeatNotAvailableException("Seats are not available please try other seats");
			}
		}
		return response;
	}

	private static double ticketCost(int age, String source, String destination, boolean foodOrder)
			throws RouteNotFoundException {
		Set<String> compareroutes = hashMap.keySet();
		String route = null;
		for (String string : compareroutes) {
			if (string.toLowerCase().contains(source.toLowerCase())
					&& string.toLowerCase().contains(destination.toLowerCase())) {

				route = source.concat(destination);
				break;
			}
		}
		if (route.equals(null)) {
			throw new RouteNotFoundException("please select correct routes");
		}
		double ticketcost = 0.0;
		if (age >= 5 || age <= 60) {
			ticketcost = hashMap.get(route);
		} else {
			ticketcost = hashMap.get(route) / 2;
		}
		if (foodOrder) {
			ticketcost = ticketcost + 50;
		}
		return ticketcost;
	}

	static void normalcancel() throws SeatNotAvailableException {
		BookTicketsNormal booktickets2 = new BookTicketsNormal();
		Scanner s = new Scanner(System.in);
		System.out.println("please enter seattype");
		String seatType = s.next();
		System.out.println("please enter seatNumber");
		Integer seatNumber = s.nextInt();

		Response response1 = booktickets2.ticketCancellation(seatType, seatNumber);
// String ref=seatType;

		System.out.println(response1.getTicketStatus());
// booktickets2.ticketCancellation(seatType, seatNumber);
		booktickets2.AvailableSeatsforNormal();

	}

	public Response ticketCancellation(String seatType, Integer seatNumber) {
		Response response = new Response();
		Integer index = userSeats.get(seatNumber);
		if (seatType.equalsIgnoreCase("l")) {
			l.add(index, seatNumber);
		} else {
			u.add(index, seatNumber);
		}

		response.setTicketStatus("successfully cancelled your ticket");

		return response;

	}
}
