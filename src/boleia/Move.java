package boleia;

public class Move {

	private String origin; // origem da viagem
	private String destination; // destino da viagem
	private String date; // data da viagem
	private int hours; // horas da viagem
	private int numSeats; // numero de lugares totais na viagem
	private int emptySeats; // numero de lugares vagos na viagem
	private double duration; // duracao da viagem
	private User user;// Utilizador que cria a viagem

	public Move(String origin, String destination, String date, int hours, double duration, int numSeats, User user) {
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.hours = hours;
		this.duration = duration;
		this.numSeats = numSeats;
		this.emptySeats = numSeats;
		this.user = user;
	}

	/**
	 * Retorna data da viagem
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Retorna origem da viagem
	 * 
	 * @return origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Retorna destino da viagem
	 * 
	 * @return destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Retorna as horas da viagem
	 * 
	 * @return hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * Retorna a duracao da viagem
	 * 
	 * @return duration
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * Retorna o numero de lugares da viagem
	 * 
	 * @return numSeats
	 */
	public int getNumSeats() {
		return numSeats;
	}

	/**
	 * Decrementa o numero de lugares vagos
	 * 
	 * @pre: emptySeats>0
	 */
	public void decEmptySeats() {
		emptySeats--;
	}

	/**
	 * Aumenta o numero de lugares vagos numa idade
	 * 
	 * @pre: emptySeats<=numSeats
	 */
	public void incEmptySeats() {
		emptySeats++;
	}

	/**
	 * Verifica se a viagem tem lugares por ocupar
	 * 
	 * @return emptySeats>0
	 */
	public boolean hasEmptySeats() {
		return emptySeats > 0;
	}

	/**
	 * Retorna o numero de lugares vagos na viagem
	 * 
	 * @return emptySeats
	 */
	public int getEmptySeats() {
		return emptySeats;
	}

	/**
	 * Retorna o conteudo da viagem
	 * 
	 * @return conteudo da viagem
	 */
	public String consult() {
		return (origin + "\n" + destination + "\n" + date + " " + hours + " " + duration + " " + numSeats
				+ "\nLugares vagos: " + emptySeats);
	}

	/**
	 * Retorna o conteudo da viagem
	 * 
	 * @return conteudo da viagem
	 */
	public String consultAll() {
		return (origin + "\n" + destination + "\n" + date + " " + hours + " " + duration + " " + numSeats);
	}

	/**
	 * retorna o utilizador
	 * 
	 * @return user
	 */
	public User getUser() {
		return user;
	}

}
